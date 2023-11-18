package io.github.koufu193.core.game.network.listener.debug;

import io.github.koufu193.core.game.entities.Player;
import io.github.koufu193.core.game.network.listener.PacketListener;
import io.github.koufu193.network.packets.AbstractPacket;
import io.github.koufu193.network.packets.UndefinedPacket;
import org.jetbrains.annotations.NotNull;

public class UndefinedPacketAlerter implements PacketListener {
    @Override
    public void onReceive(@NotNull Player player, @NotNull AbstractPacket packet) {
        if(!isUndefinedPacket(packet)) return;
        System.out.println(Integer.toHexString(packet.packetId()));
    }

    @Override
    public void afterSend(@NotNull Player player, @NotNull AbstractPacket packet) {
        if(!isUndefinedPacket(packet)) return;
        System.out.println(Integer.toHexString(packet.packetId()));
    }
    private static boolean isUndefinedPacket(AbstractPacket packet){
        return packet instanceof UndefinedPacket;
    }
}
