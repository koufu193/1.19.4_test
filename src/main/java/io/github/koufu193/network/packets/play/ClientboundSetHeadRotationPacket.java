package io.github.koufu193.network.packets.play;

import io.github.koufu193.core.game.data.Angle;
import io.github.koufu193.core.game.entities.interfaces.IEntity;
import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ClientboundSetHeadRotationPacket extends AbstractPacket {
    ClientboundSetHeadRotationPacket(){}
    public ClientboundSetHeadRotationPacket(@NotNull IEntity entity){
        this(entity.entityId(),entity.location().yaw());
    }
    public ClientboundSetHeadRotationPacket(int entityId,float yaw){
        this(entityId,new Angle(yaw));
    }
    public ClientboundSetHeadRotationPacket(int entityId, @NotNull Angle yaw){
        fields(entityId,yaw);
    }
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.VarInt,DataTypes.Angle);
    }

    @Override
    public int packetId() {
        return 0x42;
    }
}
