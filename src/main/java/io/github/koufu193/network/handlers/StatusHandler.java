package io.github.koufu193.network.handlers;

import io.github.koufu193.network.PacketDecoder;
import io.github.koufu193.network.PacketEncoder;
import io.github.koufu193.network.packets.AbstractPacket;
import io.github.koufu193.network.packets.status.ClientboundPingResponsePacket;
import io.github.koufu193.network.packets.status.ClientboundStatusResponsePacket;
import io.github.koufu193.network.packets.status.ServerboundStatusRequestPacket;
import io.github.koufu193.network.packets.status.StatusPackets;
import io.github.koufu193.server.MinecraftServer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class StatusHandler implements IHandler {
    @Override
    public void handle(AsynchronousSocketChannel channel, MinecraftServer server) throws IOException, ExecutionException, InterruptedException, TimeoutException {
        for (int i = 0; i < 2; i++) {
            AbstractPacket packet = PacketDecoder.getDecoder().decode(channel, StatusPackets.getPackets());
            if (packet instanceof ServerboundStatusRequestPacket) {
                byte[] statusResponse = PacketEncoder.getEncoder().encode(generateStatusResponsePacket());
                channel.write(ByteBuffer.wrap(statusResponse));
            } else {
                channel.write(ByteBuffer.wrap(PacketEncoder.getEncoder().encode(generatePingResponsePacket((Long) packet.fields()[0]))));
            }
        }
    }

    private ClientboundStatusResponsePacket generateStatusResponsePacket() {
        ClientboundStatusResponsePacket packet = new ClientboundStatusResponsePacket();
        packet.fields("""
                    {
                    "version": {
                    "name": "1.19.4",
                    "protocol": 762
                    },
                    "players": {
                    "max": 100,
                    "online": 5,
                    "sample": []
                    },
                    "description": {
                    "text": "hello"
                    }
                    }
                    """);
        return packet;
    }

    private ClientboundPingResponsePacket generatePingResponsePacket(long l) {
        ClientboundPingResponsePacket packet = new ClientboundPingResponsePacket();
        packet.fields(l);
        return packet;
    }
}
