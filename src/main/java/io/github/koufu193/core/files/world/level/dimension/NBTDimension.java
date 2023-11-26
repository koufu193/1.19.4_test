package io.github.koufu193.core.files.world.level.dimension;

import io.github.koufu193.core.game.data.Identifier;
import io.github.koufu193.core.files.world.level.generation.NBTDimensionGenerator;
import io.github.koufu193.util.ConvertibleToNBTCompound;
import org.jetbrains.annotations.NotNull;
import org.jglrxavpok.hephaistos.nbt.NBT;
import org.jglrxavpok.hephaistos.nbt.NBTCompound;

import java.util.Objects;

public record NBTDimension(@NotNull Identifier type, @NotNull NBTDimensionGenerator generator) implements ConvertibleToNBTCompound {
    private static final String TYPE="type";
    private static final String GENERATOR="generator";
    public NBTCompound toCompound(){
        return NBT.Compound((compound)->{
            compound.put(NBTDimension.TYPE,NBT.String(String.valueOf(this.type)));
            compound.put(NBTDimension.GENERATOR,this.generator.toCompound());
        });
    }
    public NBTDimension(@NotNull NBTCompound nbt){
        this(
                new Identifier(Objects.requireNonNull(nbt.getString(NBTDimension.TYPE),"Type Not Found")),
                new NBTDimensionGenerator(Objects.requireNonNull(nbt.getCompound(NBTDimension.GENERATOR),"Generator Not Found"))
        );
    }
}
