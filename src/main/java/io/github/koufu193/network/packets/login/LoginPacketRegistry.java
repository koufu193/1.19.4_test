package io.github.koufu193.network.packets.login;

import io.github.koufu193.exceptions.UndefinedPacketIdException;
import io.github.koufu193.network.PacketRegistry;
import io.github.koufu193.network.packets.AbstractPacket;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * ログイン状態のパケットレジストリ
 */
public class LoginPacketRegistry implements PacketRegistry {
    private static final Map<Integer, Supplier<AbstractPacket>> clientboundPackets=new HashMap<>(){{
        put(0,ClientboundDisconnectPacket::new);
        put(0x03,ClientboundSetCompressionPacket::new);
        put(0x02,ClientboundLoginSuccessPacket::new);
    }};
    private static final Map<Integer,Supplier<AbstractPacket>> serverboundPackets=new HashMap<>(){{
        put(0,ServerboundLoginStartPacket::new);
    }};
    private static final LoginPacketRegistry registry =new LoginPacketRegistry();
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
    public static LoginPacketRegistry getRegistry() {
        return registry;
    }

}
