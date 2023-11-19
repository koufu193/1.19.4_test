package io.github.koufu193.network.packets.play;

import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.core.game.entities.Entity;
import io.github.koufu193.core.game.entities.interfaces.IEntity;
import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import org.jetbrains.annotations.NotNull;

public class ClientboundUpdateEntityPositionPacket extends AbstractPacket {
    ClientboundUpdateEntityPositionPacket(){}
    public ClientboundUpdateEntityPositionPacket(@NotNull IEntity entity, Location before){
        this(entity.entityId(),before,entity.location(),entity.onGround());
    }
    public ClientboundUpdateEntityPositionPacket(int entityId, @NotNull Location before,@NotNull Location after,boolean onGround){
        this(entityId,
                calculateDeltaOrThrow(before.x(),after.x()),
                calculateDeltaOrThrow(before.y(),after.y()),
                calculateDeltaOrThrow(before.z(),after.z())
                ,onGround);
    }
    public ClientboundUpdateEntityPositionPacket(int entityId,short deltaX,short deltaY,short deltaZ,boolean onGround){
        fields(entityId,deltaX,deltaY,deltaZ,onGround);
    }
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.VarInt,DataTypes.Short,DataTypes.Short,DataTypes.Short,DataTypes.Bool);
    }

    @Override
    public int packetId() {
        return 0x2b;
    }
    private static short calculateDeltaOrThrow(double before,double after){
        if(8<Math.abs(Math.abs(before)-Math.abs(after))) throw new IllegalArgumentException("too long distance");
        return (short) ((after*32 - before * 32) * 128);
    }
}
