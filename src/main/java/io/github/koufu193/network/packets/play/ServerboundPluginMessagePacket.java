package io.github.koufu193.network.packets.play;

import io.github.koufu193.core.game.data.Identifier;
import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import io.github.koufu193.network.packets.play.channels.IPluginChannel;
import io.github.koufu193.network.packets.play.channels.PluginChannels;

import java.nio.ByteBuffer;

public class ServerboundPluginMessagePacket extends AbstractPacket {
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.PluginChannel);
    }

    @Override
    public int packetId() {
        return 0x0d;
    }

    public IPluginChannel channel() {
        return (IPluginChannel) this.fields()[0];
    }
}
