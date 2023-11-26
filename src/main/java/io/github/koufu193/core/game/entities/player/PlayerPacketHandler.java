package io.github.koufu193.core.game.entities.player;

import io.github.koufu193.core.game.commands.nodes.RootCommandNode;
import io.github.koufu193.core.game.data.Difficulty;
import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.core.game.data.component.TextComponent;
import io.github.koufu193.core.game.data.inventory.InventoryView;
import io.github.koufu193.core.game.data.item.ItemStack;
import io.github.koufu193.core.game.entities.Player;
import io.github.koufu193.core.game.entities.interfaces.IEntity;
import io.github.koufu193.core.game.network.listener.PacketListener;
import io.github.koufu193.core.game.network.listener.PacketListeners;
import io.github.koufu193.core.game.world.chunk.Chunk;
import io.github.koufu193.core.game.world.chunk.LightData;
import io.github.koufu193.network.PacketRegistry;
import io.github.koufu193.network.PacketDecoder;
import io.github.koufu193.network.PacketEncoder;
import io.github.koufu193.network.packets.AbstractPacket;
import io.github.koufu193.network.packets.play.ClientboundUpdatePlayerInfoPacket;
import io.github.koufu193.network.packets.play.channels.PluginChannel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.channels.ClosedChannelException;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public interface PlayerPacketHandler {
    void entityTeleport(@NotNull IEntity entity,@NotNull Location location,boolean onGround);
    default void entityTeleport(@NotNull IEntity entity,@NotNull Location location){
        entityTeleport(entity,location,entity.onGround());
    }
    void teleport(@NotNull Location location,boolean onGround);
    default void teleport(@NotNull Location location){teleport(location,true);};
    void sendExpData(int totalExpPoints,int expLevel,float expProgress);
    void sendPlayerAbilities(@NotNull Player.PlayerAbilities abilities);
    void sendDifficulty(@NotNull Difficulty difficulty,boolean locked);
    void sendPluginMessage(@NotNull PluginChannel channel);
    default void sendChunk(@NotNull Chunk chunk){
        sendChunk(chunk.chunkX(),chunk.chunkZ(),chunk);
    }
    void sendChunk(int chunkX,int chunkZ,@NotNull Chunk chunk);
    void sendCommandNode(@NotNull RootCommandNode root);
    void sendSystemMessage(@NotNull TextComponent component,boolean onActionBar);
    default void sendSystemMessage(@NotNull TextComponent component){
        sendSystemMessage(component,false);
    }
    void sendLightData(@NotNull LightData light);
    void sendActionBarText(@NotNull TextComponent component);
    void setChunkCenter(int chunkX, int chunkZ);
    void sendTabList(@NotNull TextComponent header, @NotNull TextComponent footer);
    void sendPlayerInfo(@NotNull ClientboundUpdatePlayerInfoPacket.PlayerActions... actions);
    void removePlayerInfo(@NotNull UUID... ids);
    void kick(@NotNull TextComponent reason);
    void closeInventory(byte windowId);
    void sendPacket(@NotNull AbstractPacket packet);
    void sendPacketOrThrow(@NotNull AbstractPacket packet) throws ClosedChannelException, ExecutionException, InterruptedException;
    void addListener(@NotNull PacketListener listener);
    PacketListeners listeners();
    PacketEncoder encoder();
    PacketDecoder decoder();
    AbstractPacket read(@NotNull PacketRegistry packets);
    void compressionSize(int size);
    void openInventory(byte windowId,@NotNull InventoryView view);
    void sendContainerContents(byte windowId, byte stateId, @NotNull InventoryView view, @Nullable ItemStack playerHeldItem);
    AbstractPacket handlePacket();
}
