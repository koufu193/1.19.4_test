package io.github.koufu193.core.game.commands.nodes;

import io.github.koufu193.core.game.commands.Command;
import io.github.koufu193.core.game.commands.CommandExecutor;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public abstract class CommandNode implements ICommandNode{
    protected Map<String,ICommandNode> children;
    protected ICommandNode redirect;
    protected BiConsumer<CommandExecutor, Command> executorConsumer;
    public CommandNode(Map<String,ICommandNode> children, ICommandNode redirect, BiConsumer<CommandExecutor, Command> executorConsumer){
        this.children=new HashMap<>(children);
        this.redirect=redirect;
        this.executorConsumer=executorConsumer;
    }
    @Override
    public Collection<ICommandNode> children() {
        return this.children.values();
    }

    @Override
    public ICommandNode child(String name) {
        return this.children.get(name);
    }

    @Override
    public ICommandNode redirect() {
        return this.redirect;
    }

    @Override
    public boolean executable() {
        return this.executorConsumer!=null;
    }

    @Override
    public ICommandNode then(ICommandNode node) {
        this.children.put(node.name(),node);
        return this;
    }

    @Override
    public ICommandNode redirect(ICommandNode node) {
        this.redirect=node;
        return this;
    }

    @Override
    public ICommandNode execute(BiConsumer<CommandExecutor, Command> executorConsumer) {
        this.executorConsumer=executorConsumer;
        return this;
    }

    @Override
    public void execute(CommandExecutor executor,Command command) {
        executorConsumer.accept(executor,command);
    }
}
