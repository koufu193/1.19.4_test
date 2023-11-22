package io.github.koufu193.core.files;

import io.github.koufu193.network.data.DataTypes;
import org.jglrxavpok.hephaistos.nbt.NBT;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Objects;

public final class NBTFiles {
    private static final NBT registryCodec;
    static {
        try {
            registryCodec = DataTypes.NBT.decode(ByteBuffer.wrap(Objects.requireNonNull(NBTFiles.class.getClassLoader().getResourceAsStream("RegistryCodec.nbt"), "RegistryCodec.nbt not found").readAllBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static NBT registryCodec() {
        return registryCodec;
    }
}
