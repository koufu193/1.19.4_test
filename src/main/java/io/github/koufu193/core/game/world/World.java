package io.github.koufu193.core.game.world;

import io.github.koufu193.core.files.world.LevelDat;
import io.github.koufu193.core.files.world.WorldUID;
import io.github.koufu193.core.files.world.level.BiomeSource;
import io.github.koufu193.core.files.world.level.dimension.NBTDimension;
import io.github.koufu193.core.files.world.level.generation.WorldGenSettings;
import io.github.koufu193.core.game.data.Difficulty;
import io.github.koufu193.core.game.data.Identifier;
import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.core.game.world.chunk.Chunk;
import io.github.koufu193.core.game.world.chunk.ChunkLoader;
import io.github.koufu193.core.game.world.chunk.block.Block;
import io.github.koufu193.core.game.world.dimension.DimensionType;
import io.github.koufu193.server.MinecraftServer;
import io.github.koufu193.util.FileUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Locale;
import java.util.UUID;

public class World {
    public static final int MIN_Y = -64;
    public static final int MAX_Y = 320;
    public static final int MAX_Y_SECTION=MAX_Y/16;
    public static final int MIN_Y_SECTION=MIN_Y/16;
    private final Path worldFolder;
    private final ChunkLoader chunkCache;

    private final LevelDat level;
    private final Identifier name;
    private final MinecraftServer server;
    private final WorldUID uid;
    private final DimensionType dimensionType;

    private Location defaultSpawnLocation;

    public World(@NotNull MinecraftServer server, @NotNull Path worldFolder) throws IOException {
        FileUtils.throwIfNotADirectory(worldFolder);
        if (!containsLevelDatFile(worldFolder)) {
            throw new IllegalArgumentException(String.format("not a world folder %s", worldFolder.getFileName()));
        }
        this.server = server;
        this.worldFolder = worldFolder;
        this.level = new LevelDat(worldFolder.resolve("level.dat"));
        this.chunkCache = new ChunkLoader(this, worldFolder.resolve("region"));
        this.defaultSpawnLocation = this.level.defaultSpawnLocation().world(this);
        this.name = new Identifier(this.level.levelName().toLowerCase(Locale.ROOT));
        this.uid = new WorldUID(worldFolder.resolve("uid.dat"));
        this.dimensionType = DimensionType.Builtin.OVERWORLD;
        if (!includingDimensionType(this.worldGenSettings(), this.dimensionType)) {
            throw new IllegalArgumentException(String.format("level.dat does not include dimension type:%s", this.dimensionType().id()));
        }

    }

    public Difficulty difficulty() {
        return this.level.difficulty();
    }

    public boolean difficultyLocked() {
        return this.level.difficultyLocked();
    }

    @NotNull
    public DimensionType dimensionType() {
        return this.dimensionType;
    }

    @NotNull
    public MinecraftServer server() {
        return this.server;
    }

    @NotNull
    public UUID uid() {
        return this.uid.uid();
    }

    @NotNull
    public Path worldFolder() {
        return worldFolder;
    }
    @Nullable
    public Block getBlock(int x,int y,int z){
        return new Location(this,x,y,z).getBlock();
    }
    @NotNull
    public Chunk chunk(int chunkX, int chunkZ) {
        return this.chunkCache.getOrLoad(chunkX, chunkZ);
    }

    @NotNull
    public Identifier name() {
        return this.name;
    }

    public boolean isFlatWorld() {
        return BiomeSource.FLAT.equals(this.biomeSourceType());
    }

    public boolean isDebugWorld() {
        return BiomeSource.DEBUG.equals(this.biomeSourceType());
    }

    @NotNull
    public Identifier biomeSourceType() {
        return this.dimensionByDimensionType(this.dimensionType).generator().biomeSource().type();
    }

    @NotNull
    public Location defaultSpawnLocation() {
        return this.defaultSpawnLocation;
    }

    @NotNull
    public WorldGenSettings worldGenSettings() {
        return this.level.worldGenSettings();
    }

    @Nullable
    public NBTDimension dimensionByDimensionType(@NotNull DimensionType type) {
        return this.worldGenSettings().dimensions().get(type.id());
    }

    private static boolean includingDimensionType(@NotNull WorldGenSettings settings, @NotNull DimensionType type) {
        return settings.dimensions().containsKey(type.id());
    }

    private static boolean containsLevelDatFile(Path folder) {
        return FileUtils.childFiles(folder).anyMatch(a -> "level.dat".equals(a.getFileName().toString()));
    }
}
