package io.github.koufu193.network.packets.login;

import io.github.koufu193.core.game.data.GameProfile;
import io.github.koufu193.core.properties.Properties;
import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;

import java.util.UUID;

public class ServerboundLoginStartPacket extends AbstractPacket {
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.String, DataTypes.Bool.encodeOptional(a-> a[2] !=null),DataTypes.UUID.decodeOptional(a->(Boolean)a[1]).encodeIfNonnull());
    }

    @Override
    public int packetId() {
        return 0;
    }
    public GameProfile toProfile(){
        String name= (String) fields()[0];
        UUID uuid= (UUID) fields()[2];
        if(uuid==null) uuid=UUID.randomUUID();
        return new GameProfile(uuid,name, Properties.EMPTY);
    }
}
