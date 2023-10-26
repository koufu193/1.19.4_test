package io.github.koufu193.network.packets;

import io.github.koufu193.network.PacketFormat;

public abstract class AbstractPacket {
    private Object[] fields;
    public abstract PacketFormat format();

    public Object[] fields() {
        return fields;
    }
    public AbstractPacket fields(Object... fields) {
        this.fields = fields;
        return this;
    }
    public abstract int packetId();
}
