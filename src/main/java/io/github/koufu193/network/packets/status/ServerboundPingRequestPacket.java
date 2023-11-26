package io.github.koufu193.network.packets.status;

import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import org.jetbrains.annotations.NotNull;

/**
 * クライアントからピング要求が出されたことを示すクラス
 */
public class ServerboundPingRequestPacket extends AbstractPacket {
    @NotNull
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.Long);
    }

    /**
     * ペイロードを取得
     * @return ペイロード
     */
    public long payload(){
        return (long)fields()[0];
    }

    @Override
    public int packetId() {
        return 0x01;
    }
}
