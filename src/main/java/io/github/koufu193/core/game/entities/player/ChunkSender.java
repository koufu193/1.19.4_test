package io.github.koufu193.core.game.entities.player;

import io.github.koufu193.core.game.entities.interfaces.IPlayer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public final class ChunkSender {
    private final List<Long> loadedChunks;
    private final IPlayer player;
    public ChunkSender(@NotNull IPlayer player, @NotNull List<Long> loadedChunks){
        this.player=player;
        this.loadedChunks=loadedChunks;
    }
    public ChunkSender(@NotNull IPlayer player){
        this(player,new ArrayList<>());
    }
    public boolean isLoaded(int chunkX,int chunkZ){
        return this.loadedChunks.contains(packChunkXZ(chunkX, chunkZ));
    }
    private void addLoadedChunk(int chunkX,int chunkZ){
        loadedChunks.add(packChunkXZ(chunkX, chunkZ));
    }
    public void load(int chunkX,int chunkZ){
        if(isLoaded(chunkX,chunkZ)) return;
        this.player.packetHandler().sendChunk(this.player.world().chunk(chunkX, chunkZ));
        this.addLoadedChunk(chunkX, chunkZ);
    }
    public void unload(int chunkX,int chunkZ){
        if(!isLoaded(chunkX, chunkZ)) return;
    }
    public void clear(){
        this.loadedChunks.clear();
    }
    private static long packChunkXZ(int chunkX,int chunkZ){
        return ((long)chunkX<<32)|(chunkZ);
    }
}
