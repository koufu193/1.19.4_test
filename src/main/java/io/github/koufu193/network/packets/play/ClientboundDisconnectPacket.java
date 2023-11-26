package io.github.koufu193.network.packets.play;

import io.github.koufu193.core.game.data.component.TextComponent;
import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import org.jetbrains.annotations.NotNull;

public class ClientboundDisconnectPacket extends AbstractPacket {
    public ClientboundDisconnectPacket(){
        this(TextComponent.EMPTY);
    }
    public ClientboundDisconnectPacket(@NotNull TextComponent text){
        this.fields(text);
    }
    @NotNull
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.Chat);
    }

    @Override
    public int packetId() {
        return 0x1a;
    }
}
