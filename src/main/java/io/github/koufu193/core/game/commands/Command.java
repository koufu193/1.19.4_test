package io.github.koufu193.core.game.commands;

import io.github.koufu193.core.game.commands.nodes.ICommandNode;
import io.github.koufu193.core.game.commands.nodes.LiteralCommandNode;
import io.github.koufu193.core.game.commands.nodes.arguments.ArgumentCommandNode;
import io.github.koufu193.exceptions.CommandException;
import io.github.koufu193.util.StringCommandReader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public interface Command {
    String command();

    Object args(int index);

    Object args(@NotNull String name);

    Command redirect();

    String rawCommand();

    ICommandNode node();
    void execute(@NotNull CommandExecutor executor);

    static Command parse(@NotNull String command, @NotNull ICommandNode root) {
        return parse(new StringCommandReader(command), root);
    }

    static Command parse(@NotNull StringCommandReader reader, @NotNull ICommandNode root) {
        try {
            ICommandNode node = root;
            ICommandNode redirect = null;
            int startOffset = reader.offset();
            Map<String, Object> argsMap = new HashMap<>();
            List<Object> args = new ArrayList<>();
            for (ICommandNode n = getValidNode(reader, node); n != null; n = getValidNode(reader, n)) {
                if (n instanceof LiteralCommandNode) {
                    args.add(reader.read());
                } else {
                    Object obj = ((ArgumentCommandNode<?>) n).parse(reader);
                    argsMap.put(n.name(), obj);
                    args.add(obj);
                }
                node = n;
                if (node.redirect() != null && getValidNode(reader, node.redirect()) != null) {
                    redirect = node.redirect();
                    break;
                }
            }
            if (redirect == null && reader.canRead()) {
                throw new IllegalStateException(String.format("Invalid command:%s", reader.rawCommand()));
            }
            if (redirect == null && !node.executable()) {
                throw new IllegalStateException(String.format("Not executable command:%s", reader.rawCommand()));
            }
            return makeCommand(reader.read(startOffset, Math.min(reader.size(), reader.offset())), args, argsMap, (redirect != null) ? parse(reader, redirect) : null, node);
        }catch (CommandException e){
            throw e;
        }catch (Throwable throwable){
            throw new CommandException(String.format("error:%s<-:%s",reader.read(0, Math.min(reader.size(), reader.offset())),throwable.getMessage()));
        }
    }

    private static Command makeCommand(@NotNull String rawCommand, @NotNull List<Object> argsList, @NotNull Map<String, Object> argsMap, @Nullable Command redirect, @NotNull ICommandNode node) {
        String command = (String) argsList.get(0);
        return new Command() {
            @Override
            public String command() {
                return command;
            }

            @Override
            public Object args(int index) {
                return argsList.get(index);
            }

            @Override
            public Object args(@NotNull String name) {
                return argsMap.get(name);
            }

            @Override
            public Command redirect() {
                return redirect;
            }

            @Override
            public String rawCommand() {
                return rawCommand;
            }

            @Override
            public ICommandNode node() {
                return node;
            }

            @Override
            public void execute(@NotNull CommandExecutor executor) {
                this.node().execute(executor,this);
            }
        };
    }

    private static ICommandNode getValidNode(@NotNull StringCommandReader reader, @NotNull ICommandNode node) {
        int offset = reader.offset();
        for (ICommandNode child : node.children()) {
            if (child.isValidInput(reader)) {
                reader.offset(offset);
                return child;
            }
            reader.offset(offset);
        }
        return null;
    }
}
