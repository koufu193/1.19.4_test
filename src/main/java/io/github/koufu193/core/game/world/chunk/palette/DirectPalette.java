package io.github.koufu193.core.game.world.chunk.palette;

import io.github.koufu193.core.game.data.Material;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.util.BitStorage;
import io.github.koufu193.util.FixedLengthBitStorage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

public class DirectPalette implements Palette {
    private final int bits;
    private final Material[] blocks;

    public DirectPalette(@NotNull Material[] blocks) {
        this.bits = Palette.FULL_BITS_PER_BLOCK;
        this.blocks = Arrays.copyOf(blocks, blocks.length);
    }

    @Override
    public byte bitsPerBlock() {
        return (byte) this.bits;
    }

    @Override
    public int longArrayLength() {
        return Math.ceilDiv(16 * 16 * 16, 64 / this.bits);
    }

    @Override
    public void write(@NotNull OutputStream output) throws IOException {
        byte[] data = bytes();
        output.write(this.bits);
        output.write(DataTypes.VarInt.encode(longArrayLength()));
        output.write(data);
    }

    private byte[] bytes() {
        FixedLengthBitStorage storage = new FixedLengthBitStorage(this.bits,longArrayLength() * Long.SIZE);
        for (Material block : blocks) {
            storage.write(block.blockId());
        }
        return storage.byteArray();
    }
}
