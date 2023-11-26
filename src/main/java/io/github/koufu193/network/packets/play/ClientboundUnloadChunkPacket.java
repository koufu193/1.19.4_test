package io.github.koufu193.network.packets.play;

import io.github.koufu193.core.game.world.chunk.Chunk;
import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import org.jetbrains.annotations.NotNull;

public class ClientboundUnloadChunkPacket extends AbstractPacket {
    ClientboundUnloadChunkPacket(){}
    public ClientboundUnloadChunkPacket(int chunkX,int chunkZ){
        fields(chunkX,chunkZ);
    }
    public ClientboundUnloadChunkPacket(@NotNull Chunk chunk){
        this(chunk.chunkX(),chunk.chunkZ());
    }
    @NotNull
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.Int,DataTypes.Int);
    }

    @Override
    public int packetId() {
        return 0x1e;
    }
}
