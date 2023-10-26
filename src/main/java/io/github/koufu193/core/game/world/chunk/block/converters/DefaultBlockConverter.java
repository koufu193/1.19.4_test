package io.github.koufu193.core.game.world.chunk.block.converters;

import io.github.koufu193.core.game.data.Identifier;
import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.core.game.world.chunk.block.Block;
import io.github.koufu193.core.game.world.chunk.block.interfaces.IBlock;
import io.github.koufu193.core.game.world.material.Material;
import org.jglrxavpok.hephaistos.mca.BlockState;

import java.util.HashMap;

public class DefaultBlockConverter implements IBlockConverter<Block> {
    private final HashMap<Identifier,BlockState> cache=new HashMap<>();
    @Override
    public Block convert(Location location, BlockState state, Material<Block> material) {
        return new Block(location, material);
    }

    @Override
    public BlockState convert(Block block) {
        return cache.computeIfAbsent(block.type().id(),a->new BlockState(a.toString(),new HashMap<>()));
    }
}
