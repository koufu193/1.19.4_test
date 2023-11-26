package io.github.koufu193.network.packets.play;

import io.github.koufu193.core.game.world.chunk.Chunk;
import io.github.koufu193.core.game.world.chunk.LightData;
import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import org.jetbrains.annotations.NotNull;
import org.jglrxavpok.hephaistos.mca.ChunkColumn;

public class ClientboundUpdateLightPacket extends AbstractPacket {
    public ClientboundUpdateLightPacket(@NotNull LightData light){
        fields(light.chunkX(),light.chunkZ(),true,light);
    }
    public ClientboundUpdateLightPacket(@NotNull Chunk chunk){
        this(LightData.from(chunk));
    }
    ClientboundUpdateLightPacket(){}
    @NotNull
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.VarInt,DataTypes.VarInt,DataTypes.Bool,DataTypes.Light);
    }

    @Override
    public int packetId() {
        return 0x27;
    }
}
