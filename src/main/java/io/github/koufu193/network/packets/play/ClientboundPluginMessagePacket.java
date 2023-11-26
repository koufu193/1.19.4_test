package io.github.koufu193.network.packets.play;

import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import io.github.koufu193.network.packets.play.channels.PluginChannel;
import org.jetbrains.annotations.NotNull;

public class ClientboundPluginMessagePacket extends AbstractPacket {
    public ClientboundPluginMessagePacket(@NotNull PluginChannel channel){
        fields(channel);
    }
    ClientboundPluginMessagePacket(){
        fields((Object) null);
    }
    @NotNull
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.PluginChannel);
    }

    @Override
    public int packetId() {
        return 0x17;
    }
}
