package io.github.koufu193.util;

import io.github.koufu193.core.game.data.Location;
import org.jetbrains.annotations.NotNull;
import org.jglrxavpok.hephaistos.collections.ImmutableIntArray;
import org.jglrxavpok.hephaistos.nbt.*;

import java.nio.ByteBuffer;
import java.util.UUID;

public final class NBTUtils {
    public static NBTList<NBTDouble> toDoubleNBTList(@NotNull double... array){
        return NBT.List(NBTType.TAG_Double,array.length,(i)->NBT.Double(array[i]));
    }
    public static NBTList<NBTFloat> toFloatNBTList(@NotNull float... array){
        return NBT.List(NBTType.TAG_Float,array.length,(i)->NBT.Float(array[i]));
    }
    public static NBTIntArray convertUUIDtoIntsNBT(@NotNull UUID uuid){
        ByteBuffer buffer=ByteBuffer.allocate(Long.BYTES*2).mark().putLong(uuid.getMostSignificantBits()).putLong(uuid.getLeastSignificantBits()).reset();
        return NBT.IntArray(buffer.getInt(),buffer.getInt(),buffer.getInt(),buffer.getInt());
    }
    public static UUID convertIntsNBTToUUID(@NotNull ImmutableIntArray uuidInts){
        ByteBuffer buffer=ByteBuffer.allocate(Integer.BYTES*4)
                .putInt(uuidInts.get(0))
                .putInt(uuidInts.get(1))
                .putInt(uuidInts.get(2))
                .putInt(uuidInts.get(3));
        return UUID.nameUUIDFromBytes(buffer.array());
    }
    public static Location convertDoublePositionToLocation(@NotNull NBTList<NBT> pos){
        return new Location((Double) pos.get(0).getValue(),(Double)pos.get(1).getValue(),(Double)pos.get(2).getValue());
    }
    public static Location convertFloatRotationToLocation(@NotNull NBTList<NBT> rotation){
        return new Location(0,0,0,(Float)rotation.get(0).getValue(),(Float)rotation.get(1).getValue());
    }
}
