package io.github.koufu193.core.game.world.chunk.palette;

import io.github.koufu193.core.game.world.material.Material;
import io.github.koufu193.network.data.DataTypes;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class SingleValuePalette implements Palette{
    private final int id;
    public SingleValuePalette(String name){
        this(Material.fromKey(name));
    }
    public SingleValuePalette(int id){
        this.id=id;
    }
    @Override
    public byte bitsPerBlock() {
        return 0;
    }

    @Override
    public int longArrayLength() {
        return 0;
    }

    @Override
    public void write(OutputStream output) throws IOException {
        output.write(this.bitsPerBlock());
        output.write(DataTypes.VarInt.encode(this.id));
        output.write((byte)0);
    }
}
