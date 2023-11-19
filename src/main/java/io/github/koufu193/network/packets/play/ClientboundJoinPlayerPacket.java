package io.github.koufu193.network.packets.play;

import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.core.game.entities.interfaces.IPlayer;
import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class ClientboundJoinPlayerPacket extends AbstractPacket {
    ClientboundJoinPlayerPacket(){}
    public ClientboundJoinPlayerPacket(int entityId, @NotNull UUID uuid, @NotNull Location location){
        this.fields(entityId,uuid,location.clone());
    }
    public ClientboundJoinPlayerPacket(@NotNull IPlayer player){
        this(player.entityId(),player.uniqueId(),player.location());
    }
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.VarInt, DataTypes.UUID, DataTypes.Location);
    }

    @Override
    public int packetId() {
        return 0x03;
    }
}
