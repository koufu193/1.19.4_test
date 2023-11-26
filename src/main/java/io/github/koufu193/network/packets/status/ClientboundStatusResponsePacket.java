package io.github.koufu193.network.packets.status;

import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import org.jetbrains.annotations.NotNull;

/**
 * {@link ServerboundStatusRequestPacket}の応答として使われるパケット
 */
public class ClientboundStatusResponsePacket extends AbstractPacket {
    @NotNull
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.String);
    }

    @Override
    public int packetId() {
        return 0;
    }
}
