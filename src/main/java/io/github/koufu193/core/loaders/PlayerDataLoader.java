package io.github.koufu193.core.loaders;

import org.jglrxavpok.hephaistos.nbt.*;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public record PlayerDataLoader(File playerDataFolder) {
    public PlayerDataLoader {
        if (!playerDataFolder.exists()) playerDataFolder.mkdirs();
    }
    public NBTCompound get(UUID uuid) {
        File f = new File(this.playerDataFolder,"342dfe34-def6-43d0-954f-9a681570a2bb.dat");//new File(this.playerDataFolder, uuid + ".dat");
        if(!f.exists()) return null;
        try (NBTReader reader = new NBTReader(f, CompressedProcesser.GZIP)) {
            return (NBTCompound) reader.read();
        } catch (IOException | NBTException e) {
            throw new RuntimeException(e);
        }
    }
    public void write(UUID uuid,NBTCompound nbt){
        File f = new File(this.playerDataFolder, uuid + ".dat");
        try (NBTWriter writer = new NBTWriter(f, CompressedProcesser.GZIP)) {
            writer.writeNamed("",nbt);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static PlayerDataLoader fromWorldFolder(File folder){
        return new PlayerDataLoader(new File(folder,"playerdata"));
    }
}
