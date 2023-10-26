package io.github.koufu193.core.game.entities.handlers;

import io.github.koufu193.core.game.commands.nodes.RootCommandNode;
import io.github.koufu193.core.game.data.Difficulty;
import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.core.game.data.component.TextComponent;
import io.github.koufu193.core.game.entities.Player;
import io.github.koufu193.core.game.world.chunk.Chunk;
import io.github.koufu193.core.game.world.chunk.LightData;
import io.github.koufu193.network.PacketEncoder;
import io.github.koufu193.network.packets.AbstractPacket;
import io.github.koufu193.network.packets.play.*;
import io.github.koufu193.network.packets.play.channels.IPluginChannel;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class V1194PlayerPacketHandler implements PlayerPacketHandler{
    private final AsynchronousSocketChannel channel;
    public V1194PlayerPacketHandler(@NotNull AsynchronousSocketChannel channel){
        this.channel=channel;
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

    private void sendPacket(@NotNull AbstractPacket packet){
        try{
            channel.write(ByteBuffer.wrap(PacketEncoder.getEncoder().encode(packet))).get();
        } catch (IOException | ExecutionException | InterruptedException e) {
            reportError(e);
        }
    }

    private void reportError(Throwable throwable){
        throwable.printStackTrace();
    }
}
