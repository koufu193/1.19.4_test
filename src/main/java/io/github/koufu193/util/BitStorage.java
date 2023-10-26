package io.github.koufu193.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.*;

public class BitStorage {
    private final long[] longs;
    private int index=0;

    public BitStorage(int bits) {
        this.longs=new long[bits/64+(bits%64==0?0:1)];
    }
    public BitStorage(long[] longs){
        this.longs=longs;
    }
    public void write(int bits, long value) {
        this.write(bits,value,index);
        index+=bits;
    }
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

    public long read(int bits) {
        long result=read(bits,index);
        index-=bits;
        return result;
    }
    public long readBack(int bits){
        return read(bits,index+=bits);
    }
    public long read(int bits,int startOffset){
        if(bits==0) return 0;
        long value=0;
        int startArrayOffset=(startOffset-bits)/64;
        int arrayOffset=(startOffset-bits)%64;
        long mask=(1L<<bits)-1;
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

    public long[] array() {
        return this.longs;
    }
    public byte[] byteArray(){
        return longsToBytes(this.array());
    }

    public void index(int index) {
        if(index<0||(longs.length*Long.SIZE)<=index) throw new IllegalArgumentException(String.format("index must be %d and %d",0,(longs.length*Long.SIZE-1)));
        this.index = index;
    }

    public int index() {
        return index;
    }
    private static byte[] longsToBytes(long[] longs){
        ByteBuffer buffer=ByteBuffer.allocate(longs.length*Long.BYTES).order(ByteOrder.BIG_ENDIAN);
        for (long aLong : longs) buffer.putLong(aLong);
        return buffer.array();
    }
}
