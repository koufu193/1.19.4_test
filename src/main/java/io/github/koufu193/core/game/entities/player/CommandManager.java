package io.github.koufu193.core.game.entities.player;

import io.github.koufu193.core.game.commands.nodes.RootCommandNode;
import io.github.koufu193.core.game.entities.interfaces.IPlayer;
import org.jetbrains.annotations.NotNull;

public class CommandManager {
    private final IPlayer player;
    private RootCommandNode root;
    public CommandManager(@NotNull IPlayer player,@NotNull RootCommandNode root){
        this.player=player;
        this.root=root;
    }
    public CommandManager(@NotNull IPlayer player){
        this(player,RootCommandNode.root());
    }

    public IPlayer player() {
        return player;
    }

    public RootCommandNode root() {
        return root;
    }

    public void root(@NotNull RootCommandNode root) {
        this.root = root;
    }
}
