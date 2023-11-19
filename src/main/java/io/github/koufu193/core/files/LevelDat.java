package io.github.koufu193.core.files;

import io.github.koufu193.core.game.data.Difficulty;
import io.github.koufu193.core.game.data.Location;
import org.jetbrains.annotations.NotNull;
import org.jglrxavpok.hephaistos.nbt.*;
import org.jglrxavpok.hephaistos.nbt.mutable.MutableNBTCompound;

import java.io.*;
import java.util.Objects;

public class LevelDat {
    private final MutableNBTCompound nbt;
    private final File file;

    private final String name;
    private Difficulty difficulty;
    private boolean difficultyLocked;
    private Location defaultSpawnLocation;
    public LevelDat(File f){
        this.file=f;
        if(!f.exists()){
            this.nbt=NBT.Compound(a->{}).toMutableCompound();
        }else try(NBTReader reader=new NBTReader(this.file, CompressedProcesser.GZIP)){
            this.nbt=((NBTCompound) reader.read()).toMutableCompound();
        } catch (IOException | NBTException e) {
            throw new RuntimeException(e);
        }

        difficulty=Difficulty.values()[(Byte)nbt.getOrPut("Difficulty",()-> NBT.Byte(Difficulty.Easy.ordinal())).getValue()];
        difficultyLocked= (byte)nbt.getOrPut("DifficultyLocked",()->NBT.Boolean(false)).getValue()== NBT.getTRUE().getValue();
        this.name= Objects.requireNonNull(nbt.getString("LevelName"),"'LevelName' Not Found");
        this.defaultSpawnLocation=readDefaultSpawnLocationData(nbt);
        write();
    }
    private static Location readDefaultSpawnLocationData(@NotNull MutableNBTCompound nbt){
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
        this.nbt.set("Difficulty",NBT.Byte(this.difficulty.ordinal()));
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
            writer.writeNamed("",this.nbt.toCompound());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static LevelDat fromFolder(File folder){
        return new LevelDat(new File(folder,"level.dat"));
    }
}
