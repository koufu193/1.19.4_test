package io.github.koufu193.network.packets.handshake;

import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;

public class ServerboundHandshakePacket extends AbstractPacket {
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.VarInt,DataTypes.String,DataTypes.Short,DataTypes.VarInt);
    }

    @Override
    public int packetId() {
        return 0x00;
    }
}
