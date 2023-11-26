package io.github.koufu193.network.packets.play;

import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import org.jetbrains.annotations.NotNull;

public class ClientboundInitWorldBorderPacket extends AbstractPacket {
    @NotNull
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.Double,DataTypes.Double,DataTypes.Double,DataTypes.Double,DataTypes.VarInt,DataTypes.VarInt,DataTypes.VarInt,DataTypes.VarInt);
    }

    @Override
    public int packetId() {
        return 0x22;
    }
}
