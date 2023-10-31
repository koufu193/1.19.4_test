package io.github.koufu193.core.game.commands.nodes.arguments;

import io.github.koufu193.core.game.commands.Command;
import io.github.koufu193.core.game.commands.CommandExecutor;
import io.github.koufu193.core.game.commands.nodes.CommandNode;
import io.github.koufu193.core.game.commands.nodes.ICommandNode;
import io.github.koufu193.core.game.data.Identifier;
import io.github.koufu193.util.StringCommandReader;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

public abstract class ArgumentCommandNode<Argument> extends CommandNode {
    public abstract Argument parse(StringCommandReader reader);
    public abstract @NotNull Identifier parserId();
    public abstract byte[] properties();
    public abstract Identifier suggestionType();
    private final String name;
    public ArgumentCommandNode(String name, Map<String, ICommandNode> children, ICommandNode redirect, BiConsumer<CommandExecutor, Command> executorConsumer) {
        super(children, redirect,executorConsumer);
        this.name=name;
    }
    @Override
    public String name() {
        return this.name;
    }

    @Override
    public boolean isValidInput(@NotNull StringCommandReader reader) {
        try{
            parse(reader);
            return true;
        }catch (Throwable throwable){
            return false;
        }
    }
}
