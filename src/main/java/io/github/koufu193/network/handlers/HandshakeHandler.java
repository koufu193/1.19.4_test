package io.github.koufu193.network.handlers;

import io.github.koufu193.network.PacketDecoder;
import io.github.koufu193.network.packets.AbstractPacket;
import io.github.koufu193.network.packets.handshake.HandshakePacketRegistry;
import io.github.koufu193.server.MinecraftServer;

import java.io.IOException;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class HandshakeHandler implements IHandler{
    @Override
    public void handle(AsynchronousSocketChannel channel, MinecraftServer server) throws IOException, ExecutionException, InterruptedException, TimeoutException {
        AbstractPacket packet= PacketDecoder.getDefaultDecoder().decode(channel, HandshakePacketRegistry.getRegistry());
        int nextState=(Integer)packet.fields()[3];
        switch (nextState) {
            case 1 -> new StatusHandler().handle(channel, server);
            case 2 -> new LoginHandler().handle(channel, server);
            default -> throw new IllegalStateException("invalid state id of " + nextState);
        }
        if(channel.isOpen()) {
            channel.close();
        }
    }
}
