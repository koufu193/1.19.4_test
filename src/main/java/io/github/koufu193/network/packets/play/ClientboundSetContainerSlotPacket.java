package io.github.koufu193.network.packets.play;

import io.github.koufu193.core.game.data.inventory.InventoryView;
import io.github.koufu193.core.game.data.item.ItemStack;
import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ClientboundSetContainerSlotPacket extends InventoryPacket {
    ClientboundSetContainerSlotPacket(){}
    public ClientboundSetContainerSlotPacket(byte windowId, int stateId, short slot, @Nullable ItemStack item){
        if(item==null) item=ItemStack.AIR;
        fields(windowId,stateId,slot,item);
    }
    @NotNull
    @Override
    public PacketFormat format(){
        return PacketFormat.of(DataTypes.Byte,DataTypes.VarInt,DataTypes.Short,DataTypes.Item);
    }

    @Override
    public int packetId() {
        return 0x14;
    }
}
