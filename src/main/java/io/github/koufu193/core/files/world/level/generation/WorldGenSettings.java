package io.github.koufu193.core.files.world.level.generation;

import io.github.koufu193.core.game.data.Identifier;
import io.github.koufu193.core.files.world.level.dimension.NBTDimension;
import io.github.koufu193.util.ConvertibleToNBTCompound;
import org.jetbrains.annotations.NotNull;
import org.jglrxavpok.hephaistos.nbt.NBT;
import org.jglrxavpok.hephaistos.nbt.NBTCompound;
import org.jglrxavpok.hephaistos.nbt.NBTCompoundLike;

import java.util.*;

public final class WorldGenSettings implements ConvertibleToNBTCompound {
    private static final String BONUS_CHEST = "bonus_chest";
    private static final String GENERATE_FEATURES = "generate_features";
    private static final String SEED = "seed";
    private static final String DIMENSIONS = "dimensions";

    private final long seed;
    private final boolean hasBonusChest;
    private final boolean generateFeatures;
    private final long hashedSeed;
    private final Map<Identifier, NBTDimension> dimensions;
    public WorldGenSettings(@NotNull NBTCompoundLike nbt){
        this(
                Objects.requireNonNull(nbt.getLong(WorldGenSettings.SEED),"Seed Not Found"),
                Objects.requireNonNull(nbt.getBoolean(WorldGenSettings.BONUS_CHEST),"Bonus Chest Not Found"),
                Objects.requireNonNull(nbt.getBoolean(WorldGenSettings.GENERATE_FEATURES),"Generate Features Not Found"),
                parseDimensions(Objects.requireNonNull(nbt.getCompound(WorldGenSettings.DIMENSIONS),"Dimensions Not Found"))
        );
    }
    private static Map<Identifier, NBTDimension> parseDimensions(@NotNull NBTCompound nbt){
        Set<String> dimensionsKeys=nbt.getKeys();
        Map<Identifier, NBTDimension> dimensions=new HashMap<>(dimensionsKeys.size());
        dimensionsKeys.forEach(key-> dimensions.put(new Identifier(key),new NBTDimension(nbt.getCompound(key))));
        return dimensions;
    }

    public WorldGenSettings(long seed, boolean hasBonusChest, boolean generateFeatures, @NotNull Map<Identifier, NBTDimension> dimensions) {
        this.seed = seed;
        this.hasBonusChest = hasBonusChest;
        this.generateFeatures = generateFeatures;
        this.dimensions = Collections.unmodifiableMap(dimensions);
        this.hashedSeed = hashSeed(seed);
    }

    public boolean hasBonusChest() {
        return hasBonusChest;
    }

    public boolean generateFeatures() {
        return generateFeatures;
    }

    public long hashedSeed() {
        return hashedSeed;
    }

    public long seed() {
        return seed;
    }

    @NotNull
    public NBTCompound toCompound() {
        return NBT.Compound((compound) -> {
            compound.put(WorldGenSettings.BONUS_CHEST, NBT.Boolean(this.hasBonusChest));
            compound.put(WorldGenSettings.SEED, NBT.Long(this.seed));
            compound.put(WorldGenSettings.GENERATE_FEATURES, NBT.Boolean(this.generateFeatures));
            compound.put(
                    WorldGenSettings.DIMENSIONS,
                    NBT.Compound(
                            dimensions -> this.dimensions.forEach((id, NBTDimension) -> dimensions.put(String.valueOf(id), NBTDimension.toCompound()))
                    )
            );
        });
    }

    public Map<Identifier, NBTDimension> dimensions() {
        return dimensions;
    }

    private static long hashSeed(long seed) {
        return seed;
    }
}
