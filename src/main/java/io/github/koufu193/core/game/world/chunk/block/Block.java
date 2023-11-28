package io.github.koufu193.core.game.world.chunk.block;

import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.core.game.data.block.BlockMeta;
import io.github.koufu193.core.game.world.chunk.block.converters.DefaultBlockMetaConverter;
import io.github.koufu193.core.game.world.chunk.block.converters.IBlockMetaConverter;
import io.github.koufu193.core.game.world.chunk.block.interfaces.IBlock;
import io.github.koufu193.core.game.data.Material;
import org.jetbrains.annotations.NotNull;
import org.jglrxavpok.hephaistos.mca.BlockState;
import org.jglrxavpok.hephaistos.nbt.NBTCompound;
import org.jglrxavpok.hephaistos.nbt.NBTCompoundLike;

public class Block implements IBlock {
    protected Location location;
    protected Material type;
    protected BlockMeta meta;
    @NotNull
    @Override
    public Location location() {
        return this.location;
    }

    @NotNull
    @Override
    public Material type() {
        return this.type;
    }

    public Block(@NotNull Location location, @NotNull Material type, @NotNull NBTCompound nbt){
        this.type=type;
        this.location=location;
        this.meta=new BlockMeta() {
            @Override
            public Material material() {
                return type;
            }

            @Override
            public NBTCompound toCompound() {
                return nbt;
            }
        };
    }
    public Block(@NotNull Location location,@NotNull BlockMeta meta){
        this.location=location;
        this.type=meta.material();
        this.meta=meta;
    }
    public void type(Material type){
        this.type=type;
    }
    public BlockMeta meta(){
        return this.meta;
    }

    @NotNull
    @Override
    public NBTCompound nbt() {
        return meta.toCompound();
    }
    public void nbt(@NotNull NBTCompound nbt){
        Material material=this.meta.material();
        this.meta=new BlockMeta() {
            @Override
            public Material material() {
                return material;
            }

            @Override
            public NBTCompound toCompound() {
                return nbt;
            }
        };
    }
}
