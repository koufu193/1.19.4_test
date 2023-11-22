package io.github.koufu193.core.files.world;

import io.github.koufu193.network.data.DataTypes;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

public final class WorldUID {
    private final UUID uid;
    public WorldUID(@NotNull File file) throws IOException {
        checkOrThrowIsFile(file);
        this.uid=parseToUID(Files.readAllBytes(file.toPath()));
    }
    public WorldUID(@NotNull UUID uid){
        this.uid=uid;
    }
    public void writeTo(@NotNull File file) throws IOException {
        checkOrThrowIsFile(file);
        Files.write(file.toPath(), DataTypes.UUID.encode(this.uid));
    }
    public void writeToWorldFolder(@NotNull File worldFolder) throws IOException {
        writeTo(locationByWorldFolder(worldFolder));
    }

    public UUID uid() {
        return uid;
    }

    private static UUID parseToUID(@NotNull byte[] bytes){
        if(bytes.length!=Long.BYTES*2) throw new IllegalArgumentException(String.format("bytes length must be %d,actual %d",Long.BYTES*2,bytes.length));
        return UUID.nameUUIDFromBytes(bytes);
    }
    public static WorldUID fromWorldFolder(@NotNull File worldFolder) throws IOException {
        return new WorldUID(locationByWorldFolder(worldFolder));
    }
    private static void checkOrThrowIsFile(@NotNull File file){
        if(!file.exists()) throw new IllegalArgumentException(String.format("%s doesn't exist",file.getName()));
        if(!file.isFile()) throw new IllegalArgumentException(String.format("%s isn't a file",file.getName()));
    }
    private static void checkOrThrowIsDirectory(@NotNull File file){
        if(!file.exists()) throw new IllegalArgumentException(String.format("%s doesn't exist",file.getName()));
        if(!file.isDirectory()) throw new IllegalArgumentException(String.format("%s isn't a directory",file.getName()));
    }
    private static File locationByWorldFolder(@NotNull File folder){
        checkOrThrowIsDirectory(folder);
        return new File(folder,"uid.dat");
    }
}
