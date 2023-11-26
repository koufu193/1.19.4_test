package io.github.koufu193.core.game.world.chunk.palette;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.OutputStream;

public interface Palette {
    int FULL_BITS_PER_BLOCK=15;
    int FULL_BITS_PER_BIOMES=6;
    byte bitsPerBlock();
    int longArrayLength();
    void write(@NotNull OutputStream output) throws IOException;
}
