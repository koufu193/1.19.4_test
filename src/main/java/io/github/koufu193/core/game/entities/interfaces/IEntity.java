package io.github.koufu193.core.game.entities.interfaces;

import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.server.MinecraftServer;
import io.github.koufu193.util.ConvertibleToNBTCompound;
import org.jetbrains.annotations.NotNull;
import org.jglrxavpok.hephaistos.nbt.mutable.MutableNBTCompound;

import java.util.UUID;

public interface IEntity extends ConvertibleToNBTCompound {
    int entityId();
    @NotNull
    UUID uniqueId();
    String customName();
    @NotNull
    Location location();
    boolean customNameVisitable();
    void customName(String customName);
    void customNameVisitable(boolean visitable);
    boolean onGround();
    //motions
    void teleport(@NotNull Location location);
    @NotNull
    MinecraftServer server();
}
