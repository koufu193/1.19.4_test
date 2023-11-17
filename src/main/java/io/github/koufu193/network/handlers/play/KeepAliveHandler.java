package io.github.koufu193.network.handlers.play;

import io.github.koufu193.core.game.entities.Player;
import io.github.koufu193.network.packets.play.ClientboundKeepAlivePacket;
import org.jetbrains.annotations.NotNull;

public class KeepAliveHandler{
    public Player player;
    public KeepAliveHandler(@NotNull Player player){
        this.player=player;
    }
    public void handleAsync() {
        new Thread(() -> {
            while (player.isOnline()){
                try {
                    player.packetHandler().sendPacketOrThrow(new ClientboundKeepAlivePacket(10));
                    Thread.sleep(1000);
                }catch (Throwable e){
                    e.printStackTrace();
                    break;
                }
            }
        }).start();
    }
}
