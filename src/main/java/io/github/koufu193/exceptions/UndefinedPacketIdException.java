package io.github.koufu193.exceptions;

/**
 * 未宣言のパケットが見つかったときに投げられる例外
 */
public class UndefinedPacketIdException extends PacketException{
    public UndefinedPacketIdException(){super();}
    public UndefinedPacketIdException(String s){super(s);}
}
