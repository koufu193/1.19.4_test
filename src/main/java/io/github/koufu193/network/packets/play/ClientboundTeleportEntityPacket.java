package io.github.koufu193.network.packets.play;

import io.github.koufu193.core.game.data.Angle;
import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.core.game.entities.interfaces.IEntity;
import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class ClientboundTeleportEntityPacket extends AbstractPacket {
    ClientboundTeleportEntityPacket(){}
    public ClientboundTeleportEntityPacket(@NotNull IEntity entity){
        this(entity.entityId(),entity.location(),entity.onGround());
    }
    public ClientboundTeleportEntityPacket(int entityId, @NotNull Location location,boolean onGround){
        this(entityId,location.x(),location.y(),location.z(),location.yaw(),location.pitch(), onGround);
    }
    public ClientboundTeleportEntityPacket(int entityId, double x, double y, double z, float yaw,float pitch,boolean onGround){
        this(entityId,x,y,z,new Angle(yaw),new Angle(pitch),onGround);
    }
    public ClientboundTeleportEntityPacket(int entityId, double x, double y, double z, @NotNull Angle yaw,@NotNull Angle pitch,boolean onGround){
        fields(entityId,x,y,z,yaw,pitch,onGround);
    }
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.VarInt,DataTypes.Double,DataTypes.Double,DataTypes.Double,DataTypes.Angle,DataTypes.Angle,DataTypes.Bool);
    }

    @Override
    public int packetId() {
        return 0x68;
    }
}
