package io.github.koufu193.network.packets.status;

import io.github.koufu193.exceptions.UndefinedPacketIdException;
import io.github.koufu193.network.PacketRegistry;
import io.github.koufu193.network.packets.AbstractPacket;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * ステータス状態のパケットレジストリ
 */
public class StatusPacketRegistry implements PacketRegistry {
    private static final Map<Integer, Supplier<AbstractPacket>> clientboundPackets=new HashMap<>(){{
        put(0, ClientboundStatusResponsePacket::new);
        put(1,ClientboundPingResponsePacket::new);
    }};
    private static final Map<Integer,Supplier<AbstractPacket>> serverboundPackets=new HashMap<>(){{
        put(0,ServerboundStatusRequestPacket::new);
        put(1,ServerboundPingRequestPacket::new);
    }};
    private static final StatusPacketRegistry registry =new StatusPacketRegistry();
    @Override
    public AbstractPacket getClientboundPacket(int id) {
        Supplier<AbstractPacket> packetSupplier=clientboundPackets.get(id);
        if(packetSupplier==null) throw new UndefinedPacketIdException("Invalid packet id of "+id);
        return packetSupplier.get();
    }

    @Override
    public AbstractPacket getServerboundPacket(int id) {
        Supplier<AbstractPacket> packetSupplier=serverboundPackets.get(id);
        if(packetSupplier==null) throw new UndefinedPacketIdException("Invalid packet id of "+id);
        return packetSupplier.get();
    }

    public static StatusPacketRegistry getRegistry() {
        return registry;
    }
}
