package io.github.koufu193.network.packets.play;

import io.github.koufu193.exceptions.InvalidPacketIdException;
import io.github.koufu193.network.IPackets;
import io.github.koufu193.network.packets.AbstractPacket;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class PlayPackets implements IPackets {
    private static final Map<Integer, Supplier<AbstractPacket>> clientboundPackets = new HashMap<>() {
        {
            reg(ClientboundLoginPacket::new);
            reg(ClientboundPluginMessagePacket::new);
            reg(ClientboundSetDefaultSpawnPositionPacket::new);
            reg(ClientboundSynchronizePlayerPositionPacket::new);
            reg(ClientboundPlayerAbilitiesPacket::new);
            reg(ClientboundChangeDifficultyPacket::new);
            reg(ClientboundSetExpPacket::new);
            reg(ClientboundChunkDataAndUpdateLightPacket::new);
            reg(ClientboundSetChunkCenterPacket::new);
            reg(ClientboundInitWorldBorderPacket::new);
            reg(ClientboundCommandsPacket::new);
            reg(ClientboundWorldTimePacket::new);
            reg(ClientboundUpdateLightPacket::new);
            reg(ClientboundKeepAlivePacket::new);
            reg(ClientboundSetActionBarTextPacket::new);
            reg(ClientboundSystemMessagePacket::new);
            reg(ClientboundSetTabListHeaderAndFooterPacket::new);
            reg(ClientboundUpdatePlayerInfoPacket::new);
            reg(ClientboundRemovePlayerInfoPacket::new);
            reg(ClientboundSetContainerContentsPacket::new);
            reg(ClientboundSetContainerSlotPacket::new);
            reg(ClientboundDisconnectPacket::new);
        }

        private void reg(Supplier<AbstractPacket> supp) {
            put(supp.get().packetId(), supp);
        }
    };
    private static final Map<Integer, Supplier<AbstractPacket>> serverboundPackets = new HashMap<>() {
        {
            reg(ServerboundPluginMessagePacket::new);
            reg(ServerboundClientInformationPacket::new);
            reg(ServerboundSetPlayerPositionAndRotationPacket::new);
            reg(ServerboundConfirmTeleportationPacket::new);
            reg(ServerboundSetPlayerPositionPacket::new);
            reg(ServerboundChatCommandPacket::new);
            reg(ServerboundSetPlayerRotationPacket::new);
            reg(ServerboundKeepAlivePacket::new);
        }

        private void reg(Supplier<AbstractPacket> supp) {
            put(supp.get().packetId(), supp);
        }
    };
    private static final PlayPackets packets = new PlayPackets();

    @Override
    public AbstractPacket getClientboundPacket(int id) {
        Supplier<AbstractPacket> packetSupplier = clientboundPackets.get(id);
        if (packetSupplier == null) throw new InvalidPacketIdException("Invalid packet id of " + id);
        return packetSupplier.get();
    }

    @Override
    public AbstractPacket getServerboundPacket(int id) {
        Supplier<AbstractPacket> packetSupplier = serverboundPackets.get(id);
        if (packetSupplier == null) {
            System.out.println(Integer.toHexString(id));
            return null;
            //throw new InvalidPacketIdException("Invalid packet id of "+id);
        }
        return packetSupplier.get();
    }

    public static PlayPackets getPackets() {
        return packets;
    }
}
