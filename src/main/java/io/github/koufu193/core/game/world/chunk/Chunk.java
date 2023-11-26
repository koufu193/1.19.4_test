package io.github.koufu193.core.game.world.chunk;

import io.github.koufu193.core.game.world.World;
import org.jetbrains.annotations.NotNull;
import org.jglrxavpok.hephaistos.nbt.NBT;
import org.jglrxavpok.hephaistos.nbt.NBTCompoundLike;
import org.jglrxavpok.hephaistos.nbt.NBTList;

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
