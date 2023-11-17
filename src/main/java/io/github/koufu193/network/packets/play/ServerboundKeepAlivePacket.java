package io.github.koufu193.network.packets.play;

import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;

public class ServerboundKeepAlivePacket extends AbstractPacket {
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.Long);
    }

    @Override
    public int packetId() {
        return 0x12;
    }
    public long id(){
        return (long)fields()[0];
    }
}
