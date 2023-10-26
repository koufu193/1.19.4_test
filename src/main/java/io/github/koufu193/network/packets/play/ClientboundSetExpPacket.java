package io.github.koufu193.network.packets.play;

import io.github.koufu193.core.game.entities.Player;
import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;

public class ClientboundSetExpPacket extends AbstractPacket {
    public ClientboundSetExpPacket(){
        this(0,0,0);
    }
    public ClientboundSetExpPacket(float progress,int total,int level){
        fields(progress,total,level);
    }
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.Float,DataTypes.VarInt,DataTypes.VarInt);
    }


    @Override
    public int packetId() {
        return 0x56;
    }
    public static ClientboundSetExpPacket from(Player player){
        return new ClientboundSetExpPacket(player.expProgress(),player.totalExpPoints(),player.expLevel());
    }
}
