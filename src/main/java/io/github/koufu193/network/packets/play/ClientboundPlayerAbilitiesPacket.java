package io.github.koufu193.network.packets.play;

import io.github.koufu193.core.game.entities.Player;
import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import org.jetbrains.annotations.NotNull;

public class ClientboundPlayerAbilitiesPacket extends AbstractPacket {
    public ClientboundPlayerAbilitiesPacket(@NotNull Player.PlayerAbilities abilities){
        fields(
                (byte)((abilities.instabuild()?1:0)<<3| (abilities.mayFly()?1:0)<<2| (abilities.flying()?1:0)<<1| (abilities.invulnerable()?1:0)),
                abilities.flySpeed(),
                abilities.walkSpeed()
        );
    }
    ClientboundPlayerAbilitiesPacket(){
        fields(0,1f,1f);
    }
    @NotNull
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.Byte,DataTypes.Float,DataTypes.Float);
    }

    @Override
    public int packetId() {
        return 0x34;
    }
    public Player.PlayerAbilities toAbilities(){
        byte flag=(byte)fields()[0];
        return new Player.PlayerAbilities().flying((flag&0x02)!=0).mayFly((flag&0x04)!=0).instabuild((flag&0x08)!=0).invulnerable((flag&0x01)!=0)
                .flySpeed((float)fields()[1])
                .walkSpeed((float)fields()[2]);
    }
}
