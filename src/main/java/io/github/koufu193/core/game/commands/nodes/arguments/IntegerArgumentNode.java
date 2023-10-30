package io.github.koufu193.core.game.commands.nodes.arguments;

import io.github.koufu193.core.game.commands.Command;
import io.github.koufu193.core.game.commands.CommandExecutor;
import io.github.koufu193.core.game.commands.nodes.ICommandNode;
import io.github.koufu193.core.game.commands.nodes.LiteralCommandNode;
import io.github.koufu193.core.game.data.Identifier;
import io.github.koufu193.network.data.DataTypes;
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
    public Integer parse(String argument) {
        return Integer.parseInt(argument);
    }

    @NotNull
    @Override
    public Identifier parserId() {
        return new Identifier("brigadier:integer");
    }

    @Override
    public byte[] properties() {
        byte flag=0;
        flag|=(this.min!=Integer.MIN_VALUE?0x01:0x00);
        flag|=(this.max!=Integer.MAX_VALUE?0x02:0x00);
        int printInts=(this.min!=Integer.MIN_VALUE?1:0)+(this.min!=Integer.MAX_VALUE?1:0);
        ByteArrayOutputStream output=new ByteArrayOutputStream(1+printInts*Integer.BYTES);
        output.write(flag);
        if(this.min!=Integer.MIN_VALUE) output.writeBytes(DataTypes.Int.encode(this.min));
        if(this.max!=Integer.MAX_VALUE) output.writeBytes(DataTypes.Int.encode(this.max));
        return output.toByteArray();
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
}
