package io.github.koufu193.core.game.parser;

import io.github.koufu193.core.game.data.Identifier;
import org.jetbrains.annotations.NotNull;

public interface CommandArgumentIdParser {
    int parse(@NotNull Identifier argumentId);
}
