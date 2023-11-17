package io.github.koufu193.core.game.entities.player.v1194;

import io.github.koufu193.core.game.commands.nodes.RootCommandNode;
import io.github.koufu193.core.game.data.Difficulty;
import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.core.game.data.component.TextComponent;
import io.github.koufu193.core.game.data.inventory.HorseInventory;
import io.github.koufu193.core.game.data.inventory.InventoryView;
import io.github.koufu193.core.game.data.item.ItemStack;
import io.github.koufu193.core.game.entities.Player;
import io.github.koufu193.core.game.entities.player.PlayerPacketHandler;
import io.github.koufu193.core.game.network.listener.PacketListener;
import io.github.koufu193.core.game.network.listener.PacketListeners;
import io.github.koufu193.core.game.world.chunk.Chunk;
import io.github.koufu193.core.game.world.chunk.LightData;
import io.github.koufu193.network.IPackets;
import io.github.koufu193.network.PacketDecoder;
import io.github.koufu193.network.PacketEncoder;
import io.github.koufu193.network.packets.AbstractPacket;
import io.github.koufu193.network.packets.play.*;
import io.github.koufu193.network.packets.play.channels.IPluginChannel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class V1194PlayerPacketHandler implements PlayerPacketHandler {
    private final AsynchronousSocketChannel channel;
    private final PacketEncoder encoder;
    private final PacketDecoder decoder;
    private final PacketListeners listeners;
    public V1194PlayerPacketHandler(@NotNull Player player,@NotNull AsynchronousSocketChannel channel,@NotNull PacketEncoder encoder,@NotNull PacketDecoder decoder){
        this.channel=channel;
        this.encoder=encoder;
        this.decoder=decoder;
        this.listeners=new PacketListeners(player);
    }
    @Override
    public void teleport(@NotNull Location location) {
        sendPacket(new ClientboundSynchronizePlayerPositionPacket(location));
    }

    @Override
    public void sendExpData(int totalExpPoints, int expLevel, float expProgress) {
        sendPacket(new ClientboundSetExpPacket(expProgress,totalExpPoints,expLevel));
    }

    @Override
    public void sendPlayerAbilities(@NotNull Player.PlayerAbilities abilities) {
        sendPacket(new ClientboundPlayerAbilitiesPacket(abilities));
    }

    @Override
    public void sendDifficulty(@NotNull Difficulty difficulty, boolean locked) {
        sendPacket(new ClientboundChangeDifficultyPacket(difficulty,locked));
    }

    @Override
    public void sendPluginMessage(@NotNull IPluginChannel channel) {
        sendPacket(new ClientboundPluginMessagePacket(channel));
    }

    @Override
    public void kick(@NotNull TextComponent reason) {
        sendPacket(new ClientboundDisconnectPacket(reason));
    }

    @Override
    public void sendChunk(@NotNull Chunk chunk) {
        sendPacket(new ClientboundChunkDataAndUpdateLightPacket(chunk));
    }

    @Override
    public void sendCommandNode(@NotNull RootCommandNode root) {
        sendPacket(new ClientboundCommandsPacket(root));
    }

    @Override
    public void sendSystemMessage(@NotNull TextComponent component, boolean onActionBar) {
        sendPacket(new ClientboundSystemMessagePacket(component,onActionBar));
    }

    @Override
    public void sendLightData(@NotNull LightData light) {
        sendPacket(new ClientboundUpdateLightPacket(light));
    }

    @Override
    public void sendActionBarText(@NotNull TextComponent component) {
        sendPacket(new ClientboundSetActionBarTextPacket(component));
    }

    @Override
    public void setChunkCenter(int chunkX, int chunkZ) {
        sendPacket(new ClientboundSetChunkCenterPacket(chunkX, chunkZ));
    }

    @Override
    public void sendTabList(@NotNull TextComponent header, @NotNull TextComponent footer) {
        sendPacket(new ClientboundSetTabListHeaderAndFooterPacket(header,footer));
    }

    @Override
    public void sendPlayerInfo(@NotNull ClientboundUpdatePlayerInfoPacket.PlayerActions... actions) {
        sendPacket(new ClientboundUpdatePlayerInfoPacket(actions));
    }

    @Override
    public void removePlayerInfo(@NotNull UUID... ids) {
        sendPacket(new ClientboundRemovePlayerInfoPacket(ids));
    }

    public synchronized void sendPacket(@NotNull AbstractPacket packet){
        try{
            sendPacketOrThrow(packet);
            this.listeners.onSend(packet);
        } catch (RuntimeException e) {
            reportError(e);
        }
    }

    @Override
    public synchronized void sendPacketOrThrow(@NotNull AbstractPacket packet) {
        try{
            channel.write(ByteBuffer.wrap(this.encoder.encode(packet))).get();
        } catch (IOException | ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addListener(@NotNull PacketListener listener) {
        this.listeners.add(listener);
    }

    @Override
    public PacketListeners listeners() {
        return this.listeners;
    }

    @Override
    public PacketEncoder encoder() {
        return this.encoder;
    }

    @Override
    public PacketDecoder decoder() {
        return this.decoder;
    }

    @Override
    public AbstractPacket read(@NotNull IPackets packets) {
        try {
            AbstractPacket packet=this.decoder.decode(this.channel, packets);
            this.listeners.onReceive(packet);
            return packet;
        } catch (ExecutionException | InterruptedException | TimeoutException e) {
            reportError(e);
            return null;
        }
    }

    @Override
    public void compressionSize(int size) {
        this.encoder.setCompressionSize(size);
        this.decoder.setCompressionSize(size);
    }

    @Override
    public void openInventory(byte windowId,@NotNull InventoryView view) {
        if(view.inventory() instanceof HorseInventory horse) sendPacket(new ClientboundOpenHorseScreenPacket(windowId,horse));
        else sendPacket(new ClientboundOpenScreenPacket(windowId,view));
    }

    @Override
    public void closeInventory(byte windowId) {
        sendPacket(new ClientboundCloseContainerPacket(windowId));
    }

    @Override
    public void sendContainerContents(byte windowId, byte stateId, @NotNull InventoryView view, @Nullable ItemStack playerHeldItem) {
        sendPacket(new ClientboundSetContainerContentsPacket(windowId,stateId,view,playerHeldItem));
    }

    @Override
    public AbstractPacket readPlayPacket() {
        return this.read(PlayPackets.getPackets());
    }

    private void reportError(Throwable throwable){
        throwable.printStackTrace();
    }
}