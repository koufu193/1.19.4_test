package io.github.koufu193.core.game.commands.nodes.arguments;

import io.github.koufu193.core.game.commands.Command;
import io.github.koufu193.core.game.commands.CommandExecutor;
import io.github.koufu193.core.game.commands.nodes.ICommandNode;
import io.github.koufu193.core.game.data.Identifier;
import io.github.koufu193.network.data.DataTypes;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class StringArgumentNode extends ArgumentCommandNode<String> {
    private final StringType type;

    @Override
    public String parse(String argument) {
        return null;
    }

    @NotNull
    @Override
    public Identifier parserId() {
        return new Identifier("brigadier:string");
    }

    @Override
    public byte[] properties() {
        return DataTypes.VarInt.encode(this.type.ordinal());
    }

    @Override
    public Identifier suggestionType() {
        return null;
    }
    public StringType type(){
        return this.type;
    }
    @Override
    public StringArgumentNode then(ICommandNode node) {
        if(this.type==StringType.GREEDY_PHRASE) throw new IllegalStateException("GREEDY PHRASE cannot use then");
        super.then(node);
        return this;
    }
    public StringArgumentNode then(Function<StringArgumentNode,ICommandNode> function){
        return this.then(function.apply(this));
    }


    public StringArgumentNode(String name, StringType type, Map<String, ICommandNode> children, ICommandNode redirect, BiConsumer<CommandExecutor, Command> executorConsumer) {
        super(name,children, redirect, executorConsumer);
        this.type=type;
    }
    public static StringArgumentNode string(@NotNull String name, @NotNull StringType type){
        return new StringArgumentNode(name,type,new HashMap<>(),null,null);
    }

    public enum StringType {
        SINGLE_WORD,QUOTABLE_PHRASE,GREEDY_PHRASE
    }
}
