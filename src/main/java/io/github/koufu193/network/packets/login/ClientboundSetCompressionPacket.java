package io.github.koufu193.network.packets.login;

import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import org.jetbrains.annotations.NotNull;

/**
 * クライアントに圧縮の閾値を送るパケット
 */
public class ClientboundSetCompressionPacket extends AbstractPacket {
    ClientboundSetCompressionPacket(){}

    /**
     * @param threshold 圧縮するかしないかの閾値
     */
    public ClientboundSetCompressionPacket(int threshold){
        fields(threshold);
    }
    @NotNull
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.VarInt.encodeIfNonnull());
    }

    @Override
    public int packetId() {
        return 0x03;
    }
}
