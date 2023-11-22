package io.github.koufu193.core.game.world.dimension;

import io.github.koufu193.core.game.data.Identifier;
import io.github.koufu193.core.game.world.generator.DimensionGenerator;
import io.github.koufu193.util.CompoundConvertible;
import org.jetbrains.annotations.NotNull;
import org.jglrxavpok.hephaistos.nbt.NBT;
import org.jglrxavpok.hephaistos.nbt.NBTCompound;

import java.util.Objects;

public record Dimension(@NotNull Identifier type,@NotNull DimensionGenerator generator) implements CompoundConvertible {
    private static final String TYPE="type";
    private static final String GENERATOR="generator";
    public NBTCompound toCompound(){
        return NBT.Compound((compound)->{
            compound.put(Dimension.TYPE,NBT.String(String.valueOf(this.type)));
            compound.put(Dimension.GENERATOR,this.generator.toCompound());
        });
    }
    public Dimension(@NotNull NBTCompound nbt){
        this(
                new Identifier(Objects.requireNonNull(nbt.getString(Dimension.TYPE),"Type Not Found")),
                new DimensionGenerator(Objects.requireNonNull(nbt.getCompound(Dimension.GENERATOR),"Generator Not Found"))
        );
    }
}
