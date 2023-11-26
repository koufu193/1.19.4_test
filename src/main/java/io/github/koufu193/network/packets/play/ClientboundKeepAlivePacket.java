package io.github.koufu193.network.packets.play;

import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import org.jetbrains.annotations.NotNull;

public class ClientboundKeepAlivePacket extends AbstractPacket {
    public ClientboundKeepAlivePacket(){
        this(0);
    }
    public ClientboundKeepAlivePacket(long id){
        this.fields(id);
    }
    @NotNull
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.Long);
    }
    public long id(){
        return (long) this.fields()[0];
    }

    @Override
    public int packetId() {
        return 0x23;
    }
}
