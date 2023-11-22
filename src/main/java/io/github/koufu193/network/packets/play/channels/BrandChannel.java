package io.github.koufu193.network.packets.play.channels;

import io.github.koufu193.core.game.data.Identifier;
import io.github.koufu193.network.data.DataTypes;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;

public class BrandChannel implements IPluginChannel{
    private String brand;
    public BrandChannel(){}
    public BrandChannel(@NotNull String brand){
        brand(brand);
    }
    @Override
    public Identifier channel() {
        return new Identifier("minecraft:brand");
    }

    @Override
    public byte[] bytes() {
        return DataTypes.String.encode(this.brand);
    }

    @Override
    public void load(ByteBuffer buffer) {
        this.brand=DataTypes.String.decode(buffer);
    }

    public String brand() {
        return this.brand;
    }
    public BrandChannel brand(@NotNull String brand){
        this.brand=brand;
        return this;
    }
}
