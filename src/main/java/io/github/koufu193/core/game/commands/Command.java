package io.github.koufu193.core.game.commands;

public interface Command {
    Object args(int index);
    Object args(String name);
    Command redirect();
}
