package io.github.koufu193.core.game.network.listener;

import io.github.koufu193.core.game.entities.Player;
import io.github.koufu193.network.packets.AbstractPacket;
import org.jetbrains.annotations.NotNull;

public interface PacketListener {
    /**
     * @param packet received packet(only serverbound)
     */
    default void onReceive(@NotNull Player player,@NotNull AbstractPacket packet){}

    /**
     * @param player player
     * @param packet sent packet(only clientbound)
     */
    default void afterSend(@NotNull Player player, @NotNull AbstractPacket packet){}
}
