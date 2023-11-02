package io.github.koufu193.core.game.world.chunk.palette;

import io.github.koufu193.core.game.data.Identifier;
import io.github.koufu193.core.game.data.Material;
import io.github.koufu193.network.data.DataTypes;

import java.io.IOException;
import java.io.OutputStream;

public class SingleValuePalette implements Palette{
    private final int id;
    public SingleValuePalette(String name){
        this(Material.fromId(name).blockId());
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
