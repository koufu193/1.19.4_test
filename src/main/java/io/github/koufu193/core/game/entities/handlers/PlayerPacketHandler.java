package io.github.koufu193.core.game.entities.handlers;

import io.github.koufu193.core.game.commands.nodes.RootCommandNode;
import io.github.koufu193.core.game.data.Difficulty;
import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.core.game.data.component.TextComponent;
import io.github.koufu193.core.game.entities.Player;
import io.github.koufu193.core.game.world.chunk.Chunk;
import io.github.koufu193.core.game.world.chunk.LightData;
import io.github.koufu193.network.packets.play.ClientboundUpdatePlayerInfoPacket;
import io.github.koufu193.network.packets.play.channels.IPluginChannel;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface PlayerPacketHandler {
    void teleport(@NotNull Location location);
    void sendExpData(int totalExpPoints,int expLevel,float expProgress);
    void sendPlayerAbilities(@NotNull Player.PlayerAbilities abilities);
    void sendDifficulty(@NotNull Difficulty difficulty,boolean locked);
    void sendPluginMessage(@NotNull IPluginChannel channel);
    void sendChunk(@NotNull Chunk chunk);
    void sendCommandNode(@NotNull RootCommandNode root);
    void sendSystemMessage(@NotNull TextComponent component,boolean onActionBar);
    void sendLightData(@NotNull LightData light);
    void sendActionBarText(@NotNull TextComponent component);
    void setChunkCenter(int chunkX, int chunkZ);
    void sendTabList(@NotNull TextComponent header, @NotNull TextComponent footer);
    void sendPlayerInfo(@NotNull ClientboundUpdatePlayerInfoPacket.PlayerActions... actions);
    void removePlayerInfo(@NotNull UUID... ids);
}
