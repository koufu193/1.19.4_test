package io.github.koufu193.core.game.parser;

import io.github.koufu193.core.game.data.inventory.DragType;

public interface DragTypeParser {
    DragType parse(int mode,byte button,short slot);
}
