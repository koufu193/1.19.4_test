package io.github.koufu193.network.packets.play;

import io.github.koufu193.core.game.data.Angle;
import io.github.koufu193.core.game.entities.Entity;
import io.github.koufu193.core.game.entities.interfaces.IEntity;
import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import org.jetbrains.annotations.NotNull;

public class ClientboundUpdateEntityRotationPacket extends AbstractPacket {
    ClientboundUpdateEntityRotationPacket(){}
    public ClientboundUpdateEntityRotationPacket(@NotNull IEntity entity){
        this(entity.entityId(),entity.location().yaw(),entity.location().pitch(),entity.onGround());
    }
    public ClientboundUpdateEntityRotationPacket(int entityId, float yaw, float pitch, boolean onGround){
        this(entityId,new Angle(yaw),new Angle(pitch),onGround);
    }
    public ClientboundUpdateEntityRotationPacket(int entityId, @NotNull Angle yaw, @NotNull Angle pitch, boolean onGround){
        fields(entityId,yaw,pitch,onGround);
    }
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.VarInt,DataTypes.Angle,DataTypes.Angle,DataTypes.Bool);
    }

    @Override
    public int packetId() {
        return 0x2d;
    }
}
