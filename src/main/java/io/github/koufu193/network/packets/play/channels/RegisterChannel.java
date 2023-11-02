package io.github.koufu193.network.packets.play.channels;

import io.github.koufu193.core.game.data.Identifier;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegisterChannel implements IPluginChannel{
    private String[] values;
    @Override
    public Identifier channel() {
        return new Identifier("minecraft:register");
    }

    @Override
    public byte[] bytes() {
        ByteArrayOutputStream output=new ByteArrayOutputStream(countBytes(values()));
        for(String str:values()){
            output.writeBytes(str.getBytes(StandardCharsets.UTF_8));
            output.write(0);
        }
        return output.toByteArray();
    }

    public String[] values() {
        return Arrays.copyOf(this.values,this.values.length);
    }

    public RegisterChannel values(String[] values) {
        this.values = values;
        return this;
    }

    @Override
    public void load(ByteBuffer buffer) {
        List<String> list=new ArrayList<>();
        while (!buffer.hasRemaining()){
            list.add(readString(buffer));
        }
        values(list.toArray(String[]::new));
    }
    private String readString(ByteBuffer buffer){
        StringBuilder builder=new StringBuilder();
        int ch=0;
        while ((ch=buffer.get())!=0){
            builder.append((char)ch);
        }
        return buffer.toString();
    }
    private int countBytes(String[] values){
        int count=values.length;
        for(String str:values){
            count+=str.length();
        }
        return count;
    }
}
