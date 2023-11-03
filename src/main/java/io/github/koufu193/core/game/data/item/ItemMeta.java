package io.github.koufu193.core.game.data.item;

import io.github.koufu193.core.game.data.Material;
import org.jetbrains.annotations.NotNull;
import org.jglrxavpok.hephaistos.nbt.NBTCompound;

public interface ItemMeta extends Cloneable{
    boolean isTypeOf(@NotNull Material material);
    Material material();
    NBTCompound toNBT();
    ItemMeta clone();
    static ItemMeta defaultItemMeta(@NotNull Material material){
        return defaultItemMeta(material,NBTCompound.EMPTY);
    }
    static ItemMeta defaultItemMeta(@NotNull Material material,@NotNull NBTCompound nbt){
        return new ItemMeta() {
            @Override
            public boolean isTypeOf(@NotNull Material material) {
                return true;
            }

            @Override
            public Material material() {
                return material;
            }

            @Override
            public NBTCompound toNBT() {
                return nbt;
            }

            @Override
            public ItemMeta clone() {
                return ItemMeta.defaultItemMeta(material,nbt.toMutableCompound().toCompound());
            }
        };
    }
}
