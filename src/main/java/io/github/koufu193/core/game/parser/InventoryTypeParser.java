package io.github.koufu193.core.game.parser;

import io.github.koufu193.core.game.data.inventory.InventoryType;
import org.jetbrains.annotations.NotNull;

public interface InventoryTypeParser {
    int parse(@NotNull InventoryType type);
}
