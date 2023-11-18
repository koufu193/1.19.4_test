package io.github.koufu193.exceptions;

public abstract class PacketException extends RuntimeException{
    public PacketException(){super();}
    public PacketException(String msg){super(msg);}
}
