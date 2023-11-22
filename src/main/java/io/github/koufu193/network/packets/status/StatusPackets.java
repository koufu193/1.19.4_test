package io.github.koufu193.network.packets.status;

import io.github.koufu193.core.properties.exceptions.UndefinedPacketIdException;
import io.github.koufu193.network.IPackets;
import io.github.koufu193.network.packets.AbstractPacket;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class StatusPackets implements IPackets {
    private static final Map<Integer, Supplier<AbstractPacket>> clientboundPackets=new HashMap<>(){{
        put(0, ClientboundStatusResponsePacket::new);
        put(1,ClientboundPingResponsePacket::new);
    }};
    private static final Map<Integer,Supplier<AbstractPacket>> serverboundPackets=new HashMap<>(){{
        put(0,ServerboundStatusRequestPacket::new);
        put(1,ServerboundPingRequestPacket::new);
    }};
    private static final StatusPackets packets=new StatusPackets();
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

    public static StatusPackets getPackets() {
        return packets;
    }
}
