package io.github.koufu193.core.game.commands.nodes;

import io.github.koufu193.core.game.commands.Command;
import io.github.koufu193.core.game.commands.CommandExecutor;
import io.github.koufu193.network.packets.play.ServerboundChatCommandPacket;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.function.BiConsumer;

public interface ICommandNode {
    ICommandNode child(String name);
    Collection<ICommandNode> children();
    ICommandNode redirect();
    String name();
    ICommandNode then(ICommandNode node);
    ICommandNode redirect(ICommandNode node);
    ICommandNode execute(BiConsumer<CommandExecutor, Command> executorConsumer);
    void execute( CommandExecutor executor,Command command);
    boolean executable();
}
