package io.github.koufu193.network;

import io.github.koufu193.network.data.DataTypes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * マイクラのパケットのデータ部のフォーマットを示すクラス
 */
public final class PacketFormat {
    private final List<DataTypes.DataType<?>> format=new ArrayList<>();
    
    private PacketFormat(DataTypes.DataType<?>... data){
        this.add(data);
    }

    /**
     *
     * @param data フォーマットに追加したいデータのフォーマットの配列
     * @return 自身
     */
    public PacketFormat add(DataTypes.DataType<?>... data){
        for(DataTypes.DataType<?> obj:data) if(obj==null) throw new NullPointerException("the data has null");
        format.addAll(List.of(data));
        return this;
    }
    /**
     * {@link PacketFormat}を取得
     * @param data 初期値
     */
    public static PacketFormat of(DataTypes.DataType<?>... data){
        return new PacketFormat(data);
    }

    /**
     * 初期値の数が0の{@link PacketFormat}を取得
     */
    public static PacketFormat ofNull(){return new PacketFormat();}

    /**
     * 変更不可能なリストとして取得
     */

    public List<DataTypes.DataType<?>> asUnmodifiableList() {
        return Collections.unmodifiableList(this.format);
    }
}
