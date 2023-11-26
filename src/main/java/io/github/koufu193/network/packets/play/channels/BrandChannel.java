package io.github.koufu193.network.packets.play.channels;

import io.github.koufu193.core.game.data.Identifier;
import io.github.koufu193.network.data.DataTypes;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;

/**
 * ブランドチャネル
 */
public class BrandChannel implements PluginChannel {
    private String brand;
    BrandChannel(){}

    /**
     * @param brand ブランド名
     */
    public BrandChannel(@NotNull String brand){
        this.brand=brand;
    }
    @NotNull
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

    /**
     * ブランド名を取得
     * @return ブランド名
     */

    public String brand() {
        return this.brand;
    }
}
