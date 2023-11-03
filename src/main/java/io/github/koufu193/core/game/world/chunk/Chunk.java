package io.github.koufu193.core.game.world.chunk;

import io.github.koufu193.core.game.data.Identifier;
import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.core.game.world.World;
import io.github.koufu193.core.game.world.chunk.block.Block;
import io.github.koufu193.core.game.world.chunk.block.interfaces.IBlock;
import io.github.koufu193.core.game.data.Material;
import org.jglrxavpok.hephaistos.mca.BlockState;
import org.jglrxavpok.hephaistos.mca.ChunkColumn;

public class Chunk {
    private final ChunkColumn column;
    private final World world;
    public Chunk(World world,ChunkColumn column){
        this.column=column;
        this.world=world;
    }
    public IBlock block(int x,int y,int z){
        BlockState data=column.getBlockState(x,y,z);
        Material material=Material.fromId(data.component1());
        if(material==null) return null;
        return null;
        //return material.(new Location(this.world,x,y,z),data);
    }
    public void changeBlock(Block block){
        Location pos=block.location();
        column.setBlockState(pos.blockX(),pos.blockY(),pos.blockZ(),block.convert());
    }
    public int chunkX(){
        return this.column.getX();
    }
    public int chunkZ(){
        return this.column.getZ();
    }

    public ChunkColumn column() {
        return this.column;
    }
}
