package io.github.koufu193.core.game.world.dimension;

import io.github.koufu193.core.game.data.Identifier;

public final class BuiltinDimensionTypes {
    public static final DimensionType OVERWORLD=new DimensionType(new Identifier("minecraft:overworld"));
    public static final DimensionType NETHER=new DimensionType(new Identifier("minecraft:the_nether"));
    public static final DimensionType END=new DimensionType(new Identifier("minecraft:the_end"));
}
