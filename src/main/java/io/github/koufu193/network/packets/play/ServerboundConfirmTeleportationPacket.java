package io.github.koufu193.network.packets.play;

import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;

public class ServerboundConfirmTeleportationPacket extends AbstractPacket {
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.VarInt);
    }
    public int teleportId(){
        return (Integer)fields()[0];
    }
    @Override
    public int packetId() {
        return 0x00;
    }
}
