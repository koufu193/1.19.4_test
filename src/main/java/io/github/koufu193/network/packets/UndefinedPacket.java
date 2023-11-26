package io.github.koufu193.network.packets;

import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import org.jetbrains.annotations.NotNull;

public class UndefinedPacket extends AbstractPacket {
    private byte[] bytes;
    private final int packetId;
    public UndefinedPacket(int packetId, @NotNull byte[] bytes){
        this.bytes=bytes;
        this.packetId=packetId;
    }
    public UndefinedPacket(int packetId){
        this(packetId,new byte[0]);
    }
    @NotNull
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.ByteArray);
    }
    public byte[] data(){
        return this.bytes;
    }

    @NotNull
    @Override
    public AbstractPacket fields(@NotNull Object... fields) {
        this.bytes=(byte[])fields[0];
        return this;
    }

    @Override
    public int packetId() {
        return this.packetId;
    }
}
