package io.github.koufu193.core.game.parser;

import io.github.koufu193.core.game.data.inventory.ClickType;

public interface ClickTypeParser {
    ClickType parse(int mode,byte button,short slot);
}
