package io.github.koufu193.core.game.network.listener;

import io.github.koufu193.core.game.entities.Player;
import io.github.koufu193.network.packets.AbstractPacket;
import io.github.koufu193.network.packets.play.InventoryPacket;
import org.jetbrains.annotations.NotNull;

public class InventoryPacketListener implements PacketListener {
    @Override
    public void onReceive(@NotNull Player player, @NotNull AbstractPacket packet) {
        if(!(packet instanceof InventoryPacket packet1)) return;

    }
}
