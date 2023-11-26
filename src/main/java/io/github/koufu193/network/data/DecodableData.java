package io.github.koufu193.network.data;

import java.nio.ByteBuffer;

/**
 * 渡された{@link ByteBuffer}を{@link T}のオブジェクトにデコードできることを示すインターフェース
 * @param <T> デコード可能にするクラス
 */
public interface DecodableData<T>{
    /**
     * {@link T}のオブジェクトにデコードする
     * @param buffer デコード前のデータ
     * @return デコードされた物
     */
    T decode(ByteBuffer buffer);
}
