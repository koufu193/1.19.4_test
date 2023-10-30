package io.github.koufu193.network.packets.play;

import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import org.jetbrains.annotations.NotNull;

public class ServerboundSetPlayerPositionPacket extends ServerboundMovementPacket {
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.Double,DataTypes.Double,DataTypes.Double,DataTypes.Bool);
    }
    public Location toLocation(@NotNull Location defaultValue){
        return new Location(defaultValue.world(),(Double)fields()[0],(Double)fields()[1],(Double)fields()[2],defaultValue.yaw(),defaultValue.pitch());
    }
    public boolean onGround(){
        return (Boolean)fields()[3];
    }

    @Override
    public int packetId() {
        return 0x14;
    }
}
