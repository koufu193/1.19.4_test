package io.github.koufu193.core.game.commands.nodes;

import io.github.koufu193.core.game.commands.Command;
import io.github.koufu193.core.game.commands.CommandExecutor;

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
    public LiteralCommandNode then(ICommandNode node) {
        super.then(node);
        return this;
    }
    public LiteralCommandNode then(Function<LiteralCommandNode,ICommandNode> function){
        return this.then(function.apply(this));
    }

    @Override
    public LiteralCommandNode execute(BiConsumer<CommandExecutor, Command> executorConsumer) {
        super.execute(executorConsumer);
        return this;
    }

    @Override
    public LiteralCommandNode redirect(ICommandNode node) {
        super.redirect(node);
        return this;
    }

    public static LiteralCommandNode literal(String literal){
        if(literal.contains(" ")) throw new IllegalArgumentException("literal must not include spaces");
        return new LiteralCommandNode(literal,new HashMap<>(),null,null);
    }
}
