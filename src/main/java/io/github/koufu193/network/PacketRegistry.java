package io.github.koufu193.network;

import io.github.koufu193.exceptions.UndefinedPacketIdException;
import io.github.koufu193.network.packets.AbstractPacket;

/**
 * パケットレジストリ
 */
public interface PacketRegistry {
    /**
     * クライアントに対して送られるパケットIDからパケットオブジェクトを生成
     * @param id パケットID
     * @return 生成されたパケットオブジェクト
     * @throws UndefinedPacketIdException 引数のパケットIDに対応するパケットが存在しなかった場合
     */
    AbstractPacket getClientboundPacket(int id);
    /**
     * サーバーに対して送られるパケットIDからパケットオブジェクトを生成
     * @param id パケットID
     * @return 生成されたパケットオブジェクト
     * @throws UndefinedPacketIdException 引数のパケットIDに対応するパケットが存在しなかった場合
     */
    AbstractPacket getServerboundPacket(int id);
}
