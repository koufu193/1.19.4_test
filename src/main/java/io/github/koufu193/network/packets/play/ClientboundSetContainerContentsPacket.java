package io.github.koufu193.network.packets.play;

import io.github.koufu193.core.game.data.inventory.InventoryView;
import io.github.koufu193.core.game.data.item.ItemStack;
import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ClientboundSetContainerContentsPacket extends InventoryPacket {
    ClientboundSetContainerContentsPacket(){}
    public ClientboundSetContainerContentsPacket(byte windowId,int stateId,@NotNull InventoryView view, @Nullable ItemStack playerHeldItem){
        if(playerHeldItem==null) playerHeldItem=ItemStack.AIR;
        fields(windowId,stateId,view.inventory().getAllContents(),playerHeldItem);
    }
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.Byte,DataTypes.VarInt,DataTypes.Item.array(),DataTypes.Item);
    }

    @Override
    public int packetId() {
        return 0x12;
    }
}
