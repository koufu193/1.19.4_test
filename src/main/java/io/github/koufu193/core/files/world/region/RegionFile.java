package io.github.koufu193.core.files.world.region;

import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

public final class RegionFile {
    private final Path filePath;

    public RegionFile(@NotNull Path filePath) {
        //if (FileUtils.createFile(filePath))
        this.filePath = filePath;
    }
}
