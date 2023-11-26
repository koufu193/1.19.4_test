package io.github.koufu193.core.game.world.chunk;

import io.github.koufu193.util.FileUtils;
import org.bukkit.Chunk;
import org.jetbrains.annotations.NotNull;
import org.jglrxavpok.hephaistos.data.DataSource;
import org.jglrxavpok.hephaistos.data.GrowableSource;
import org.jglrxavpok.hephaistos.mca.AnvilException;
import org.jglrxavpok.hephaistos.mca.RegionFile;
import org.jglrxavpok.hephaistos.nbt.NBTReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.util.RandomAccess;

public class RegionFileOpener {
    public static RegionFile open(@NotNull Path folder, int chunkX, int chunkZ) throws IOException, AnvilException {
        return new RegionFile(new RandomAccessFile(folder.resolve(RegionFile.Companion.createFileName(chunkX>>5,chunkZ>>5)).toFile(),"rw"),chunkX>>5,chunkZ>>5);
    }
    public static RegionFile openOrCreate(@NotNull Path folder,int chunkX,int chunkZ) throws IOException, AnvilException {
        Path f=folder.resolve(RegionFile.Companion.createFileName(chunkX>>5,chunkZ>>5));
        FileUtils.createFile(f);
        return open(folder,chunkX,chunkZ);
    }
}
