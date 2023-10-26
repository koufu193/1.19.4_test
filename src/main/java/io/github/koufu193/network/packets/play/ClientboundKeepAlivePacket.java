package io.github.koufu193.network.packets.play;

import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;

public class ClientboundKeepAlivePacket extends AbstractPacket {
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.Long);
    }

    @Override
    public int packetId() {
        return 0x23;
    }
    public ClientboundKeepAlivePacket id(long id){
        return (ClientboundKeepAlivePacket)this.fields(id);
    }
}
