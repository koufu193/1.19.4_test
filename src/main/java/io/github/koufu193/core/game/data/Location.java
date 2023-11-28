package io.github.koufu193.core.game.data;

import io.github.koufu193.core.game.world.World;
import io.github.koufu193.core.game.world.chunk.Chunk;
import io.github.koufu193.core.game.world.chunk.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public record Location(@Nullable World world,double x,double y,double z,float yaw,float pitch){
    public Location(double x,double y,double z){
        this(null,x,y,z);
    }

    public Location(@Nullable World world,double x,double y,double z){
        this(world,x,y,z,0f,0f);
    }

    public Location(@Nullable World world,long position){
        this(world,(position>>38),(position<<52)>>52,(position<<26)>>26);
    }
    public Location(double x,double y,double z,float yaw,float pitch){
        this(null,x,y,z,yaw,pitch);
    }
    @NotNull

    public Location world(World world) {
        return new Location(world,x,y,z,yaw,pitch);
    }

    public int blockX(){
        return (int) Math.floor(x);
    }
    public int blockY(){
        return (int)Math.floor(y);
    }
    public int blockZ(){
        return (int)Math.floor(z);
    }
    public int chunkX(){
        return blockX()>>4;
    }
    public int chunkZ(){
        return blockZ()>>4;
    }
    @Nullable
    public Block getBlock(){
        if(world==null) return null;
        return chunk().getBlock(blockX(),blockY(),blockZ());
    }
    @Nullable
    public Chunk chunk(){
        if(this.world==null) return null;
        return world.chunk(chunkX(),chunkZ());
    }

    @NotNull
    public Location pitch(float pitch) {
        return new Location(world,x,y,z,yaw,pitch);
    }
    @NotNull

    public Location x(double x) {
        return new Location(world,x,y,z,yaw,pitch);
    }
    @NotNull

    public Location y(double y) {
        return new Location(world,x,y,z,yaw,pitch);
    }
    @NotNull

    public Location yaw(float yaw) {
        return new Location(world,x,y,z,yaw,pitch);
    }
    @NotNull
    public Location z(double z) {
        return new Location(world,x,y,z,yaw,pitch);
    }
    @NotNull
    public Location add(double x,double y,double z){
        return new Location(world,this.x+x,this.y+y,this.z+z,yaw,pitch);
    }
    public long toLong(){
        return ((long) (((int) x) & 0x3FFFFFF) << 38) | ((long) (((int) z) & 0x3FFFFFF) << 12) | (((int)y) & 0xFFF);
    }
}
