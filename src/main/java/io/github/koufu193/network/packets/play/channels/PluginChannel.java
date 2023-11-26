package io.github.koufu193.network.packets.play.channels;

import io.github.koufu193.core.game.data.Identifier;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;

/**
 * プラグインチャネルのインターフェース
 */
public interface PluginChannel {
    /**
     * このプラグインチャネルのIDを取得
     * @return このプラグインチャネルのID
     */
    @NotNull
    Identifier channel();

    /**
     * データ部を取得
     * @return データ部
     */
    byte[] bytes();

    /**
     * {@link ByteBuffer}からデータ部を読み込む
     * @param buffer {@link ByteBuffer}化されているデータ部
     */
    void load(ByteBuffer buffer);
}
