package io.github.koufu193.network.packets.play;

import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;

public class ClientboundLoginPacket extends AbstractPacket {
    @Override
    public PacketFormat format() {
        return PacketFormat.of(
                DataTypes.Int, DataTypes.Bool, DataTypes.Byte,DataTypes.Byte,DataTypes.Identifier.array(),DataTypes.NBT,DataTypes.Identifier,DataTypes.Identifier,
                DataTypes.Long,DataTypes.VarInt,DataTypes.VarInt,DataTypes.VarInt,DataTypes.Bool,DataTypes.Bool,DataTypes.Bool,DataTypes.Bool,DataTypes.Bool,
                DataTypes.Identifier.encodeOptional(a->(Boolean)a[16]).encodeIfNonnull().decodeOptional(a->(Boolean)a[16]),DataTypes.LongLocation.encodeOptional(a->(Boolean)a[16]).encodeIfNonnull().decodeOptional(a->(Boolean)a[16])
        );
    }

    @Override
    public int packetId() {
        return 0x28;
    }
}
