package io.github.koufu193.network.packets.handshake;

import io.github.koufu193.core.properties.exceptions.UndefinedPacketIdException;
import io.github.koufu193.network.IPackets;
import io.github.koufu193.network.packets.AbstractPacket;

public class HandshakePackets implements IPackets {
    private static final HandshakePackets packets=new HandshakePackets();
    @Override
    public AbstractPacket getClientboundPacket(int id) {
        throw new UndefinedPacketIdException("Invalid packet id of "+id);
    }

    @Override
    public AbstractPacket getServerboundPacket(int id) {
        if(id!=0) throw new UndefinedPacketIdException("Invalid packet id of "+id);
        return new ServerboundHandshakePacket();
    }

    public static HandshakePackets getPackets() {
        return packets;
    }
}
