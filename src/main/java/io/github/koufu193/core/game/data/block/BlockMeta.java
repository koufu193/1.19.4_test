package io.github.koufu193.core.game.data.block;

import io.github.koufu193.core.game.data.Material;
import io.github.koufu193.util.ConvertibleToNBTCompound;

public interface BlockMeta extends ConvertibleToNBTCompound {
    Material material();
}
