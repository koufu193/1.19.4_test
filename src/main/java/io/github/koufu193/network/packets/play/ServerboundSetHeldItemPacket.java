package io.github.koufu193.network.packets.play;

import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import org.jetbrains.annotations.NotNull;

public class ServerboundSetHeldItemPacket extends InventoryPacket{
    @NotNull
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.Short);
    }
    public short slotId(){
        return (short) this.fields()[0];
    }
    @Override
    public int packetId() {
        return 0x28;
    }
}
