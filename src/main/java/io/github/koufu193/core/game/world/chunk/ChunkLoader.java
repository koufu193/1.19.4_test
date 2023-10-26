package io.github.koufu193.core.game.world.chunk;

import io.github.koufu193.core.game.world.World;
import org.jglrxavpok.hephaistos.mca.AnvilException;
import org.jglrxavpok.hephaistos.mca.RegionFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public final class ChunkLoader {
    private final World world;
    private final File regionFolder;
    private final Map<Long,Chunk> cache=new HashMap<>();

    public ChunkLoader(World world) {
        this.world = world;
        this.regionFolder =new File(world.worldFolder(),"regions");
        if(!regionFolder.exists()) regionFolder.mkdirs();
    }

    public Chunk getFromCache(int chunkX, int chunkZ){
        return this.cache.get(packChunkXZ(chunkX,chunkZ));
    }
    public Chunk getOrLoad(int chunkX,int chunkZ){
        Chunk chunk= getFromCache(chunkX, chunkZ);
        if(chunk==null) chunk= _loadAndPut(chunkX,chunkZ);
        return chunk;
    }
    public void unloadFromCache(int chunkX, int chunkZ){
        this.cache.remove(packChunkXZ(chunkX, chunkZ));
    }
    private Chunk _loadAndPut(int chunkX, int chunkZ) {
        try(RegionFile file=RegionFileOpener.openOrCreate(this.regionFolder,chunkX,chunkZ)){
            Chunk chunk=new Chunk(this.world,file.getOrCreateChunk(chunkX, chunkZ));
            this.cache.put(packChunkXZ(chunkX,chunkZ),chunk);
            return chunk;
        }catch (IOException e){
            throw new RuntimeException(e);
        }catch (AnvilException e){
            System.out.println("broken chunk region");
            new File(this.regionFolder,RegionFile.Companion.createFileName(chunkX>>5,chunkZ>>5)).delete();
            return _loadAndPut(chunkX, chunkZ);
        }
    }
    private static long packChunkXZ(int chunkX,int chunkZ){
        return ((long)chunkX<<32)|(chunkZ);
    }
}
