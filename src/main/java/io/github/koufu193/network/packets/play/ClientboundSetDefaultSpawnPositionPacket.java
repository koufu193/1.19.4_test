package io.github.koufu193.network.packets.play;

import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import org.jetbrains.annotations.NotNull;

public class ClientboundSetDefaultSpawnPositionPacket extends AbstractPacket {
    ClientboundSetDefaultSpawnPositionPacket(){}
    public ClientboundSetDefaultSpawnPositionPacket(@NotNull Location location,float angle){
        this.fields(location,angle);
    }
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.LongLocation, DataTypes.Float);
    }

    @Override
    public int packetId() {
        return 0x50;
    }
}
