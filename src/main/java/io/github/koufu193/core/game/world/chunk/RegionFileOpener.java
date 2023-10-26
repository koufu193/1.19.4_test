package io.github.koufu193.core.game.world.chunk;

import org.bukkit.Chunk;
import org.jglrxavpok.hephaistos.data.DataSource;
import org.jglrxavpok.hephaistos.data.GrowableSource;
import org.jglrxavpok.hephaistos.mca.AnvilException;
import org.jglrxavpok.hephaistos.mca.RegionFile;
import org.jglrxavpok.hephaistos.nbt.NBTReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RegionFileOpener {
    public static RegionFile open(File folder, int chunkX, int chunkZ) throws IOException, AnvilException {
        return new RegionFile(new RandomAccessFile(new File(folder,RegionFile.Companion.createFileName(chunkX>>5,chunkZ>>5)),"rw"),chunkX>>5,chunkZ>>5);
    }
    public static RegionFile openOrCreate(File folder,int chunkX,int chunkZ) throws IOException, AnvilException {
        File f=new File(folder,RegionFile.Companion.createFileName(chunkX>>5,chunkZ>>5));
        if(!f.exists()) f.createNewFile();
        return open(folder,chunkX,chunkZ);
    }
}
