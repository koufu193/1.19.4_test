package io.github.koufu193.core.game.world;

import io.github.koufu193.core.game.world.chunk.Chunk;
import io.github.koufu193.core.game.world.chunk.ChunkLoader;
import org.jglrxavpok.hephaistos.mca.AnvilException;
import org.jglrxavpok.hephaistos.nbt.CompressedProcesser;
import org.jglrxavpok.hephaistos.nbt.NBTCompound;
import org.jglrxavpok.hephaistos.nbt.NBTException;
import org.jglrxavpok.hephaistos.nbt.NBTReader;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;

public class World {
    public static final int MIN_Y=-64;
    public static final int MAX_Y=320;
    private final File worldFolder;
    private final ChunkLoader chunkCache;

    private final NBTCompound levelDat;
    private final String name;
    public World(File worldFolder){
        this.worldFolder=worldFolder;
        if(!worldFolder.exists()||!hasLevelDat(worldFolder)) throw new IllegalArgumentException("not a world folder");
        this.chunkCache=new ChunkLoader(this);
        try(NBTReader reader=new NBTReader(new FileInputStream(new File(worldFolder,"level.dat")), CompressedProcesser.GZIP)){
            this.levelDat= (NBTCompound) reader.read();
        } catch (IOException | NBTException e) {
            throw new RuntimeException(e);
        }
        this.name=Objects.requireNonNull(this.levelDat.getString("LevelName"),"In "+worldFolder.getName()+", property 'LevelName' not found");
    }
    private static boolean hasLevelDat(File folder){
        return Arrays.stream(folder.listFiles(File::isFile)).anyMatch(a->"level.dat".equals(a.getName()));
    }

    public File worldFolder() {
        return worldFolder;
    }
    public Chunk chunk(int chunkX,int chunkZ){
        return this.chunkCache.getOrLoad(chunkX,chunkZ);
    }
    public String name(){
        return this.name;
    }
}
