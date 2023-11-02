package io.github.koufu193.core.game.world.chunk.block.converters;

import io.github.koufu193.core.game.data.block.BlockMeta;
import org.jglrxavpok.hephaistos.mca.BlockState;

public interface IBlockMetaConverter<T extends BlockMeta>{
    T convert(BlockState state);
    BlockState convert(T block);
}
