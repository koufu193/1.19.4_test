package io.github.koufu193.network.packets.play;

import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.network.packets.AbstractPacket;
import org.jetbrains.annotations.NotNull;

public abstract class ServerboundMovementPacket extends AbstractPacket {
    public abstract Location toLocation(@NotNull Location defaultValue);
    public abstract boolean onGround();
}
