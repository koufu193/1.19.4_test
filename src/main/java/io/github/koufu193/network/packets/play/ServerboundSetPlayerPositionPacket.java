package io.github.koufu193.network.packets.play;

import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;

public class ServerboundSetPlayerPositionPacket extends AbstractPacket {
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.Double,DataTypes.Double,DataTypes.Double,DataTypes.Bool);
    }
    public Location toLocation(){
        return new Location((Double)fields()[0],(Double)fields()[1],(Double)fields()[2]);
    }
    public boolean onGround(){
        return (Boolean)fields()[3];
    }

    @Override
    public int packetId() {
        return 0x14;
    }
}
