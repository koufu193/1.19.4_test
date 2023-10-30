package io.github.koufu193.network.packets.play;

import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import org.jetbrains.annotations.NotNull;

public class ServerboundSetPlayerRotationPacket extends ServerboundMovementPacket {
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.Float,DataTypes.Float,DataTypes.Bool);
    }

    @Override
    public int packetId() {
        return 0x16;
    }

    @Override
    public Location toLocation(@NotNull Location defaultValue) {
        return new Location(defaultValue.world(),defaultValue.x(),defaultValue.y(),defaultValue.z(),yaw(),pitch());
    }
    public float yaw(){
        return (Float)fields()[0];
    }
    public float pitch(){
        return (Float)fields()[1];
    }

    @Override
    public boolean onGround() {
        return (Boolean)fields()[2];
    }
}
