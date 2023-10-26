package io.github.koufu193.core.game.world.chunk.block;

import io.github.koufu193.core.game.data.Identifier;
import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.core.game.world.chunk.block.converters.DefaultBlockConverter;
import io.github.koufu193.core.game.world.chunk.block.converters.IBlockConverter;
import io.github.koufu193.core.game.world.chunk.block.interfaces.IBlock;
import io.github.koufu193.core.game.world.material.Material;
import org.jglrxavpok.hephaistos.mca.BlockState;

public class Block implements IBlock {
    private static final IBlockConverter<Block> CONVERTER=new DefaultBlockConverter();
    protected Location location;
    protected Material<?> type;
    @Override
    public Location location() {
        return this.location.clone();
    }

    @Override
    public Material<?> type() {
        return this.type;
    }

    @Override
    public BlockState convert() {
        return CONVERTER.convert(this);
    }

    public Block(Location location, Material<Block> type){
        this.type=type;
        this.location=location.clone();
    }
    public void type(Material<?> type){
        this.type=type;
        apply();
    }
    private void apply(){
        this.location.chunk().changeBlock(this);
    }
}
