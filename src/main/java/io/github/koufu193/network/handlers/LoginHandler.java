package io.github.koufu193.network.handlers;

import io.github.koufu193.core.game.entities.Player;
import io.github.koufu193.network.PacketDecoder;
import io.github.koufu193.network.packets.login.ClientboundLoginSuccessPacket;
import io.github.koufu193.network.packets.login.ClientboundSetCompressionPacket;
import io.github.koufu193.network.packets.login.LoginPackets;
import io.github.koufu193.network.packets.login.ServerboundLoginStartPacket;
import io.github.koufu193.server.MinecraftServer;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class LoginHandler implements IHandler{
    @Override
    public void handle(@NotNull AsynchronousSocketChannel channel,@NotNull MinecraftServer server) throws IOException, ExecutionException, InterruptedException, TimeoutException {
        ServerboundLoginStartPacket packet = (ServerboundLoginStartPacket) PacketDecoder.getDecoder().decode(channel, LoginPackets.getPackets());
        Player player=server.join(packet.toProfile(),channel);
        System.out.printf("uuid:%s name:%s logging%n",player.uniqueId(),player.name());
        startCompress(player,server.serverProperties().networkCompressionThreshold());
        player.packetHandler().sendPacket(new ClientboundLoginSuccessPacket(packet.toProfile()));
        loginSuccess(player,server);
        server.quit(player);
    }
    private void loginSuccess(@NotNull Player player, @NotNull MinecraftServer server) throws IOException, ExecutionException, InterruptedException, TimeoutException {
        new PlayHandler(player).handle(player.channel(),server);
    }
    private void loginFailed(AsynchronousSocketChannel channel){

    }
    private void startCompress(Player player, int threshold) {
        player.packetHandler().sendPacket(new ClientboundSetCompressionPacket().fields(threshold));
        player.packetHandler().compressionSize(threshold);
    }
}
