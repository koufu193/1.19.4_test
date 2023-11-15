package io.github.koufu193.network.packets.play;

import io.github.koufu193.core.game.data.component.TextComponent;
import io.github.koufu193.core.game.data.inventory.InventoryView;
import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import org.jetbrains.annotations.NotNull;

public class ClientboundOpenScreenPacket extends InventoryPacket{
    ClientboundOpenScreenPacket(){}
    public ClientboundOpenScreenPacket(byte windowId,@NotNull InventoryView view){
        this(windowId,view.inventory().type().typeId(),view.title());
    }
    public ClientboundOpenScreenPacket(int windowId, int windowType, @NotNull TextComponent title){
        this.fields(windowId,windowType,title);
    }
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.VarInt,DataTypes.VarInt,DataTypes.Chat);
    }

    @Override
    public int packetId() {
        return 0x30;
    }
}
