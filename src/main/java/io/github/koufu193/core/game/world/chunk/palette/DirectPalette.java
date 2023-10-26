package io.github.koufu193.core.game.world.chunk.palette;

import io.github.koufu193.core.game.world.material.Material;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.util.BitStorage;
import org.jglrxavpok.hephaistos.mca.BlockState;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

public class DirectPalette implements Palette {
    private final int bits;
    private final BlockState[] states;

    public DirectPalette(BlockState[] states) {
        this.bits = Palette.FULL_BITS_PER_BLOCK;
        this.states = Arrays.copyOf(states, states.length);
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
        output.write(DataTypes.VarInt.encode(longArrayLength()));
        output.write(data);
    }
    private byte[] bytes() {
        BitStorage storage = new BitStorage(longArrayLength() * Long.SIZE);
        int pad = 64 % this.bits;
        int num = 64 / this.bits;
        for (int i = 0; i < states.length; i++) {
            storage.write(this.bits, Material.fromKey(states[i].component1()));
            if ((i + 1) % num == 0) storage.write(pad, 0);
        }
        return storage.byteArray();
    }
}
