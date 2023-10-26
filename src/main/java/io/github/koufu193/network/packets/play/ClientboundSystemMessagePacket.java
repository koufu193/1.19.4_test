package io.github.koufu193.network.packets.play;

import io.github.koufu193.core.game.data.component.TextComponent;
import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import net.minecraft.network.chat.ChatMessageType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ClientboundSystemMessagePacket extends AbstractPacket {
    public ClientboundSystemMessagePacket(@NotNull TextComponent component){
        this(component,false);
    }
    public ClientboundSystemMessagePacket(@NotNull TextComponent component,boolean onActionBar){
        fields(component,onActionBar);
    }
    public ClientboundSystemMessagePacket(){this(TextComponent.EMPTY);}
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.Chat, DataTypes.Bool);
    }

    @Override
    public int packetId() {
        return 0x64;
    }
}
