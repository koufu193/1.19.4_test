package io.github.koufu193.network.packets.play;

import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import org.jetbrains.annotations.NotNull;

public class ClientboundSetChunkCenterPacket extends AbstractPacket {
    public ClientboundSetChunkCenterPacket(int chunkX,int chunkZ){
        fields(chunkX,chunkZ);
    }
    ClientboundSetChunkCenterPacket(){this(0,0);}
    @NotNull
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.VarInt,DataTypes.VarInt);
    }

    @Override
    public int packetId() {
        return 0x4e;
    }
}
