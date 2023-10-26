package io.github.koufu193.network.handlers;

import io.github.koufu193.core.game.data.GameProfile;
import io.github.koufu193.core.properties.Properties;
import io.github.koufu193.network.PacketDecoder;
import io.github.koufu193.network.PacketEncoder;
import io.github.koufu193.network.packets.AbstractPacket;
import io.github.koufu193.network.packets.login.ClientboundLoginSuccessPacket;
import io.github.koufu193.network.packets.login.ClientboundSetCompressionPacket;
import io.github.koufu193.network.packets.login.LoginPackets;
import io.github.koufu193.network.packets.login.ServerboundLoginStartPacket;
import io.github.koufu193.server.MinecraftServer;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class LoginHandler implements IHandler{
    @Override
    public void handle(@NotNull AsynchronousSocketChannel channel,@NotNull MinecraftServer server) throws IOException, ExecutionException, InterruptedException, TimeoutException {
        ServerboundLoginStartPacket packet = (ServerboundLoginStartPacket) PacketDecoder.getDecoder().decode(channel, LoginPackets.getPackets());
        startCompress(channel,server.serverProperties().networkCompressionThreshold());
        channel.write(ByteBuffer.wrap(PacketEncoder.getEncoder().encode(new ClientboundLoginSuccessPacket(packet.toProfile())))).get();
        loginSuccess(channel,server, packet.toProfile());
    }
    private void loginSuccess(AsynchronousSocketChannel channel, MinecraftServer server, GameProfile profile) throws IOException, ExecutionException, InterruptedException, TimeoutException {
        new PlayHandler(server.loadPlayer(profile,channel)).handle(channel,server);
    }
    private void loginFailed(AsynchronousSocketChannel channel){

    }
    private void startCompress(AsynchronousSocketChannel channel,int threshold) throws IOException, ExecutionException, InterruptedException {
        channel.write(ByteBuffer.wrap(PacketEncoder.getEncoder().encode(new ClientboundSetCompressionPacket().fields(threshold)))).get();
        PacketEncoder.getEncoder().setCompressionSize(threshold);
        PacketDecoder.getDecoder().setCompressionSize(threshold);
    }
}
