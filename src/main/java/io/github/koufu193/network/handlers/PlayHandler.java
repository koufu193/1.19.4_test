package io.github.koufu193.network.handlers;


import io.github.koufu193.core.game.commands.nodes.LiteralCommandNode;
import io.github.koufu193.core.game.commands.nodes.RootCommandNode;
import io.github.koufu193.core.game.data.Identifier;
import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.core.game.entities.Player;
import io.github.koufu193.core.game.world.World;
import io.github.koufu193.network.PacketDecoder;
import io.github.koufu193.network.PacketEncoder;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import io.github.koufu193.network.packets.play.*;
import io.github.koufu193.network.packets.play.channels.BrandChannel;
import io.github.koufu193.network.packets.play.channels.ServerboundChatCommandPacket;
import io.github.koufu193.server.MinecraftServer;
import org.jglrxavpok.hephaistos.nbt.*;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class PlayHandler implements IHandler {
    private final Player player;
    private MinecraftServer server;
    NBT registryCodec;

    public PlayHandler(Player player) {
        this.player = player;
        try {
            registryCodec = DataTypes.NBT.decode(ByteBuffer.wrap(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("RegistryCodec.nbt"),"RegistryCodec.nbt not found").readAllBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void handle(AsynchronousSocketChannel channel, MinecraftServer server) throws IOException, ExecutionException, InterruptedException, TimeoutException {
        this.server = server;
        player.abilities().mayFly(true);
        channel.write(ByteBuffer.wrap(PacketEncoder.getEncoder().encode(makeLoginPacket()))).get();
        channel.write(ByteBuffer.wrap(PacketEncoder.getEncoder().encode(new ClientboundSetDefaultSpawnPositionPacket().fields(Location.from(1), 1f)))).get();
        channel.write(ByteBuffer.wrap(PacketEncoder.getEncoder().encode(new ClientboundSetChunkCenterPacket(0, 0)))).get();
        player.packetHandler().sendPlayerAbilities(player.abilities());
        player.packetHandler().sendDifficulty(this.server.levelDat().difficulty(), this.server.levelDat().difficultyLocked());
        player.packetHandler().sendExpData(player.totalExpPoints(), player.expLevel(), player.expProgress());
        player.packetHandler().sendPluginMessage(new BrandChannel().brand("koufu"));
        player.packetHandler().teleport(player.location());
        World world = server.world("world");
        channel.write(ByteBuffer.wrap(PacketEncoder.getEncoder().encode(new ClientboundWorldTimePacket().fields(10L, 10L))));
        /*for(int x=-5;x<=5;x++) {
            for(int z=-5;z<=5;z++) {
                if (26 < x * x + z * z) continue;
                player.handler().sendChunk(world.chunk(x,z));
            }
        }*/
        player.packetHandler().sendChunk(world.chunk(0,0));
        player.teleport(player.location().y(256).x(5).z(5));
        player.packetHandler().sendCommandNode(
                RootCommandNode.root().then(root ->
                        LiteralCommandNode.literal("hello").then(
                                LiteralCommandNode.literal("aiueo").then(LiteralCommandNode.literal("what")).redirect(root)
                        )
                ).then(
                        LiteralCommandNode.literal("what")
                )
        );
        AbstractPacket pak=null;
        while((pak=PacketDecoder.getDecoder().decode(channel,PlayPackets.getPackets()))!=null) {
            if (pak instanceof ServerboundSetPlayerPositionAndRotationPacket packet) {
                Location location = packet.toLocation();
                if (player.movementHandler().canMoveTo(location)) player.location(location);
            }else if(pak instanceof ServerboundChatCommandPacket){
                System.out.println(pak);
            }
        }
        while (true) {
            channel.write(ByteBuffer.wrap(PacketEncoder.getEncoder().encode(new ClientboundKeepAlivePacket().id(0)))).get();
            Thread.sleep(1000);
        }
    }

    private AbstractPacket makeLoginPacket() {
        return new ClientboundLoginPacket().fields(player.entityId(), server.serverProperties().hardcore(), (byte) Player.GameMode.Creative.id(), (byte) (player.previousGameMode() != null ? player.previousGameMode().id() : -1),
                new Identifier[]{Identifier.from("overworld"), Identifier.from("overworld_caves"), Identifier.from("the_nether"), Identifier.from("the_end")}, registryCodec, Identifier.from("overworld"), Identifier.from("overworld"), (long) 1,
                server.serverProperties().maxPlayers(), 3, 5, false, server.gameRules().doImmediateRespawn.value(), false, false, false, null, null);
    }
}
