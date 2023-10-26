package io.github.koufu193.network.packets.play;

import io.github.koufu193.core.game.data.component.TextComponent;
import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import org.jetbrains.annotations.NotNull;

public class ClientboundSetTabListHeaderAndFooterPacket extends AbstractPacket {
    public ClientboundSetTabListHeaderAndFooterPacket(){
        this(TextComponent.EMPTY,TextComponent.EMPTY);
    }
    public ClientboundSetTabListHeaderAndFooterPacket(@NotNull TextComponent header,@NotNull TextComponent footer){
        fields(header,footer);
    }
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.Chat,DataTypes.Chat);
    }

    @Override
    public int packetId() {
        return 0x65;
    }
}
