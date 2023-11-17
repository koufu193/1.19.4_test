package io.github.koufu193.core.game.network.listener;

import io.github.koufu193.core.game.entities.Player;
import io.github.koufu193.network.packets.AbstractPacket;
import io.github.koufu193.network.packets.play.ServerboundChatCommandPacket;
import org.jetbrains.annotations.NotNull;

public class ExecuteCommandPacketListener implements PacketListener{
    @Override
    public void onReceive(@NotNull Player player,@NotNull AbstractPacket packet) {
        if(!(packet instanceof ServerboundChatCommandPacket packet1)) return;
        player.dispatchCommand(packet1.command());
    }
}
