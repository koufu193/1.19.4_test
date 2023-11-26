package io.github.koufu193.util;

import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.*;

/**
 * ビット単位でデータを書き込むクラス
 */
public class BitStorage {
    private final long[] longs;
    private int index=0;

    /**
     * 引数に
     * @param bits サイズ(ビット)
     */
    public BitStorage(int bits) {
        this.longs=new long[bits/64+(bits%64==0?0:1)];
    }

    /**
     * @param longs 初期値
     */
    public BitStorage(@NotNull long[] longs){
        this.longs=longs;
    }

    /**
     * データを指定されたビット数分書き込みオフセットをビット数分加算する
     * @param bits 書き込むビット数
     * @param value 書き込む値
     */
    public void write(int bits, long value) {
        this.write(bits,value,index);
        index+=bits;
    }

    /**
     * データを指定されたビット数分書き込む
     * @param bits 書き込むビット数
     * @param value 書き込む値
     * @param startOffset 書き込む位置(ビット)
     */
    public void write(int bits,long value,int startOffset){
        if(bits==0) return;
        int startArrayOffset=startOffset/64;
        int arrayOffset=startOffset%64;
        long mask=(1L<<bits)-1;
        value&=mask;
        longs[startArrayOffset]&=~(mask<<arrayOffset);
        longs[startArrayOffset]|=value<<arrayOffset;
        if(64<arrayOffset+bits){
            int endArrayOffset=startArrayOffset+1;
            int remainingBits=bits-(64-arrayOffset);
            longs[endArrayOffset]&=~(mask>>>(bits-remainingBits));
            longs[endArrayOffset]|=value>>>(bits-remainingBits);
        }
        /*int startArrayOffset=startOffset/64;
        int arrayOffset=64-startOffset%64;
        value&=(1L<<bits)-1;
        if(bits<=arrayOffset){
            longs[startArrayOffset]&=~(((1L<<bits)-1)<<(arrayOffset-bits));
            longs[startArrayOffset]|=value<<(arrayOffset-bits);
        }else{
            longs[startArrayOffset]&= -(1L << arrayOffset);
            longs[startArrayOffset]|=value>>>(bits-arrayOffset);
            int endArrayOffset=startArrayOffset+1;
            int remainingBits=(bits-arrayOffset);
            longs[endArrayOffset]&=~(((1L<<remainingBits)-1)<<(64-remainingBits));
            longs[endArrayOffset]|=(value&((1L<<remainingBits)-1))<<(64-remainingBits);
        }*/
    }

    /**
     * データを指定されたビット数分読み、オフセットをビット数分加算する
     * @param bits 読むビット数
     * @return 読まれた値
     */
    public long read(int bits) {
        long result=read(bits,index);
        index+=bits;
        return result;
    }


    /**
     * データを指定されたビット数分読む
     * @param bits 読むビット数
     * @param startOffset 読む位置(ビット)
     * @return 読まれた値
     */
    public long read(int bits,int startOffset){
        if(bits==0) return 0;
        long value=0;
        int startArrayOffset=startOffset/64;
        int arrayOffset=startOffset%64;
        long mask=(1L<<bits)-1;
        if(bits==64) mask=-1L;
        value|=(longs[startArrayOffset]>>>arrayOffset)&mask;
        if(64<arrayOffset+bits){
            int endArrayOffset=startArrayOffset+1;
            int remainingBits=bits-(64-arrayOffset);
            long remainingBitsMask=(1L<<remainingBits)-1;
            value|=(longs[endArrayOffset]&remainingBitsMask)<<(64-arrayOffset);
            //System.out.println((longs[endArrayOffset]&remainingBitsMask)<<remainingBits);
        }
        return value;
        /*long value=0;
        int startArrayOffset=(startOffset-bits)/64;
        int arrayOffset=64-(startOffset-bits)%64;
        if(bits<=arrayOffset){
            value|=longs[startArrayOffset]&(((1L<<bits)-1)<<(arrayOffset-bits));
            return value>>>(arrayOffset-bits);
        }else{
            value|=(longs[startArrayOffset]&((1L<<arrayOffset)-1))<<(bits-arrayOffset);
            int endArrayOffset=startArrayOffset+1;
            int remainingBits=(bits-arrayOffset);
            value|=(longs[endArrayOffset]&((((1L<<remainingBits)-1))<<(64-remainingBits)))>>>(64-remainingBits);
            return value;
        }*/
    }

    /**
     * データ全体を取得する
     * 戻り値の要素を書き換えると、元々の値も書き変わります
     * @return ロング配列のデータ
     */
    public long[] array() {
        return this.longs;
    }

    /**
     * データ全体をバイト配列として返す(配列の長さは、ロングのバイト数の倍数)
     * 戻り値の要素を書き換えても、元々の値は書き変わりません
     * @return バイト配列のデータ
     */
    public byte[] byteArray(){
        return longsToBytes(this.array());
    }

    /**
     * 書き込み,読み寄りに使用されるオフセットを設定する
     * @param index オフセット(ビット)
     */
    public void index(int index) {
        if(index<0||(longs.length*Long.SIZE)<index) throw new IllegalArgumentException(String.format("index must be %d and %d",0,(longs.length*Long.SIZE)));
        this.index = index;
    }

    /**
     * 現在のオフセットを返す
     * @return 現在のオフセット
     */
    public int index() {
        return index;
    }
    private static byte[] longsToBytes(long[] longs){
        ByteBuffer buffer=ByteBuffer.allocate(longs.length*Long.BYTES).order(ByteOrder.BIG_ENDIAN);
        for (long aLong : longs) buffer.putLong(aLong);
        return buffer.array();
    }
}
