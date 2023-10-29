package io.github.koufu193.network.packets.play;

import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class ServerboundChatCommandPacket extends AbstractPacket {
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.String, DataTypes.Long, DataTypes.Long, new DataTypes.DataType<ArgumentSignature>() {
            @Override
            public byte[] encode(ArgumentSignature value) {
                ByteArrayOutputStream output=new ByteArrayOutputStream(this.size(value));
                output.writeBytes(DataTypes.String.encode(value.name()));
                output.writeBytes(value.signature());
                return output.toByteArray();
            }

            @Override
            public int size(ArgumentSignature value) {
                return DataTypes.String.size(value.name())+value.signature().length;
            }

            @Override
            public ArgumentSignature decode(ByteBuffer buffer){
                byte[] signature=new byte[256];
                String name=DataTypes.String.decode(buffer);
                buffer.get(signature);
                return new ArgumentSignature(name,signature);
            }
        }.array(),DataTypes.VarInt,DataTypes.BitSet);
    }

    @Override
    public int packetId() {
        return 0x04;
    }
    public String command(){
        return (String)fields()[0];
    }

    public record ArgumentSignature(String name, byte[] signature){
        public ArgumentSignature {
            if (signature.length != 256) throw new RuntimeException("signature size must be 256");
        }
    }
}
