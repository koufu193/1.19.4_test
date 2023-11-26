package io.github.koufu193.core.game.world.chunk.palette;

import io.github.koufu193.core.game.data.Material;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.util.BitStorage;
import io.github.koufu193.util.BitUtils;
import io.github.koufu193.util.FixedLengthBitStorage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

public class IndirectPalette implements Palette {
    private final Material[] blocks;
    private final Material[] palette;
    private final Map<Material, Integer> paletteMap;
    private final int bits;

    public IndirectPalette(@NotNull Material[] blocks) {
        this(blocks, createPalette(blocks));
    }

    public IndirectPalette(@NotNull Material[] blocks,@NotNull Material[] palette) {
        this.blocks=Arrays.copyOf(blocks,blocks.length);
        this.palette = Arrays.copyOf(palette,palette.length);
        this.bits = Math.max(4, BitUtils.calculateBitsToStore(palette.length-1));
        this.paletteMap = createPaletteMap(this.palette);
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
        output.write(DataTypes.VarInt.encode(this.palette.length));
        for (Material p : palette) {
            output.write(DataTypes.VarInt.encode(p.blockId()));
        }
        output.write(DataTypes.VarInt.encode(longArrayLength()));
        output.write(data);
    }
    private byte[] bytes() {
        FixedLengthBitStorage storage = new FixedLengthBitStorage(this.bits,longArrayLength() * Long.SIZE);
        for (Material block : this.blocks) {
            storage.write(paletteMap.get(block));
        }
        return storage.byteArray();
    }

    private static Material[] createPalette(@NotNull Material[] blocks) {
        return new HashSet<>(Arrays.asList(blocks)).toArray(Material[]::new);
    }

    private static Map<Material, Integer> createPaletteMap(@NotNull Material[] palette) {
        Map<Material, Integer> paletteMap = new HashMap<>(palette.length);
        for (int i = 0; i < palette.length; i++) {
            paletteMap.put(palette[i], i);
        }
        return paletteMap;
    }
}
