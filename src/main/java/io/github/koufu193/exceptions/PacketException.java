package io.github.koufu193.exceptions;

/**
 * パケット例外ぼ抽象クラス
 */
public abstract class PacketException extends RuntimeException{
    public PacketException(){super();}
    public PacketException(String msg){super(msg);}
}
