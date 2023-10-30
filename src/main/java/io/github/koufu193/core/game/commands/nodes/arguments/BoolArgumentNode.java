package io.github.koufu193.core.game.commands.nodes.arguments;

import io.github.koufu193.core.game.commands.Command;
import io.github.koufu193.core.game.commands.CommandExecutor;
import io.github.koufu193.core.game.commands.nodes.ICommandNode;
import io.github.koufu193.core.game.data.Identifier;
import org.checkerframework.checker.units.qual.N;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class BoolArgumentNode extends ArgumentCommandNode<Boolean>{
    public BoolArgumentNode(String name, Map<String, ICommandNode> children, ICommandNode redirect, BiConsumer<CommandExecutor, Command> executorConsumer) {
        super(name, children, redirect, executorConsumer);
    }

    @Override
    public Boolean parse(String argument) {
        return null;
    }

    @NotNull
    @Override
    public Identifier parserId() {
        return new Identifier("brigadier:bool");
    }

    @Override
    public byte[] properties() {
        return new byte[0];
    }

    @Override
    public Identifier suggestionType() {
        return null;
    }
    public static BoolArgumentNode bool(@NotNull String name){
        return new BoolArgumentNode(name,new HashMap<>(),null,null);
    }
}
