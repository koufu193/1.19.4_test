package io.github.koufu193.core.files.world;

import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.util.FileUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

/**
 * world/uid.datの内容
 * ワールドのUIDを示す
 */
public final class WorldUID {
    private final UUID uid;

    /**
     * @param file ワールドのUIDが保存されているファイル
     */
    public WorldUID(@NotNull Path file){
        this.uid=parseToUID(FileUtils.readAllBytes(file));
    }

    /**
     * @param uid ワールドのUID
     */
    public WorldUID(@NotNull UUID uid){
        this.uid=uid;
    }

    /**
     * ワールドのUIDを取得
     * @return ワールドのUID
     */
    @NotNull
    public UUID uid() {
        return this.uid;
    }

    /**
     * ワールドのUIDを指定ファイルへ書き込む
     * @param file ワールドのUIDを書き込むファイル
     */
    public void writeTo(@NotNull Path file){
        FileUtils.write(file, DataTypes.UUID.encode(this.uid));
    }


    private static UUID parseToUID(@NotNull byte[] bytes){
        if(bytes.length!=Long.BYTES*2) throw new IllegalArgumentException(String.format("bytes length must be %d,actual %d",Long.BYTES*2,bytes.length));
        return UUID.nameUUIDFromBytes(bytes);
    }
}
