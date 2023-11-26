package io.github.koufu193.core.files.world;

import io.github.koufu193.core.game.data.Difficulty;
import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.core.files.world.level.generation.WorldGenSettings;
import org.jetbrains.annotations.NotNull;
import org.jglrxavpok.hephaistos.nbt.*;
import org.jglrxavpok.hephaistos.nbt.mutable.MutableNBTCompound;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class LevelDat {
    private final MutableNBTCompound data;
    private final Path file;

    private final String name;
    private Difficulty difficulty;
    private boolean difficultyLocked;
    private Location defaultSpawnLocation;
    private final WorldGenSettings worldGenSettings;
    public LevelDat(@NotNull Path file){
        this.file=file;
        if(Files.notExists(file)){
            this.data =NBT.Compound(a->{}).toMutableCompound();
        }else try(NBTReader reader=new NBTReader(this.file, CompressedProcesser.GZIP)){
            this.data = Objects.requireNonNull(((NBTCompound) reader.read()),"Data Not Found").toMutableCompound();
        } catch (IOException | NBTException e) {
            throw new RuntimeException(e);
        }
        difficulty=Difficulty.values()[(Byte) data.getOrPut("Difficulty",()-> NBT.Byte(Difficulty.Easy.ordinal())).getValue()];
        difficultyLocked= ((NBTByte)data.getOrPut("DifficultyLocked",()->NBT.Boolean(false))).asBoolean();
        this.name= Objects.requireNonNull(data.getString("LevelName"),"'LevelName' Not Found");
        this.defaultSpawnLocation= readOrPutDefaultSpawnLocationData(data);
        this.worldGenSettings=new WorldGenSettings(Objects.requireNonNull(data.getCompound("WorldGenSettings"),"WorldGenSettings Not Found"));
        write();
    }

    public WorldGenSettings worldGenSettings() {
        return this.worldGenSettings;
    }

    private static Location readOrPutDefaultSpawnLocationData(@NotNull MutableNBTCompound nbt){
        return new Location(
                (Integer)nbt.getOrPut("SpawnX",()->NBT.Int(0)).getValue(),
                (Integer)nbt.getOrPut("SpawnY",()->NBT.Int(0)).getValue(),
                (Integer)nbt.getOrPut("SpawnZ",()->NBT.Int(0)).getValue()
                );
    }
    public Difficulty difficulty(){
        return this.difficulty;
    }

    public boolean difficultyLocked() {
        return this.difficultyLocked;
    }

    public LevelDat difficulty(Difficulty difficulty){
        this.difficulty=difficulty;
        this.data.set("Difficulty",NBT.Byte(this.difficulty.ordinal()));
        return this;
    }

    public String levelName() {
        return name;
    }
    public void defaultSpawnLocation(@NotNull Location location){
        this.defaultSpawnLocation=location.clone();
    }

    public Location defaultSpawnLocation() {
        return defaultSpawnLocation.clone();
    }

    public void write(){
        try(NBTWriter writer=new NBTWriter(file,CompressedProcesser.GZIP)){
            writer.writeNamed("",this.data.toCompound());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
