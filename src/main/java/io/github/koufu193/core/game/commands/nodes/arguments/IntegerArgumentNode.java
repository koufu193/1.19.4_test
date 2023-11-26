package io.github.koufu193.core.game.commands.nodes.arguments;

import io.github.koufu193.core.game.commands.Command;
import io.github.koufu193.core.game.commands.CommandExecutor;
import io.github.koufu193.core.game.commands.nodes.ICommandNode;
import io.github.koufu193.core.game.data.Identifier;
import io.github.koufu193.exceptions.CommandException;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.util.StringCommandReader;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class IntegerArgumentNode extends ArgumentCommandNode<Integer>{
    private final int min;
    private final int max;
    public IntegerArgumentNode(String name,int max,int min, Map<String, ICommandNode> children, ICommandNode redirect, BiConsumer<CommandExecutor, Command> executorConsumer) {
        super(name, children, redirect, executorConsumer);
        if(max<min) throw new IllegalArgumentException(String.format("max:%d<min:%d",max,min));
        this.max=max;
        this.min=min;
    }


    @Override
    public Integer parse(StringCommandReader reader) {
        int value=reader.readInt();
        if(value<this.min||this.max<value) throw new CommandException(String.format("value must be between %d and %d:actual %d",this.min,this.max,value));
        return value;
    }

    @NotNull
    @Override
    public Identifier parserId() {
        return new Identifier("brigadier:integer");
    }

    @Override
    public byte[] properties() {
        ByteArrayOutputStream output=new ByteArrayOutputStream(this.size());
        output.write(this.flag());
        if(this.needToSendMinValue()) output.writeBytes(DataTypes.Int.encode(this.min));
        if(this.needToSendMaxValue()) output.writeBytes(DataTypes.Int.encode(this.max));
        return output.toByteArray();
    }
    private boolean needToSendMaxValue(){
        return this.max!=Integer.MAX_VALUE;
    }
    private boolean needToSendMinValue(){
        return this.min!=Integer.MIN_VALUE;
    }
    private byte flag(){
        byte flag=0;
        if(this.needToSendMinValue()) flag|=0x01;
        if(this.needToSendMaxValue()) flag|=0x02;
        return flag;
    }
    private int size(){
        int size=1;
        if(this.needToSendMinValue()) size+=Integer.BYTES;
        if(this.needToSendMaxValue()) size+=Integer.BYTES;
        return size;
    }
    public int max() {
        return this.max;
    }

    public int min() {
        return this.min;
    }

    @Override
    public Identifier suggestionType() {
        return null;
    }
    public IntegerArgumentNode then(Function<IntegerArgumentNode,ICommandNode> function){
        this.then(function.apply(this));
        return this;
    }
    public static IntegerArgumentNode integer(@NotNull String name,int min,int max){
        return new IntegerArgumentNode(name,max,min,new HashMap<>(),null,null);
    }
    public static IntegerArgumentNode max(@NotNull String name,int max){
        return integer(name,Integer.MIN_VALUE,max);
    }
    public static IntegerArgumentNode min(@NotNull String name,int min){
        return integer(name,min,Integer.MAX_VALUE);
    }
    public static IntegerArgumentNode integer(@NotNull String name){
        return integer(name,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }

    @Override
    public boolean isValidInput(@NotNull StringCommandReader reader) {
        return reader.read().matches("^[+\\-]?\\d+$");
    }
}
