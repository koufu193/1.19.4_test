package io.github.koufu193.network.packets.play;

import io.github.koufu193.core.game.entities.Player;
import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;

public class ServerboundClientInformationPacket extends AbstractPacket {
    @Override
    public PacketFormat format() {
        return PacketFormat.of(
                DataTypes.String,
                DataTypes.Byte,
                DataTypes.VarInt,
                DataTypes.Bool,
                DataTypes.Byte,
                DataTypes.VarInt,
                DataTypes.Bool,
                DataTypes.Bool
        );
    }

    @Override
    public int packetId() {
        return 0x08;
    }
    public Player.Property property(){
        return new Player.Property(
                (String)fields()[0],
                (Byte)fields()[1],
                Player.ChatMode.values()[(Integer)fields()[2]],
                (Boolean)fields()[3],
                (Byte)fields()[4],
                Player.MainHand.values()[(Integer)fields()[5]],
                (Boolean)fields()[6],
                (Boolean)fields()[7]
        );
    }
}
