package io.github.koufu193.core.files.world;

import io.github.koufu193.core.files.world.level.generation.WorldGenSettings;
import io.github.koufu193.core.game.data.Difficulty;
import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.util.ConvertibleToNBTCompound;
import org.jetbrains.annotations.NotNull;
import org.jglrxavpok.hephaistos.nbt.*;
import org.jglrxavpok.hephaistos.nbt.mutable.MutableNBTCompound;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class LevelDat implements ConvertibleToNBTCompound {
    private final Path file;

    private final String name;
    private Difficulty difficulty;
    private boolean difficultyLocked;
    private Location defaultSpawnLocation;
    private final WorldGenSettings worldGenSettings;

    public LevelDat(@NotNull Path file) {
        this.file = file;
        NBTCompoundLike nbt;
        if (Files.notExists(file)) {
            nbt = NBTCompound.EMPTY;
        } else try (NBTReader reader = new NBTReader(this.file, CompressedProcesser.GZIP)) {
            nbt = Objects.requireNonNull(((NBTCompound) reader.read()), "Data Not Found");
        } catch (IOException | NBTException e) {
            throw new RuntimeException(e);
        }
        this.difficulty = Difficulty.values()[Objects.requireNonNullElseGet(nbt.getByte("Difficulty"),()->(byte)Difficulty.Normal.ordinal())];
        this.difficultyLocked = Objects.requireNonNullElse(nbt.getBoolean ("DifficultyLocked"),false);
        this.name = Objects.requireNonNull(nbt.getString("LevelName"), "'LevelName' Not Found");
        this.defaultSpawnLocation = readDefaultSpawnLocation(nbt);
        this.worldGenSettings = new WorldGenSettings(Objects.requireNonNull(nbt.getCompound("WorldGenSettings"), "WorldGenSettings Not Found"));
        write();
    }
    @Override
    public NBTCompound toCompound() {
        return NBT.Compound(root->{
            root.setByte("Difficulty", (byte) this.difficulty.ordinal());
            root.set("DifficultyLocked",NBT.Boolean(this.difficultyLocked));
            root.setString("LevelName",this.name);
            root.setInt("SpawnX", (int) this.defaultSpawnLocation.x());
            root.setInt("SpawnY", (int) this.defaultSpawnLocation.y());
            root.setInt("SpawnZ", (int) this.defaultSpawnLocation.z());
            root.set("WorldGenSettings",this.worldGenSettings.toCompound());
        });
    }

    public WorldGenSettings worldGenSettings() {
        return this.worldGenSettings;
    }

    private static Location readDefaultSpawnLocation(@NotNull NBTCompoundLike nbt) {
        return new Location(
                Objects.requireNonNullElse(nbt.getInt("SpawnX"), 0),
                Objects.requireNonNullElse(nbt.getInt("SpawnY"), 0),
                Objects.requireNonNullElse(nbt.getInt("SpawnZ"), 0)
        );
    }

    public Difficulty difficulty() {
        return this.difficulty;
    }

    public boolean difficultyLocked() {
        return this.difficultyLocked;
    }

    public LevelDat difficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public String levelName() {
        return name;
    }

    public void defaultSpawnLocation(@NotNull Location location) {
        this.defaultSpawnLocation = location;
    }

    public Location defaultSpawnLocation() {
        return defaultSpawnLocation;
    }

    public void write() {
        try (NBTWriter writer = new NBTWriter(file, CompressedProcesser.GZIP)) {
            writer.writeNamed("", this.toCompound());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
