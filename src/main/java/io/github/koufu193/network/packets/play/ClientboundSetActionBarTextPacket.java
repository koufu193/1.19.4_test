package io.github.koufu193.network.packets.play;

import io.github.koufu193.core.game.data.component.TextComponent;
import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import org.jetbrains.annotations.NotNull;

public class ClientboundSetActionBarTextPacket extends AbstractPacket {
    @NotNull
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.Chat);
    }
    public ClientboundSetActionBarTextPacket(){this(TextComponent.EMPTY);}
    public ClientboundSetActionBarTextPacket(@NotNull TextComponent component){
        fields(component);
    }
    @Override
    public int packetId() {
        return 0x46;
    }
}
