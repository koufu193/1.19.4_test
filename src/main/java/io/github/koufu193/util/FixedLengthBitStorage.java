package io.github.koufu193.util;

import org.jetbrains.annotations.NotNull;

/**
 * ビット単位でデータを書き込むクラス
 */
public class FixedLengthBitStorage {
    private final int bitsPerValue;
    private final BitStorage storage;
    private final int valuesPerLong;
    public FixedLengthBitStorage(int bitsPerValue, int bits){
        this(bitsPerValue,new BitStorage(bits));
    }
    public FixedLengthBitStorage(int bitsPerValue, @NotNull long[] longs){
        this(bitsPerValue,new BitStorage(longs));
    }
    public FixedLengthBitStorage(int bitsPerValue, @NotNull BitStorage storage){
        this.bitsPerValue = bitsPerValue;
        this.storage=storage;
        this.valuesPerLong=64/bitsPerValue;
    }
    public long read(){
        long value=this.storage.read(bitsPerValue);
        long offset=this.storage.index();
        if(offset/64!=(offset+bitsPerValue)/64){
            this.storage.index(this.storage.index()+(64-valuesPerLong*bitsPerValue));
        }
        return value;
    }
    public void write(long value){
        this.storage.write(bitsPerValue,value);
        long offset=this.storage.index();
        if(offset/64!=(offset+bitsPerValue)/64){
            this.storage.index(this.storage.index()+(64-valuesPerLong*bitsPerValue));
        }
    }
    public long[] array(){
        return this.storage.array();
    }
    public byte[] byteArray(){
        return this.storage.byteArray();
    }
    public void resetPosition(){
        this.storage.index(0);
    }
}
