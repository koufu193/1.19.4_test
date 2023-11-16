package io.github.koufu193.core.game.parser;

import org.jetbrains.annotations.NotNull;

public interface Parsers {
    @NotNull
    ClickTypeParser clickTypeParser();
    @NotNull
    InventoryTypeParser inventoryTypeParser();
    @NotNull
    CommandArgumentIdParser commandArgumentIdParser();
    @NotNull
    DragTypeParser dragTypeParser();
}
