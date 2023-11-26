package io.github.koufu193.util;

import org.jglrxavpok.hephaistos.nbt.NBTCompound;

/**
 * {@link NBTCompound}に変換可能なクラスであることを示す
 */
public interface ConvertibleToNBTCompound {
    NBTCompound toCompound();
}
