package io.github.koufu193.network.handlers;

import io.github.koufu193.core.game.entities.Player;
import io.github.koufu193.core.game.entities.interfaces.IPlayer;
import io.github.koufu193.network.packets.play.ClientboundKeepAlivePacket;
import io.github.koufu193.server.MinecraftServer;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

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
