package io.github.koufu193.core.files.world.level.generation;

import io.github.koufu193.core.game.data.Identifier;
import io.github.koufu193.core.files.world.level.BiomeSource;
import io.github.koufu193.util.ConvertibleToNBTCompound;
import org.jetbrains.annotations.NotNull;
import org.jglrxavpok.hephaistos.nbt.NBT;
import org.jglrxavpok.hephaistos.nbt.NBTCompound;

import java.util.Objects;

public record NBTDimensionGenerator(@NotNull Identifier type, @NotNull Identifier settings, @NotNull BiomeSource biomeSource) implements ConvertibleToNBTCompound {
    private static final String TYPE="type";
    private static final String SETTINGS="settings";
    private static final String BIOME_SOURCE="biome_source";
    public NBTDimensionGenerator(@NotNull Identifier type, @NotNull Identifier settings, @NotNull BiomeSource biomeSource){
        this.type=type;
        this.settings=settings;
        this.biomeSource=biomeSource;
    }
    public NBTDimensionGenerator(@NotNull NBTCompound nbt){
        this(
                new Identifier(Objects.requireNonNull(nbt.getString(NBTDimensionGenerator.TYPE),"Type Not Found")),
                new Identifier(Objects.requireNonNull(nbt.getString(NBTDimensionGenerator.SETTINGS),"Settings Not Found")),
                new BiomeSource(Objects.requireNonNull(nbt.getCompound(NBTDimensionGenerator.BIOME_SOURCE),"Biome Source Not Found"))
        );
    }
    public NBTCompound toCompound(){
        return NBT.Compound((compound)->{
           compound.put(NBTDimensionGenerator.TYPE,NBT.String(String.valueOf(this.type)));
           compound.put(NBTDimensionGenerator.SETTINGS,NBT.String(String.valueOf(this.settings)));
           compound.put(NBTDimensionGenerator.BIOME_SOURCE,this.biomeSource.toCompound());
        });
    }
}
