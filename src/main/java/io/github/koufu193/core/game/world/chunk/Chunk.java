package io.github.koufu193.core.game.world.chunk;

import io.github.koufu193.core.game.data.block.BlockMeta;
import io.github.koufu193.core.game.world.World;
import io.github.koufu193.core.game.world.chunk.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jglrxavpok.hephaistos.nbt.*;

import java.util.Arrays;
import java.util.Objects;

public class Chunk {
    private final World world;
    private final int chunkX;
    private final int chunkZ;
    private final int minYSection;
    private final long[] motionBlocking;

    private final ChunkSection[] sections;

    public Chunk(@NotNull World world, int maxYSection, @NotNull NBTCompoundLike nbt) {
        this.world = world;
        this.chunkX = Objects.requireNonNull(nbt.getInt("xPos"), "'xPos' not found");
        this.chunkZ = Objects.requireNonNull(nbt.getInt("zPos"), "'zPos' not found");
        this.minYSection = Objects.requireNonNull(nbt.getInt("yPos"), "'yPos' not found");
        this.sections = loadChunkSections(world, this.chunkX, this.chunkZ, this.minYSection, maxYSection, Objects.requireNonNull(nbt.getList("sections"), "'sections' not found"));
        this.motionBlocking = Objects.requireNonNull(Objects.requireNonNull(nbt.getCompound("Heightmaps")).getLongArray("MOTION_BLOCKING")).copyArray();
        applyBlockEntities(Objects.requireNonNullElseGet(nbt.getList("block_entities"),()->new NBTList<NBTCompound>(NBTType.TAG_Compound)));
    }

    public Chunk(@NotNull World world, int chunkX, int chunkZ) {
        this(world, chunkX, chunkZ, World.MIN_Y_SECTION, World.MAX_Y_SECTION);
    }

    public Chunk(@NotNull World world, int chunkX, int chunkZ, int minYSection, int maxYSection) {
        if (maxYSection < minYSection) throw new IllegalArgumentException("maxYSection<minYSection");
        this.world = world;
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
        this.minYSection = minYSection;
        this.motionBlocking = new long[37];
        this.sections = createEmptyChunkSections(world, chunkX, chunkZ, minYSection, maxYSection);
    }

    public int chunkX() {
        return this.chunkX;
    }

    public int chunkZ() {
        return this.chunkZ;
    }
    public Block getBlock(int x, int y, int z){
        checkValidXZ(x,z);
        if(!(minYSection*16<=y&&y<(minYSection+sections.length)*16)) return null;
        return getBlockByRelativeLocation(x&0x0f,y,z&0x0f);
    }
    public void setBlock(int x, int y, int z, @NotNull BlockMeta meta){
        checkValidXZ(x, z);
        if(!(minYSection*16<=y&&y<(minYSection+sections.length)*16)) return;
        setBlockByRelativeLocation(x&0xf,y,z&0xf,meta);
    }
    private void checkValidXZ(int x, int z){
        if(!(chunkX*16<=x&&x<(chunkX+1)*16))
            throw new IllegalArgumentException(String.format("'x' must be between %d and %d actual:%d",chunkX*16,(chunkX+1)*16-1,x));
        if(!(chunkZ*16<=z&&z<(chunkZ+1)*16))
            throw new IllegalArgumentException(String.format("'z' must be between %d and %d actual:%d",chunkZ*16,(chunkZ+1)*16-1,z));
    }
    private Block getBlockByRelativeLocation(int x, int y, int z){
        ChunkSection section=sections[(y-minYSection*16)/16];
        return section.getBlock(x,y-section.getSectionY()*16,z);
    }
    private void setBlockByRelativeLocation(int x,int y,int z,@NotNull BlockMeta meta){
        ChunkSection section=sections[(y-minYSection*16)/16];
        section.setBlock(x,y-section.getSectionY()*16,z,meta);
    }
    public long[] getMotionBlocking() {
        return Arrays.copyOf(this.motionBlocking, this.motionBlocking.length);
    }

    @NotNull
    public ChunkSection[] sections() {
        return Arrays.copyOf(this.sections, this.sections.length);
    }

    public int sectionSize() {
        return this.sections.length;
    }
    private void applyBlockEntities(@NotNull NBTList<?> blockEntities){
        blockEntities.forEach(nbt->{
            NBTCompound compound=(NBTCompound)nbt;
            int x=compound.getInt("x");
            int y=compound.getInt("y");
            int z=compound.getInt("z");
            getBlock(x,y,z).nbt(compound);
        });
    }

    private static ChunkSection[] loadChunkSections(@NotNull World world, int chunkX, int chunkZ, int minYSection, int maxYSection, @NotNull NBTList<NBT> sectionsNbt) {
        ChunkSection[] sections = new ChunkSection[maxYSection - minYSection];
        sectionsNbt.forEach(nbt -> {
            ChunkSection section = new ChunkSection(world, chunkX, chunkZ, (NBTCompoundLike) nbt);
            sections[section.getSectionY() - minYSection] = section;
        });
        for (int i = 0; i < sections.length; i++) {
            if (sections[i] == null) sections[i] = new ChunkSection(world, chunkX, chunkZ, (byte) (i + minYSection));
        }
        return sections;
    }

    private static ChunkSection[] createEmptyChunkSections(@NotNull World world, int chunkX, int chunkZ, int minYSection, int maxYSection) {
        ChunkSection[] sections = new ChunkSection[maxYSection - minYSection];
        for (int i = 0; i < sections.length; i++) {
            sections[i] = new ChunkSection(world, chunkX, chunkZ, (byte) (i + minYSection));
        }
        return sections;
    }
}
