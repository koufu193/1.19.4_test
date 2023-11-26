package io.github.koufu193.network.packets;

import io.github.koufu193.network.PacketFormat;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * マイクラのパケットの抽象クラス
 */
public abstract class AbstractPacket {
    private Object[] fields;

    /**
     * このパケットのデータ部のフォーマットを取得
     * @return このパケットのフォーマット
     */
    @NotNull
    public abstract PacketFormat format();

    /**
     * このパケットのデータ部のを取得
     * @return データ部の内容 未設定なら null
     */
    @Nullable
    public Object[] fields() {
        return this.fields;
    }

    /**
     * このパケットのデータ部の内容を設定
     * @param fields 設定するデータすべて
     * @return 自身
     */
    @NotNull
    public AbstractPacket fields(@NotNull Object... fields) {
        this.fields = fields;
        return this;
    }

    /**
     * 送信時に用いられるパケットIDを取得
     * @return パケットID
     */
    public abstract int packetId();
}
