package io.github.koufu193.network;

import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.zip.InflaterOutputStream;

/**
 * バイト配列をパケットにデコードするクラス
 */
public class PacketDecoder {
    private static final int SEGMENT_BITS = 0x7f;
    private static final int CONTINUE_BIT = 0x80;
    private static final PacketDecoder decoder=new PacketDecoder();
    private static final int timeout=10;
    private int compressionSize=-1;

    /**
     * バイト配列をパケットにデコードする
     * @param channel バイト配列の読み取り元
     * @param packets 今の状態に応じたパケットクラス
     * @return デコードされたパケット
     * @throws ExecutionException エラー発生時
     * @throws InterruptedException 割り込み処理が発生したとき
     * @throws TimeoutException タイムアウト時
     */
    public AbstractPacket decode(@NotNull AsynchronousSocketChannel channel,@NotNull PacketRegistry packets) throws ExecutionException, InterruptedException, TimeoutException {
        int length=decodeVarInt(channel);
        ByteBuffer buffer=ByteBuffer.allocate(length);
        channel.read(buffer).get(timeout,TimeUnit.SECONDS);
        buffer.flip();
        if(0<=this.compressionSize) buffer= uncompress(buffer);
        return decodeWithoutLength(buffer,packets);
    }
    private ByteBuffer uncompress(@NotNull ByteBuffer buffer){
        if(DataTypes.VarInt.decode(buffer)==0) return buffer;
        ByteArrayOutputStream output=new ByteArrayOutputStream();
        try(InflaterOutputStream inflater=new InflaterOutputStream(output)){
            inflater.write(buffer.array(),buffer.position(),buffer.remaining());
            return ByteBuffer.wrap(output.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private AbstractPacket decodeWithoutLength(@NotNull ByteBuffer buffer,@NotNull PacketRegistry packets){
        AbstractPacket packet = packets.getServerboundPacket(DataTypes.VarInt.decode(buffer));
        if(packet==null) return null;
        List<DataTypes.DataType<?>> format=packet.format().asUnmodifiableList();
        Object[] fields=new Object[format.size()];
        for(int i=0;i<fields.length;i++){
            fields[i]=format.get(i).decode(buffer,fields);
        }
        packet.fields(fields);
        return packet;
    }

    private int decodeVarInt(@NotNull AsynchronousSocketChannel channel) throws ExecutionException, InterruptedException, TimeoutException {
        int value = 0;
        int position = 0;
        byte currentByte;
        while (true) {
            ByteBuffer buffer=ByteBuffer.wrap(new byte[1]);
            channel.read(buffer).get(10,TimeUnit.SECONDS);
            currentByte = buffer.array()[0];
            value |= (currentByte & SEGMENT_BITS) << position;
            if ((currentByte & CONTINUE_BIT) == 0) break;
            position += 7;
            if (32 <= position) throw new RuntimeException("VarInt is too big");
        }
        return value;
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
    public static PacketDecoder getDefaultDecoder(){
        return decoder;
    }
}
