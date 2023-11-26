package io.github.koufu193.network.packets.play;

import io.github.koufu193.core.game.data.Angle;
import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.core.game.entities.interfaces.IEntity;
import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import org.checkerframework.checker.units.qual.A;
import org.jetbrains.annotations.NotNull;

public class ClientboundUpdateEntityPositionAndRotationPacket extends AbstractPacket {
    ClientboundUpdateEntityPositionAndRotationPacket(){}
    public ClientboundUpdateEntityPositionAndRotationPacket(@NotNull IEntity entity,Location before){
        this(entity.entityId(),before,entity.location(),entity.onGround());
    }
    public ClientboundUpdateEntityPositionAndRotationPacket(int entityId, Location before,Location after,boolean onGround){
        this(entityId,
                calculateDeltaOrThrow(before.x(),after.x()),
                calculateDeltaOrThrow(before.y(),after.y()),
                calculateDeltaOrThrow(before.z(),after.z()),
                after.yaw(),after.pitch(),onGround
                );
    }
    public ClientboundUpdateEntityPositionAndRotationPacket(int entityId, short deltaX, short deltaY, short deltaZ, Angle yaw, Angle pitch,boolean onGround){
        fields(entityId,deltaX,deltaY,deltaZ,yaw,pitch,onGround);
    }
    public ClientboundUpdateEntityPositionAndRotationPacket(int entityId, short deltaX, short deltaY, short deltaZ, float yaw, float pitch,boolean onGround){
        this(entityId,deltaX,deltaY,deltaZ,new Angle(yaw),new Angle(pitch),onGround);
    }
    @NotNull
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.VarInt,DataTypes.Short,DataTypes.Short,DataTypes.Short,DataTypes.Angle,DataTypes.Angle,DataTypes.Bool);
    }

    @Override
    public int packetId() {
        return 0x2c;
    }
    private static short calculateDeltaOrThrow(double after,double before){
        if(8<Math.abs(Math.abs(before)-Math.abs(after))) throw new IllegalArgumentException("too long distance");
        return (short) ((before * 32 - after * 32) * 128);
    }
}
