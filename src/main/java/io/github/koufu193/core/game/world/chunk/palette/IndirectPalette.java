package io.github.koufu193.core.game.world.chunk.palette;

import io.github.koufu193.core.game.data.Identifier;
import io.github.koufu193.core.game.data.Material;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.util.BitStorage;
import org.jglrxavpok.hephaistos.mca.BlockState;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

public class IndirectPalette implements Palette {
    private final BlockState[] states;
    private final BlockState[] palette;
    private final Map<String, Integer> paletteMap;
    private final int bits;

    public IndirectPalette(BlockState[] states) {
        this(states, palette(states));
    }

    public IndirectPalette(BlockState[] states, BlockState[] palette) {
        Objects.requireNonNull(states, "states must not be null");
        Objects.requireNonNull(palette, "palette must not be null");
        this.states = states;
        this.palette = palette;
        this.bits = Math.max(4, calculateBits(palette.length));
        this.paletteMap = paletteMap(this.palette);
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
    public void write(OutputStream output) throws IOException {
        byte[] data = bytes();
        output.write(this.bits);
        output.write(DataTypes.VarInt.encode(this.palette.length));
        for (BlockState p : palette) {
            output.write(DataTypes.VarInt.encode(Material.fromId(p.component1()).blockId()));
        }
        output.write(DataTypes.VarInt.encode(longArrayLength()));
        output.write(data);
    }

    private byte[] bytes() {
        BitStorage storage = new BitStorage(longArrayLength() * Long.SIZE);
        int pad = 64 % this.bits;
        int num = 64 / this.bits;
        for (int i = 0; i < states.length; i++) {
            storage.write(this.bits, paletteMap.get(states[i].component1()));
            if ((i + 1) % num == 0) storage.write(pad, 0);
        }
        return storage.byteArray();
    }

    private static int calculateBits(int num) {
        return Integer.toBinaryString(num).length();
    }

    private static BlockState[] palette(BlockState[] states) {
        List<BlockState> palette = new ArrayList<>();
        for (BlockState state : states) {
            if (!palette.contains(state)) palette.add(state);
        }
        return palette.toArray(BlockState[]::new);
    }

    private static Map<String, Integer> paletteMap(BlockState[] palette) {
        Map<String, Integer> paletteMap = new HashMap<>(palette.length);
        for (int i = 0; i < palette.length; i++) {
            paletteMap.put(palette[i].component1(), i);
        }
        return paletteMap;
    }
}
