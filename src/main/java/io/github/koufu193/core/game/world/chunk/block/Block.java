package io.github.koufu193.core.game.world.chunk.block;

import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.core.game.data.block.BlockMeta;
import io.github.koufu193.core.game.world.chunk.block.converters.DefaultBlockMetaConverter;
import io.github.koufu193.core.game.world.chunk.block.converters.IBlockMetaConverter;
import io.github.koufu193.core.game.world.chunk.block.interfaces.IBlock;
import io.github.koufu193.core.game.data.Material;
import org.jglrxavpok.hephaistos.mca.BlockState;

public class Block implements IBlock {
    private static final IBlockMetaConverter<BlockMeta> CONVERTER=new DefaultBlockMetaConverter();
    protected Location location;
    protected Material type;
    protected BlockMeta meta;
    @Override
    public Location location() {
        return this.location.clone();
    }

    @Override
    public Material type() {
        return this.type;
    }

    @Override
    public BlockState convert() {
        return CONVERTER.convert(meta());
    }

    public Block(Location location, Material type){
        this.type=type;
        this.location=location.clone();
    }
    public void type(Material type){
        this.type=type;
        apply();
    }
    private void apply(){
        this.location.chunk().changeBlock(this);
    }
    public BlockMeta meta(){
        return this.meta;
    }
}
