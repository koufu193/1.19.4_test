package io.github.koufu193.network.handlers;

import io.github.koufu193.core.game.data.component.TextComponent;
import io.github.koufu193.core.game.entities.Player;
import io.github.koufu193.network.PacketDecoder;
import io.github.koufu193.network.PacketEncoder;
import io.github.koufu193.network.packets.login.*;
import io.github.koufu193.server.MinecraftServer;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class LoginHandler implements IHandler{
    @Override
    public void handle(@NotNull AsynchronousSocketChannel channel,@NotNull MinecraftServer server) throws IOException, ExecutionException, InterruptedException, TimeoutException {
        ServerboundLoginStartPacket packet = (ServerboundLoginStartPacket) PacketDecoder.getDefaultDecoder().decode(channel, LoginPacketRegistry.getRegistry());
        Player player=server.join(packet.toProfile(),channel);
        System.out.printf("uuid:%s name:%s logging%n",player.uniqueId(),player.name());
        startCompression(player,server.serverProperties().networkCompressionThreshold());
        loginSuccess(player,server);
        server.onQuit(player);
    }
    private void loginSuccess(@NotNull Player player, @NotNull MinecraftServer server) throws IOException, ExecutionException, InterruptedException, TimeoutException {
        player.packetHandler().sendPacket(new ClientboundLoginSuccessPacket(player.profile()));
        new PlayHandler(player).handle(player.channel(),server);
    }
    private void loginFailed(@NotNull AsynchronousSocketChannel channel,@NotNull String reason) throws IOException {
        this.loginFailed(channel,new TextComponent(reason));
    }
    private void loginFailed(@NotNull AsynchronousSocketChannel channel, @NotNull TextComponent reason) throws IOException {
        channel.write(ByteBuffer.wrap(PacketEncoder.getDefaultEncoder().encode(new ClientboundDisconnectPacket(reason))));
        channel.close();
    }
    private void startCompression(Player player, int threshold) {
        player.packetHandler().sendPacket(new ClientboundSetCompressionPacket(threshold));
        player.packetHandler().compressionSize(threshold);
    }
}
