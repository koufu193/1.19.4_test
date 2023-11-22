package io.github.koufu193.core.game.world.generator;

import io.github.koufu193.core.game.data.Identifier;
import io.github.koufu193.core.game.world.BiomeSource;
import io.github.koufu193.util.CompoundConvertible;
import org.jetbrains.annotations.NotNull;
import org.jglrxavpok.hephaistos.nbt.NBT;
import org.jglrxavpok.hephaistos.nbt.NBTCompound;
import org.jglrxavpok.hephaistos.nbt.NBTType;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

public record DimensionGenerator(@NotNull Identifier type,@NotNull Identifier settings,@NotNull BiomeSource biomeSource) implements CompoundConvertible {
    private static final String TYPE="type";
    private static final String SETTINGS="settings";
    private static final String BIOME_SOURCE="biome_source";
    public DimensionGenerator(@NotNull Identifier type, @NotNull Identifier settings, @NotNull BiomeSource biomeSource){
        this.type=type;
        this.settings=settings;
        this.biomeSource=biomeSource;
    }
    public DimensionGenerator(@NotNull NBTCompound nbt){
        this(
                new Identifier(Objects.requireNonNull(nbt.getString(DimensionGenerator.TYPE),"Type Not Found")),
                new Identifier(Objects.requireNonNull(nbt.getString(DimensionGenerator.SETTINGS),"Settings Not Found")),
                new BiomeSource(Objects.requireNonNull(nbt.getCompound(DimensionGenerator.BIOME_SOURCE),"Biome Source Not Found"))
        );
    }
    public NBTCompound toCompound(){
        return NBT.Compound((compound)->{
           compound.put(DimensionGenerator.TYPE,NBT.String(String.valueOf(this.type)));
           compound.put(DimensionGenerator.SETTINGS,NBT.String(String.valueOf(this.settings)));
           compound.put(DimensionGenerator.BIOME_SOURCE,this.biomeSource.toCompound());
        });
    }
}
