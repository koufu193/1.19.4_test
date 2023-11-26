package io.github.koufu193.network.packets.status;

import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import org.jetbrains.annotations.NotNull;

/**
 * {@link ServerboundPingRequestPacket}の応答として使われるパケット
 */
public class ClientboundPingResponsePacket extends AbstractPacket {
    ClientboundPingResponsePacket(){}

    /**
     * @param payload {@link ServerboundPingRequestPacket}で送られたペイロードと同じものでなければならない
     */
    public ClientboundPingResponsePacket(long payload){
        fields(payload);
    }
    @NotNull
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.Long);
    }

    @Override
    public int packetId() {
        return 0x01;
    }

}
