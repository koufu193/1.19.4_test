package io.github.koufu193.network.packets.play;

import io.github.koufu193.core.game.data.item.ItemStack;
import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import org.jetbrains.annotations.NotNull;

public class ServerboundSetCreativeModeSlotPacket extends InventoryPacket{
    @NotNull
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.Short,DataTypes.Item);
    }
    public short slotId(){
        return (short) this.fields()[0];
    }
    public ItemStack item(){
        return (ItemStack) this.fields()[1];
    }

    @Override
    public int packetId() {
        return 0x2b;
    }
}
