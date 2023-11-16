package io.github.koufu193.core.game.parser.v1194;

import io.github.koufu193.core.game.parser.*;
import org.jetbrains.annotations.NotNull;

public class V1194Parsers implements Parsers {
    private static final Parsers PARSERS=new V1194Parsers();
    @NotNull
    @Override
    public ClickTypeParser clickTypeParser() {
        return V1194ClickTypeParser.getParser();
    }

    @NotNull
    @Override
    public InventoryTypeParser inventoryTypeParser() {
        return V1194InventoryTypeParser.getParser();
    }

    @NotNull
    @Override
    public CommandArgumentIdParser commandArgumentIdParser() {
        return V1194CommandArgumentIdParser.getParser();
    }

    @NotNull
    @Override
    public DragTypeParser dragTypeParser() {
        return V1194DragTypeParser.getParser();
    }
    public static Parsers getParsers(){
        return PARSERS;
    }

}
