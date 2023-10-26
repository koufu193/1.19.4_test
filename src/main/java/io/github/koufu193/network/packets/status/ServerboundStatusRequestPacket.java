package io.github.koufu193.network.packets.status;

import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.packets.AbstractPacket;

public class ServerboundStatusRequestPacket extends AbstractPacket {
    @Override
    public PacketFormat format() {
        return PacketFormat.ofNull();
    }

    @Override
    public int packetId() {
        return 0;
    }
}
