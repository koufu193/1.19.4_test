package io.github.koufu193.core.loaders;

import io.github.koufu193.core.game.data.Difficulty;
import org.jglrxavpok.hephaistos.nbt.*;
import org.jglrxavpok.hephaistos.nbt.mutable.MutableNBTCompound;

import java.io.*;

public class LevelDat {
    private MutableNBTCompound nbt;
    private File file;

    private Difficulty difficulty;
    private boolean difficultyLocked;
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
        write();
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
