package io.github.koufu193.network.packets;

import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;

public class ServerboundSetPlayerRotationPacket extends AbstractPacket{
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.Float,DataTypes.Float,DataTypes.Bool);
    }
    public float yaw(){
        return (Float)fields()[0];
    }
    public float pitch(){
        return (Float)fields()[1];
    }
    public boolean onGround(){
        return (Boolean)fields()[2];
    }

    @Override
    public int packetId() {
        return 0x14;
    }
}
