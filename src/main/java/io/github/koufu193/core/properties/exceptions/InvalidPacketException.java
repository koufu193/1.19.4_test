package io.github.koufu193.core.properties.exceptions;

import io.github.koufu193.network.packets.AbstractPacket;

public class InvalidPacketException extends PacketException{
    private final AbstractPacket packet;
    public InvalidPacketException(AbstractPacket packet){
        super();
        this.packet=packet;
    }
    public InvalidPacketException(AbstractPacket packet,String msg){
        super(msg);
        this.packet=packet;
    }

    public AbstractPacket packet() {
        return packet;
    }
}
