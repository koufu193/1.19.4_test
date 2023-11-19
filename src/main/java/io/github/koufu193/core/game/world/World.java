package io.github.koufu193.core.game.world;

import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.core.game.world.chunk.Chunk;
import io.github.koufu193.core.game.world.chunk.ChunkLoader;
import io.github.koufu193.core.files.LevelDat;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

public class World {
    public static final int MIN_Y = -64;
    public static final int MAX_Y = 320;
    private final File worldFolder;
    private final ChunkLoader chunkCache;

    private final LevelDat level;

    private Location defaultSpawnLocation;

    public World(File worldFolder) {
        this.worldFolder = worldFolder;
        if (!worldFolder.exists() || !containsLevelDatFile(worldFolder))
            throw new IllegalArgumentException("not a world folder");
        this.chunkCache = new ChunkLoader(this);
        this.level = LevelDat.fromFolder(worldFolder);
        this.defaultSpawnLocation=this.level.defaultSpawnLocation().world(this);
    }

    private static boolean containsLevelDatFile(File folder) {
        return Arrays.stream(Objects.requireNonNull(folder.listFiles(File::isFile))).anyMatch(a -> "level.dat".equals(a.getName()));
    }

    public File worldFolder() {
        return worldFolder;
    }

    public Chunk chunk(int chunkX, int chunkZ) {
        return this.chunkCache.getOrLoad(chunkX, chunkZ);
    }

    public String name() {
        return this.level.levelName();
    }

    public Location defaultSpawnLocation() {
        return this.defaultSpawnLocation;
    }
}
