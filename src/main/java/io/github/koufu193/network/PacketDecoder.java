package io.github.koufu193.network;

import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;

import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.zip.InflaterOutputStream;

public class PacketDecoder {
    private static final int SEGMENT_BITS = 0x7f;
    private static final int CONTINUE_BIT = 0x80;
    private static final PacketDecoder decoder=new PacketDecoder();
    private static final int timeout=10;
    private int compressionSize=-1;
    public AbstractPacket decode(AsynchronousSocketChannel channel, IPackets packets) throws ExecutionException, InterruptedException, TimeoutException {
        int length=decodeVarInt(channel);
        ByteBuffer buffer=ByteBuffer.allocate(length);
        channel.read(buffer).get(timeout,TimeUnit.SECONDS);
        buffer.flip();
        if(0<=this.compressionSize) buffer= uncompress(buffer);
        return decodeWithoutLength(buffer,packets);
    }
    public AbstractPacket decode(InputStream input, IPackets packets) throws IOException{
        int length=decodeVarInt(input);
        ByteBuffer buffer=ByteBuffer.wrap(input.readNBytes(length));
        if(0<=this.compressionSize) buffer=uncompress(buffer);
        return decodeWithoutLength(buffer,packets);
    }
    private ByteBuffer uncompress(ByteBuffer buffer){
        if(DataTypes.VarInt.decode(buffer)==0) return buffer;
        ByteArrayOutputStream output=new ByteArrayOutputStream();
        try(InflaterOutputStream inflater=new InflaterOutputStream(output)){
            inflater.write(buffer.array(),buffer.position(),buffer.remaining());
            return ByteBuffer.wrap(output.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private AbstractPacket decodeWithoutLength(ByteBuffer buffer,IPackets packets){
        AbstractPacket packet = packets.getServerboundPacket(DataTypes.VarInt.decode(buffer));
        if(packet==null) return null;
        List<DataTypes.DataType<?>> format=packet.format().getFormat();
        Object[] fields=new Object[format.size()];
        for(int i=0;i<fields.length;i++){
            fields[i]=format.get(i).decode(buffer,fields);
        }
        packet.fields(fields);
        return packet;
    }
    private int decodeVarInt(InputStream input) throws IOException {
        int value = 0;
        int position = 0;
        int currentInt;
        byte currentByte;
        while (true) {
            currentInt=input.read();
            if(currentInt<0) throw new EOFException();
            currentByte = (byte)currentInt;
            value |= (currentByte & SEGMENT_BITS) << position;
            if ((currentByte & CONTINUE_BIT) == 0) break;
            position += 7;
            if (32 <= position) throw new RuntimeException("VarInt is too big");
        }
        return value;
    }
    private int decodeVarInt(AsynchronousSocketChannel channel) throws ExecutionException, InterruptedException, TimeoutException {
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
    public void setCompressionSize(int size){
        if(size<0) size=-1;
        this.compressionSize=size;
    }

    public int getCompressionSize() {
        return this.compressionSize;
    }
    public static PacketDecoder getDecoder(){
        return decoder;
    }
}
