package io.github.koufu193.network.packets.play;

import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import org.jetbrains.annotations.NotNull;

public class ServerboundSetPlayerPositionAndRotationPacket extends ServerboundMovementPacket {
    @NotNull
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.Double, DataTypes.Double,DataTypes.Double,DataTypes.Float,DataTypes.Float,DataTypes.Bool);
    }

    @Override
    public int packetId() {
        return 0x15;
    }
    public Location toLocation(){
        return new Location((Double)fields()[0],(Double)fields()[1],(Double)fields()[2],(Float)fields()[3],(Float)fields()[4]);
    }

    @Override
    public Location toLocation(@NotNull Location defaultValue) {
        return this.toLocation().world(defaultValue.world());
    }

    public boolean onGround(){
        return (Boolean)fields()[5];
    }
}
