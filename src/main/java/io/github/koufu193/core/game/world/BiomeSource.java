package io.github.koufu193.core.game.world;

import io.github.koufu193.core.game.data.Identifier;
import io.github.koufu193.util.CompoundConvertible;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jglrxavpok.hephaistos.nbt.NBT;
import org.jglrxavpok.hephaistos.nbt.NBTCompound;

import java.util.Objects;

public record BiomeSource(@NotNull Identifier type, @Nullable Identifier preset) implements CompoundConvertible {
    private static final String TYPE="type";
    private static final String PRESET ="preset";
    public static final Identifier FLAT=new Identifier("minecraft:flat");
    public static final Identifier DEBUG=new Identifier("minecraft:debug");
    public BiomeSource(@NotNull NBTCompound nbt){
        this(
                new Identifier(Objects.requireNonNull(nbt.getString(BiomeSource.TYPE),"Type Not Found")),
                parsePreset(nbt)
        );
    }
    @Nullable
    private static Identifier parsePreset(@NotNull NBTCompound nbt){
        if(!nbt.containsKey(BiomeSource.PRESET)) return null;
        return new Identifier(nbt.getString(BiomeSource.PRESET));
    }
    @Override
    public NBTCompound toCompound() {
        return NBT.Compound((compound)->{
            compound.put(BiomeSource.TYPE,NBT.String(String.valueOf(this.type)));
            if(preset!=null) compound.put(BiomeSource.PRESET,NBT.String(String.valueOf(this.preset)));
        });
    }
}
