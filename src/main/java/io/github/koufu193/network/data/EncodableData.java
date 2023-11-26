package io.github.koufu193.network.data;


/**
 * {@link T}クラスのオブジェクトをbyte[]にエンコードできることを示すインターフェース
 * @param <T> エンコード可能にするクラス
 */
public interface EncodableData<T>{
    /**
     * byte[]にエンコードする
     * @param value エンコード前のデータ
     * @return エンコードされた物
     */
    byte[] encode(T value);

    /**
     * エンコードしたときにbyte[]の長さがどうなるかを取得する
     * @param value エンコード後のサイズを取得したいオブジェクト
     * @return サイズ
     */
    int size(T value);
}
