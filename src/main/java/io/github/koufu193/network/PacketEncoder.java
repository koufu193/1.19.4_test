package io.github.koufu193.network;

import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import io.github.koufu193.network.packets.login.LoginPackets;
import io.github.koufu193.network.packets.play.PlayPackets;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.zip.*;

public class PacketEncoder {
    private static final PacketEncoder encoder=new PacketEncoder();
    private int compressionSize=-1;
    public byte[] encode(AbstractPacket packet) throws IOException {
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
    public byte[] encodeWithoutLength(AbstractPacket packet) throws IOException{
        ByteArrayOutputStream output=new ByteArrayOutputStream();
        output.write(DataTypes.VarInt.encode(packet.packetId()));
        List<DataTypes.DataType<?>> format=packet.format().getFormat();
        for(int i=0;i<format.size();i++){
            output.write(format.get(i).encodeObj(packet.fields()[i],packet.fields()));
        }
        return output.toByteArray();
    }
    public void setCompressionSize(int size){
        if(size<0) size=-1;
        this.compressionSize=size;
    }

    public int getCompressionSize() {
        return this.compressionSize;
    }

    public static PacketEncoder getEncoder(){
        return encoder;
    }
}
