package io.github.koufu193.core.game.world;

import io.github.koufu193.core.files.world.LevelDat;
import io.github.koufu193.core.files.world.WorldUID;
import io.github.koufu193.core.game.data.Difficulty;
import io.github.koufu193.core.game.data.Identifier;
import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.core.game.world.chunk.Chunk;
import io.github.koufu193.core.game.world.chunk.ChunkLoader;
import io.github.koufu193.core.game.world.dimension.Dimension;
import io.github.koufu193.core.game.world.dimension.DimensionType;
import io.github.koufu193.core.game.world.generator.WorldGenSettings;
import io.github.koufu193.server.MinecraftServer;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;

public class World {
    public static final Identifier OVERWORLD=new Identifier("minecraft:overworld");
    public static final Identifier NETHER=new Identifier("minecraft:the_nether");
    public static final Identifier END=new Identifier("minecraft:the_end");
    public static final int MIN_Y = -64;
    public static final int MAX_Y = 320;
    private final File worldFolder;
    private final ChunkLoader chunkCache;

    private final LevelDat level;

    private Location defaultSpawnLocation;
    private final Identifier name;
    private final MinecraftServer server;
    private final WorldUID uid;
    private final DimensionType dimensionType;
    public World(@NotNull MinecraftServer server, File worldFolder) throws IOException {
        this.worldFolder = worldFolder;
        if (!worldFolder.exists() || !containsLevelDatFile(worldFolder))
            throw new IllegalArgumentException(String.format("not a world folder %s",worldFolder.getName()));
        this.chunkCache = new ChunkLoader(this);
        this.level = LevelDat.fromFolder(worldFolder);
        this.defaultSpawnLocation = this.level.defaultSpawnLocation().world(this);
        this.name = new Identifier(this.level.levelName().toLowerCase(Locale.ROOT));
        this.server=server;
        this.uid=WorldUID.fromWorldFolder(worldFolder);
        this.dimensionType=DimensionType.OVERWORLD;
        if(!includingDimensionType(this.worldGenSettings(),this.dimensionType)) throw new IllegalArgumentException(String.format("level.dat does not include dimension type:%s",this.dimensionType().id()));

    }
    public Difficulty difficulty(){
        return this.level.difficulty();
    }
    public boolean difficultyLocked(){
        return this.level.difficultyLocked();
    }
    public Dimension dimensionByDimensionType(@NotNull DimensionType type){
        return this.worldGenSettings().dimensions().get(type.id());
    }
    public DimensionType dimensionType() {
        return this.dimensionType;
    }

    public MinecraftServer server() {
        return server;
    }

    public WorldUID uid() {
        return uid;
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

    public Identifier nameId() {
        return this.name;
    }

    public Location defaultSpawnLocation() {
        return this.defaultSpawnLocation;
    }
    public boolean flat(){
        return BiomeSource.FLAT.equals(this.biomeSourceType());
    }
    public boolean debug(){
        return BiomeSource.DEBUG.equals(this.biomeSourceType());
    }
    public Identifier biomeSourceType(){
        return this.dimensionByDimensionType(this.dimensionType).generator().biomeSource().type();
    }
    public WorldGenSettings worldGenSettings() {
        return this.level.worldGenSettings();
    }
    private static boolean includingDimensionType(@NotNull WorldGenSettings settings,@NotNull DimensionType type){
        return settings.dimensions().containsKey(type.id());
    }
}
