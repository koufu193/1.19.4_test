package io.github.koufu193.network;

import io.github.koufu193.network.data.DataTypes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class PacketFormat {
    private final List<DataTypes.DataType<?>> format=new ArrayList<>();
    public PacketFormat(DataTypes.DataType<?>... data){
        this.add(data);
    }
    public PacketFormat add(DataTypes.DataType<?>... data){
        for(DataTypes.DataType<?> obj:data) if(obj==null) throw new NullPointerException("the data has null");
        format.addAll(List.of(data));
        return this;
    }
    public static PacketFormat of(DataTypes.DataType<?>... data){
        return new PacketFormat(data);
    }
    public static PacketFormat ofNull(){return new PacketFormat();}

    public List<DataTypes.DataType<?>> getFormat() {
        return Collections.unmodifiableList(this.format);
    }
}
