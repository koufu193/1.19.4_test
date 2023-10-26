package io.github.koufu193.network.packets.play;

import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import org.jetbrains.annotations.NotNull;

public class ClientboundSynchronizePlayerPositionPacket extends AbstractPacket {
    private final int teleportId;
    public ClientboundSynchronizePlayerPositionPacket(@NotNull Location location){
        fields(location.x(),location.y(),location.z(),location.yaw(),location.pitch(),(byte)0,(teleportId=(int)(Math.random()*100)));
    }
    ClientboundSynchronizePlayerPositionPacket(){this.teleportId=0;}
    public int teleportId() {
        return this.teleportId;
    }

    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.Double, DataTypes.Double, DataTypes.Double, DataTypes.Float,DataTypes.Float,DataTypes.Byte,DataTypes.VarInt);
    }

    @Override
    public int packetId() {
        return 0x3c;
    }
}
