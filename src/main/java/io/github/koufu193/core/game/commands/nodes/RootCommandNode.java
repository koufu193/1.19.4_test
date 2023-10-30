package io.github.koufu193.core.game.commands.nodes;

import io.github.koufu193.core.game.commands.Command;
import io.github.koufu193.core.game.commands.CommandExecutor;
import io.github.koufu193.core.game.commands.nodes.arguments.ArgumentCommandNode;
import io.github.koufu193.network.packets.play.ServerboundChatCommandPacket;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class RootCommandNode extends CommandNode {
    public static final RootCommandNode EMPTY = new RootCommandNode(Collections.emptyMap(), null) {
        @Override
        public RootCommandNode then(Function<RootCommandNode, ICommandNode> function) {
            return this;
        }

        @Override
        public RootCommandNode then(ICommandNode node) {
            return this;
        }
    };

    public RootCommandNode(Map<String, ICommandNode> children, ICommandNode redirect) {
        super(children, redirect, null);
    }

    @Override
    public String name() {
        return "";
    }

    public static RootCommandNode root() {
        return new RootCommandNode(new HashMap<>(), null);
    }

    @Override
    public RootCommandNode redirect(ICommandNode node) {
        return this;
    }

    @Override
    public RootCommandNode execute(BiConsumer<CommandExecutor, Command> executorConsumer) {
        return this;
    }


    @Override
    public RootCommandNode then(ICommandNode node) {
        if (node instanceof ArgumentCommandNode<?>)
            throw new IllegalArgumentException("Argument Node cannot be root's child");
        super.then(node);
        return this;
    }

    public RootCommandNode then(Function<RootCommandNode, ICommandNode> function) {
        return this.then(function.apply(this));
    }
}
