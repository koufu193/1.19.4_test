package io.github.koufu193.core.game.world.chunk.block.converters;

import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.core.game.world.chunk.block.interfaces.IBlock;
import io.github.koufu193.core.game.world.material.Material;
import org.jglrxavpok.hephaistos.mca.BlockState;

public interface IBlockConverter<T extends IBlock>{
    T convert(Location location, BlockState state, Material<T> material);
    BlockState convert(T block);
}
