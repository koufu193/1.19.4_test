package io.github.koufu193.network.packets.handshake;

import io.github.koufu193.exceptions.UndefinedPacketIdException;
import io.github.koufu193.network.PacketRegistry;
import io.github.koufu193.network.packets.AbstractPacket;

/**
 * ハンドシェイク状態のパケットレジストリ
 */
public class HandshakePacketRegistry implements PacketRegistry {
    private static final HandshakePacketRegistry registry =new HandshakePacketRegistry();
    @Override
    public AbstractPacket getClientboundPacket(int id) {
        throw new UndefinedPacketIdException("Undefined packet id of "+id);
    }

    @Override
    public AbstractPacket getServerboundPacket(int id) {
        if(id!=0) throw new UndefinedPacketIdException("Undefined packet id of "+id);
        return new ServerboundHandshakePacket();
    }

    public static HandshakePacketRegistry getRegistry() {
        return registry;
    }
}
