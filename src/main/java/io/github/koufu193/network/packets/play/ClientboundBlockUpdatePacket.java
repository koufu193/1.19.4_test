package io.github.koufu193.network.packets.play;

import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.core.game.data.Material;
import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import org.jetbrains.annotations.NotNull;

public class ClientboundBlockUpdatePacket extends AbstractPacket {
    ClientboundBlockUpdatePacket(){}
    public ClientboundBlockUpdatePacket(@NotNull Location location,int blockId){
        fields(location,blockId);
    }
    public ClientboundBlockUpdatePacket(@NotNull Location location, @NotNull Material type){
        this(location, type.blockId());
    }
    @NotNull
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.Position,DataTypes.VarInt);
    }

    @Override
    public int packetId() {
        return 0x0a;
    }
}
