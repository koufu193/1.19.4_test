package io.github.koufu193.core.game.entities.interfaces;

import io.github.koufu193.core.game.data.Location;
import org.jetbrains.annotations.NotNull;
import org.jglrxavpok.hephaistos.nbt.mutable.MutableNBTCompound;

import java.util.UUID;

public interface IEntity {
    int entityId();
    UUID uniqueId();
    String customName();
    MutableNBTCompound createMutableNBT();
    Location location();
    boolean customNameVisitable();
    void customName(String customName);
    void customNameVisitable(boolean visitable);
    boolean onGround();
    //motions
    void teleport(@NotNull Location location);
}
