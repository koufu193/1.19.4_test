package io.github.koufu193.network.packets.login;

import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;

public class ClientboundDisconnectPacket extends AbstractPacket {
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.Chat);
    }

    @Override
    public int packetId() {
        return 0;
    }
}
