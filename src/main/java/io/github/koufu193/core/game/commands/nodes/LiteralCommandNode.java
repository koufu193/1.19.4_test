package io.github.koufu193.core.game.commands.nodes;

import io.github.koufu193.core.game.commands.Command;
import io.github.koufu193.core.game.commands.CommandExecutor;
import io.github.koufu193.util.StringCommandReader;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class LiteralCommandNode extends CommandNode{
    private final String literal;
    public LiteralCommandNode(String literal, Map<String, ICommandNode> children, ICommandNode redirect, BiConsumer<CommandExecutor, Command> executorConsumer) {
        super(children, redirect,executorConsumer);
        this.literal=literal;
    }

    public String literal() {
        return literal;
    }

    @Override
    public String name() {
        return this.literal();
    }

    @Override
    public boolean isValidInput(@NotNull StringCommandReader reader) {
        return this.literal.equals(reader.read());
    }

    public LiteralCommandNode then(Function<LiteralCommandNode,ICommandNode> function){
        this.then(function.apply(this));
        return this;
    }


    public static LiteralCommandNode literal(String literal){
        if(literal.contains(" ")) throw new IllegalArgumentException("literal must not include spaces");
        return new LiteralCommandNode(literal,new HashMap<>(),null,null);
    }
}
