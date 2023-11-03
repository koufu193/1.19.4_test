package io.github.koufu193.network.packets.play;

import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;

public class ClientboundSetHeldSlotPacket extends AbstractPacket {
    ClientboundSetHeldSlotPacket(){}
    public ClientboundSetHeldSlotPacket(byte slot){
        if(slot<0||9<=slot) throw new IllegalArgumentException(String.format("heldSlot must be between %d and %d",0,8));
        this.fields(slot);
    }
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.Byte);
    }

    @Override
    public int packetId() {
        return 0x4d;
    }
}
