package io.github.koufu193.network.packets.play.channels;

import io.github.koufu193.core.game.data.Identifier;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * レジスターチャネル
 */
public class RegisterChannel implements PluginChannel {
    private String[] values;
    RegisterChannel(){}

    /**
     * @param values 値
     */
    public RegisterChannel(@NotNull String[] values){
        this.values=values;
    }
    @NotNull
    @Override
    public Identifier channel() {
        return new Identifier("minecraft:register");
    }

    @Override
    public byte[] bytes() {
        ByteArrayOutputStream output=new ByteArrayOutputStream(calculateDataSize(values()));
        for(String str:values()){
            output.writeBytes(str.getBytes(StandardCharsets.UTF_8));
            output.write(0);
        }
        return output.toByteArray();
    }

    /**
     * データ部を取得
     * @return データ部
     */
    public String[] values() {
        return Arrays.copyOf(this.values,this.values.length);
    }


    @Override
    public void load(ByteBuffer buffer) {
        List<String> list=new ArrayList<>();
        while (!buffer.hasRemaining()){
            list.add(readString(buffer));
        }
        this.values=list.toArray(String[]::new);
    }
    private String readString(ByteBuffer buffer){
        StringBuilder builder=new StringBuilder();
        int ch;
        while ((ch=buffer.get())!=0){
            builder.append((char)ch);
        }
        return buffer.toString();
    }
    private int calculateDataSize(String[] values){
        int count=values.length;
        for(String str:values){
            count+=str.length();
        }
        return count;
    }
}
