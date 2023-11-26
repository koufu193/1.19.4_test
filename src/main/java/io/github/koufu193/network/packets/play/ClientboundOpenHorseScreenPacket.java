package io.github.koufu193.network.packets.play;

import io.github.koufu193.core.game.data.inventory.HorseInventory;
import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import org.jetbrains.annotations.NotNull;

public class ClientboundOpenHorseScreenPacket extends InventoryPacket{
    ClientboundOpenHorseScreenPacket(){}
    public ClientboundOpenHorseScreenPacket(byte windowId, @NotNull HorseInventory inventory){
        this(windowId,inventory.size(),inventory.entity().entityId());
    }
    public ClientboundOpenHorseScreenPacket(byte windowId,int slotCount,int entityId){
        this.fields(windowId,slotCount,entityId);
    }
    @NotNull
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.Byte,DataTypes.VarInt,DataTypes.Int);
    }

    @Override
    public int packetId() {
        return 0x20;
    }
}
