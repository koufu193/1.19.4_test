package io.github.koufu193.core.game.world.chunk;

import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.core.game.data.Material;
import io.github.koufu193.core.game.data.block.BlockMeta;
import io.github.koufu193.core.game.world.World;
import io.github.koufu193.core.game.world.chunk.block.Block;
import io.github.koufu193.util.BitUtils;
import io.github.koufu193.util.FixedLengthBitStorage;
import org.jetbrains.annotations.NotNull;
import org.jglrxavpok.hephaistos.collections.ImmutableByteArray;
import org.jglrxavpok.hephaistos.nbt.NBTCompound;
import org.jglrxavpok.hephaistos.nbt.NBTCompoundLike;
import org.jglrxavpok.hephaistos.nbt.NBTList;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public final class ChunkSection {
    private final byte sectionY;
    private final Block[] blocks;
    private final byte[] blockLights;
    private final byte[] skyLights;
    public ChunkSection(@NotNull World world, int chunkX, int chunkZ, @NotNull NBTCompoundLike nbt){
        this.sectionY= Objects.requireNonNull(nbt.getByte("Y"),"'Y' not found");
        this.blocks= parseBlocks(new Location(world,chunkX*16,sectionY*16,chunkZ*16),Objects.requireNonNull(nbt.getCompound("block_states"),"'block_states' not found"));
        this.blockLights=Optional.ofNullable(nbt.getByteArray("BlockLight")).map(ImmutableByteArray::copyArray).orElseGet(()->new byte[2048]);
        this.skyLights= Optional.ofNullable(nbt.getByteArray("SkyLight")).map(ImmutableByteArray::copyArray).orElseGet(()->new byte[2048]);
    }
    public ChunkSection(@NotNull World world,int chunkX,int chunkZ,byte sectionY){
        this.sectionY=sectionY;
        this.blocks=createEmptyBlocks(new Location(world,chunkX*16,sectionY*16,chunkZ*16));
        this.blockLights=new byte[2048];
        this.skyLights=new byte[2048];
    }
    public byte[] getBlockLights() {
        return Arrays.copyOf(this.blockLights,this.blockLights.length);
    }

    public byte[] getSkyLights() {
        return Arrays.copyOf(this.skyLights,this.skyLights.length);
    }

    public byte getSectionY() {
        return sectionY;
    }
    public Block getBlock(int x, int y, int z){
        checkValidXYZ(x,y,z);
        return this.blocks[(x<<8)|(y<<4)|z];
    }
    public void setBlock(int x,int y,int z,@NotNull BlockMeta meta){
        checkValidXYZ(x, y, z);
        this.blocks[(x<<8)|(y<<4)|z]=new Block(getBlock(x,y,z).location(),meta);
    }

    @NotNull
    public Block[] blocks(){
        return Arrays.copyOf(this.blocks,this.blocks.length);
    }
    private static void checkValidXYZ(int x,int y,int z){
        if(x<0||15<x) throw new IllegalArgumentException("'x' must be between 0 and 15");
        if(y<0||15<y) throw new IllegalArgumentException("'y' must be between 0 and 15");
        if(z<0||15<z) throw new IllegalArgumentException("'z' must be between 0 and 15");
    }
    private static Block[] parseBlocks(@NotNull Location baseLocation, @NotNull NBTCompoundLike nbt){
        if(!nbt.containsKey("data")) return createEmptyBlocks(baseLocation);
        NBTList<?> palette=Objects.requireNonNull(nbt.getList("palette"),"'palette' not found");
        BlockMeta[] metaPalette=parsePalette(palette);
        if(metaPalette.length==1) return createSingleBlocks(baseLocation,metaPalette[0]);
        int bitsPerBlock=Math.max(4,BitUtils.calculateBitsToStore(palette.getSize()-1));
        FixedLengthBitStorage storage=new FixedLengthBitStorage(bitsPerBlock,Objects.requireNonNull(nbt.getLongArray("data"),"'data' not found").copyArray());
        Block[] blocks=new Block[16*16*16];
        for(int x=0;x<16;x++){
            for(int y=0;y<16;y++){
                for(int z=0;z<16;z++){
                    int index=(x<<8)|(y<<4)|(z);
                    int v= (int) storage.read();
                    blocks[index]=new Block(baseLocation.add(x,y,z),metaPalette[v].material(),metaPalette[v].toCompound());
                }
            }
        }
        return blocks;
    }
    private static BlockMeta[] parsePalette(@NotNull NBTList<?> palette){
        BlockMeta[] metaPalette=new BlockMeta[palette.getSize()];
        for(int i=0;i<metaPalette.length;i++){
            Material material=Material.fromId(
                    Objects.requireNonNull(((NBTCompoundLike)palette.get(i)).getString("Name"),"'Name' not found")
            );
            NBTCompound nbt=Objects.requireNonNullElse (((NBTCompoundLike)palette.get(i)).getCompound("Properties"),NBTCompound.EMPTY);
            metaPalette[i]=new BlockMeta() {
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
        return metaPalette;
    }
    private static Block[] createSingleBlocks(@NotNull Location baseLocation, @NotNull BlockMeta meta){
        Block[] blocks=new Block[16*16*16];
        for(int x=0;x<16;x++){
            for(int y=0;y<16;y++){
                for(int z=0;z<16;z++){
                    int index=(x<<8)|(y<<4)|(z);
                    blocks[index]=new Block(baseLocation.add(x,y,z),meta);
                }
            }
        }
        return blocks;
    }
    private static Block[] createEmptyBlocks(@NotNull Location baseLocation){
        return createSingleBlocks(baseLocation, new BlockMeta() {
            @Override
            public Material material() {
                return Material.AIR;
            }

            @Override
            public NBTCompound toCompound() {
                return NBTCompound.EMPTY;
            }
        });
    }
    private static int calculateBitsPerBlock(int paletteLength){
        return Math.max(4,Integer.toBinaryString(paletteLength).length());
    }
}
