package io.github.koufu193.core.game.entities.player.v1194;

import io.github.koufu193.core.game.commands.nodes.RootCommandNode;
import io.github.koufu193.core.game.data.Difficulty;
import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.core.game.data.component.TextComponent;
import io.github.koufu193.core.game.data.inventory.HorseInventory;
import io.github.koufu193.core.game.data.inventory.InventoryView;
import io.github.koufu193.core.game.data.item.ItemStack;
import io.github.koufu193.core.game.entities.Player;
import io.github.koufu193.core.game.entities.interfaces.IEntity;
import io.github.koufu193.core.game.entities.player.PlayerPacketHandler;
import io.github.koufu193.core.game.network.listener.PacketListener;
import io.github.koufu193.core.game.network.listener.PacketListeners;
import io.github.koufu193.core.game.world.chunk.Chunk;
import io.github.koufu193.core.game.world.chunk.LightData;
import io.github.koufu193.network.PacketDecoder;
import io.github.koufu193.network.PacketEncoder;
import io.github.koufu193.network.PacketRegistry;
import io.github.koufu193.network.packets.AbstractPacket;
import io.github.koufu193.network.packets.play.*;
import io.github.koufu193.network.packets.play.channels.PluginChannel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.ClosedChannelException;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class V1194PlayerPacketHandler implements PlayerPacketHandler {
    private final AsynchronousSocketChannel channel;
    private final PacketEncoder encoder;
    private final PacketDecoder decoder;
    private final PacketListeners listeners;
    private final Player player;

    public V1194PlayerPacketHandler(@NotNull Player player, @NotNull PacketListeners listeners, @NotNull AsynchronousSocketChannel channel, @NotNull PacketEncoder encoder, @NotNull PacketDecoder decoder) {
        this.channel = channel;
        this.encoder = encoder;
        this.decoder = decoder;
        this.listeners = listeners;
        this.player = player;
    }

    @Override
    public void teleport(@NotNull Location location, boolean onGround) {
        sendPacket(new ClientboundSynchronizePlayerPositionPacket(location));
        AbstractPacket teleportPacket = new ClientboundTeleportEntityPacket(player.entityId(), location, onGround);
        player.server().onlinePlayers().forEach(player1 -> {
            if (player1 == player) return;
            player1.packetHandler().sendPacket(teleportPacket);
        });
    }

    @Override
    public void sendExpData(int totalExpPoints, int expLevel, float expProgress) {
        sendPacket(new ClientboundSetExpPacket(expProgress, totalExpPoints, expLevel));
    }

    @Override
    public void sendPlayerAbilities(@NotNull Player.PlayerAbilities abilities) {
        sendPacket(new ClientboundPlayerAbilitiesPacket(abilities));
    }

    @Override
    public void entityTeleport(@NotNull IEntity entity, @NotNull Location location, boolean onGround) {
        sendPacket(new ClientboundTeleportEntityPacket(entity.entityId(), location, onGround));
    }

    @Override
    public void sendDifficulty(@NotNull Difficulty difficulty, boolean locked) {
        sendPacket(new ClientboundChangeDifficultyPacket(difficulty, locked));
    }

    @Override
    public void sendPluginMessage(@NotNull PluginChannel channel) {
        sendPacket(new ClientboundPluginMessagePacket(channel));
    }

    @Override
    public void kick(@NotNull TextComponent reason) {
        sendPacket(new ClientboundDisconnectPacket(reason));
    }

    @Override
    public void sendChunk(int chunkX, int chunkZ, @NotNull Chunk chunk) {
        sendPacket(new ClientboundChunkDataAndUpdateLightPacket(chunkX, chunkZ, chunk));
    }

    @Override
    public void sendCommandNode(@NotNull RootCommandNode root) {
        sendPacket(new ClientboundCommandsPacket(root));
    }

    @Override
    public void sendSystemMessage(@NotNull TextComponent component, boolean onActionBar) {
        sendPacket(new ClientboundSystemMessagePacket(component, onActionBar));
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
        sendPacket(new ClientboundSetTabListHeaderAndFooterPacket(header, footer));
    }

    @Override
    public void sendPlayerInfo(@NotNull ClientboundUpdatePlayerInfoPacket.PlayerActions... actions) {
        sendPacket(new ClientboundUpdatePlayerInfoPacket(actions));
    }

    @Override
    public void removePlayerInfo(@NotNull UUID... ids) {
        sendPacket(new ClientboundRemovePlayerInfoPacket(ids));
    }

    public synchronized void sendPacket(@NotNull AbstractPacket packet) {
        try {
            sendPacketOrThrow(packet);
        } catch (RuntimeException | ClosedChannelException | ExecutionException | InterruptedException e) {
            reportError(e);
        }
    }

    @Override
    public synchronized void sendPacketOrThrow(@NotNull AbstractPacket packet) throws ClosedChannelException, ExecutionException, InterruptedException {
        if (!channel.isOpen()) throw new ClosedChannelException();
        channel.write(ByteBuffer.wrap(this.encoder.encode(packet))).get();
        this.listeners.onSend(packet);
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
    @Nullable
    public AbstractPacket read(@NotNull PacketRegistry packets) {
        if(!this.channel.isOpen()) return null;
        try {
            AbstractPacket packet = this.decoder.decode(this.channel, packets);
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
    public void openInventory(byte windowId, @NotNull InventoryView view) {
        if (view.inventory() instanceof HorseInventory horse)
            sendPacket(new ClientboundOpenHorseScreenPacket(windowId, horse));
        else sendPacket(new ClientboundOpenScreenPacket(windowId, view));
    }

    @Override
    public void closeInventory(byte windowId) {
        sendPacket(new ClientboundCloseContainerPacket(windowId));
    }

    @Override
    public void sendContainerContents(byte windowId, byte stateId, @NotNull InventoryView view, @Nullable ItemStack playerHeldItem) {
        sendPacket(new ClientboundSetContainerContentsPacket(windowId, stateId, view, playerHeldItem));
    }

    @Override
    public AbstractPacket handlePacket() {
        return this.read(PlayPacketRegistry.getRegistry());
    }

    private void reportError(Throwable throwable) {
        throwable.printStackTrace();
    }
}
