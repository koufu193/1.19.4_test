package io.github.koufu193.network.packets.play;

import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;

public class ClientboundCloseContainerPacket extends InventoryPacket {
    ClientboundCloseContainerPacket(){}
    public ClientboundCloseContainerPacket(byte windowId){
        this.fields(windowId);
    }
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.Byte);
    }

    @Override
    public int packetId() {
        return 0x11;
    }
}
