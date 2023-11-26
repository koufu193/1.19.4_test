package io.github.koufu193.network;

import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.*;

/**
 * パケットをバイト配列にエンコードするクラス
 */
public class PacketEncoder {
    private static final PacketEncoder encoder=new PacketEncoder();
    private int compressionSize=-1;

    /**
     * パケットを長さ込みでバイト配列にエンコードする
     * @param packet エンコードするパケット
     * @return パケットをエンコードしたもの
     */
    public byte[] encode(@NotNull AbstractPacket packet){
        byte[] data=encodeWithoutLength(packet);
        ByteArrayOutputStream packetOutput=new ByteArrayOutputStream(data.length+1);
        if(0<=this.compressionSize) {
            //compressed
            if(this.compressionSize<=data.length) {
                byte[] compressedData = compress(data);
                packetOutput.writeBytes(DataTypes.VarInt.encode(DataTypes.VarInt.size(data.length) + compressedData.length));
                packetOutput.writeBytes(DataTypes.VarInt.encode(data.length));
                packetOutput.writeBytes(compressedData);
            }else{
                packetOutput.writeBytes(DataTypes.VarInt.encode(data.length+1));
                packetOutput.writeBytes(DataTypes.VarInt.encode(0));
                packetOutput.writeBytes(data);
            }
        }else{
            //not compressed
            packetOutput.writeBytes(DataTypes.VarInt.encode(data.length));
            packetOutput.writeBytes(data);
        }
        return packetOutput.toByteArray();
    }
    private byte[] compress(byte[] data){
        ByteArrayOutputStream output=new ByteArrayOutputStream();
        try(DeflaterOutputStream deflater=new DeflaterOutputStream(output)){
            deflater.write(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return output.toByteArray();
    }

    /**
     * パケットの長さを入れずにパケットをバイト配列にエンコードする
     * @param packet エンコードするパケット
     * @return パケットをエンコードしたもの
     */
    public byte[] encodeWithoutLength(@NotNull AbstractPacket packet) {
        Object[] fields=packet.fields();
        List<DataTypes.DataType<?>> format=packet.format().asUnmodifiableList();
        if(fields==null) throw new IllegalArgumentException("packet is not initialized");
        ByteArrayOutputStream output=new ByteArrayOutputStream(calculatePacketSize(packet));
        output.writeBytes(DataTypes.VarInt.encode(packet.packetId()));
        for(int i=0;i<format.size();i++){
            output.writeBytes(format.get(i).encodeObj(fields[i],fields));
        }
        return output.toByteArray();
    }

    /**
     * 圧縮する最低サイズを設定する -1は圧縮しない
     * @param size 圧縮する最低サイズ
     */
    public void setCompressionSize(int size){
        if(size<0) size=-1;
        this.compressionSize=size;
    }

    /**
     * 圧縮する最低サイズを取得する
     * @return 圧縮する最低サイズ -1は圧縮しない
     */
    public int getCompressionSize() {
        return this.compressionSize;
    }

    public static PacketEncoder getDefaultEncoder(){
        return encoder;
    }
    private static int calculatePacketSize(@NotNull AbstractPacket packet){
        int size=DataTypes.VarInt.size(packet.packetId());
        List<DataTypes.DataType<?>> format=packet.format().asUnmodifiableList();
        for(int i=0;i<format.size();i++){
            size+=format.get(i).sizeObj(packet.fields()[i],packet.fields());
        }
        return size;
    }
}
