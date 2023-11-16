package io.github.koufu193.core.game.entities.handlers;

import io.github.koufu193.core.game.commands.nodes.RootCommandNode;
import io.github.koufu193.core.game.data.Difficulty;
import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.core.game.data.component.TextComponent;
import io.github.koufu193.core.game.data.inventory.Inventory;
import io.github.koufu193.core.game.data.inventory.InventoryView;
import io.github.koufu193.core.game.data.item.ItemStack;
import io.github.koufu193.core.game.entities.Player;
import io.github.koufu193.core.game.world.chunk.Chunk;
import io.github.koufu193.core.game.world.chunk.LightData;
import io.github.koufu193.network.IPackets;
import io.github.koufu193.network.PacketDecoder;
import io.github.koufu193.network.PacketEncoder;
import io.github.koufu193.network.packets.AbstractPacket;
import io.github.koufu193.network.packets.play.ClientboundUpdatePlayerInfoPacket;
import io.github.koufu193.network.packets.play.channels.IPluginChannel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
    void sendPacketOrThrow(@NotNull AbstractPacket packet);
    PacketEncoder encoder();
    PacketDecoder decoder();
    AbstractPacket read(@NotNull IPackets packets);
    void compressionSize(int size);
    void openInventory(byte windowId,@NotNull InventoryView view);
    void sendContainerContents(byte windowId, byte stateId, @NotNull InventoryView view, @Nullable ItemStack playerHeldItem);
}
