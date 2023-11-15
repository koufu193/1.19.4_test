package io.github.koufu193.network.packets.play;

import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;

public class ServerboundCloseContainerPacket extends InventoryPacket{
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.Byte);
    }
    public byte windowId(){
        return (byte) this.fields()[0];
    }

    @Override
    public int packetId() {
        return 0x0c;
    }
}
