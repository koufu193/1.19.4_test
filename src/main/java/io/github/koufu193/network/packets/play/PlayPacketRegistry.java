package io.github.koufu193.network.packets.play;

import io.github.koufu193.network.PacketRegistry;
import io.github.koufu193.network.packets.AbstractPacket;
import io.github.koufu193.network.packets.UndefinedPacket;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * プレイ状態のパケットレジストリ
 */
public class PlayPacketRegistry implements PacketRegistry {
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
            reg(ClientboundOpenScreenPacket::new);
            reg(ClientboundOpenHorseScreenPacket::new);
            reg(ClientboundCloseContainerPacket::new);
            reg(ClientboundJoinPlayerPacket::new);
            reg(ClientboundUpdateEntityPositionAndRotationPacket::new);
            reg(ClientboundUpdateEntityPositionPacket::new);
            reg(ClientboundUpdateEntityRotationPacket::new);
            reg(ClientboundTeleportEntityPacket::new);
            reg(ClientboundSetHeadRotationPacket::new);
            reg(ClientboundBlockUpdatePacket::new);
        }

        private void reg(Supplier<AbstractPacket> supp) {
            AbstractPacket instance=supp.get();
            if(containsKey(instance.packetId())) {
                System.out.println(instance.getClass().getSimpleName()+" "+get(instance.packetId()).get().getClass().getSimpleName());
            }
            put(instance.packetId(),supp);
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
            reg(ServerboundSetHeldItemPacket::new);
            reg(ServerboundSetCreativeModeSlotPacket::new);
            reg(ServerboundCloseContainerPacket::new);
            reg(ServerboundClickContainerPacket::new);
            reg(ServerboundPlayerAbilitiesPacket::new);
        }

        private void reg(Supplier<AbstractPacket> supp) {
            put(supp.get().packetId(), supp);
        }
    };
    private static final PlayPacketRegistry registry = new PlayPacketRegistry();

    @Override
    public AbstractPacket getClientboundPacket(int id) {
        Supplier<AbstractPacket> packetSupplier = clientboundPackets.get(id);
        if (packetSupplier == null) return new UndefinedPacket(id);
        return packetSupplier.get();
    }

    @Override
    public AbstractPacket getServerboundPacket(int id) {
        Supplier<AbstractPacket> packetSupplier = serverboundPackets.get(id);
        if (packetSupplier == null) return new UndefinedPacket(id);
        return packetSupplier.get();
    }

    public static PlayPacketRegistry getRegistry() {
        return registry;
    }
}
