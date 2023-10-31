package io.github.koufu193.core.game.commands.nodes;

import io.github.koufu193.util.StringCommandReader;
import org.jetbrains.annotations.NotNull;

public final class CommandParser {
    private final RootCommandNode root;
    public CommandParser(@NotNull String command,@NotNull RootCommandNode root){
        this(new StringCommandReader(command),root);
    }
    public CommandParser(@NotNull StringCommandReader reader,@NotNull RootCommandNode root){
        this.root=root;
    }
}
