package io.github.koufu193.core.game.world.chunk;

import io.github.koufu193.core.files.world.region.RegionFileReader;
import io.github.koufu193.core.game.world.World;
import io.github.koufu193.util.FileUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jglrxavpok.hephaistos.mca.RegionFile;
import org.jglrxavpok.hephaistos.nbt.NBTException;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * チャンク読み込みクラス
 */
public final class ChunkLoader implements AutoCloseable{
    private final World world;
    private final Path regionFolder;
    private final Map<Long, Chunk> cache = new HashMap<>();
    private RegionFileReader reader;

    /**
     * @param world        チャンクを読み込むワールド
     * @param regionFolder チャンクデータがあるワールド
     */

    public ChunkLoader(@NotNull World world, @NotNull Path regionFolder) throws IOException {
        FileUtils.createDirectories(regionFolder);
        this.regionFolder = regionFolder;
        this.world = world;
        this.reader=new RegionFileReader(regionFolder.resolve("r.0.0.mca"));
    }

    /**
     * キャッシュされたチャンクを取得
     *
     * @return チャンク キャッシュされていないならnull
     */
    @Nullable
    public Chunk getFromCache(int chunkX, int chunkZ) {
        return this.cache.get(packChunkXZ(chunkX, chunkZ));
    }

    /**
     * チャンクを取得<br>キャッシュされていないならファイルから、ファイルがないなら作成する
     *
     * @return チャンク
     */
    @NotNull
    public Chunk getOrLoad(int chunkX, int chunkZ) {
        Chunk chunk = getFromCache(chunkX, chunkZ);
        if (chunk == null) chunk = _loadAndCreate(chunkX, chunkZ);
        return chunk;
    }

    public void unloadFromCache(int chunkX, int chunkZ) {
        this.cache.remove(packChunkXZ(chunkX, chunkZ));
    }

    @NotNull
    private Chunk _loadAndCreate(int chunkX, int chunkZ) {
        try {
            Chunk chunk = reader.readChunk(world, chunkX & 0x1f, chunkZ & 0x1f);
            this.cache.put(packChunkXZ(chunkX, chunkZ), chunk);
            return chunk;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NBTException e) {
            System.out.println("broken chunk region");
            FileUtils.delete(this.regionFolder.resolve(RegionFile.Companion.createFileName(chunkX >> 5, chunkZ >> 5)));
            return _loadAndCreate(chunkX, chunkZ);
        }
    }

    private static long packChunkXZ(int chunkX, int chunkZ) {
        return ((long) chunkX << 32) | (chunkZ&-1);
    }

    @Override
    public void close() throws IOException {
        if(!this.reader.isOpen()) this.reader.close();
    }
}
