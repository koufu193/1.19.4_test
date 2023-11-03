package io.github.koufu193.network.handlers;


import io.github.koufu193.core.game.commands.Command;
import io.github.koufu193.core.game.commands.nodes.LiteralCommandNode;
import io.github.koufu193.core.game.commands.nodes.RootCommandNode;
import io.github.koufu193.core.game.commands.nodes.arguments.IntegerArgumentNode;
import io.github.koufu193.core.game.data.Identifier;
import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.core.game.data.Material;
import io.github.koufu193.core.game.data.component.TextComponent;
import io.github.koufu193.core.game.data.inventory.InventoryView;
import io.github.koufu193.core.game.data.inventory.PlayerInventory;
import io.github.koufu193.core.game.data.item.ItemMeta;
import io.github.koufu193.core.game.data.item.ItemStack;
import io.github.koufu193.core.game.entities.Player;
import io.github.koufu193.core.game.world.World;
import io.github.koufu193.exceptions.CommandException;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import io.github.koufu193.network.packets.play.*;
import io.github.koufu193.network.packets.play.channels.BrandChannel;
import io.github.koufu193.server.MinecraftServer;
import org.jetbrains.annotations.NotNull;
import org.jglrxavpok.hephaistos.nbt.NBT;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class PlayHandler implements IHandler {
    private final Player player;
    private MinecraftServer server;
    NBT registryCodec;

    public PlayHandler(@NotNull Player player) {
        this.player = player;
        try {
            registryCodec = DataTypes.NBT.decode(ByteBuffer.wrap(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("RegistryCodec.nbt"), "RegistryCodec.nbt not found").readAllBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void handle(AsynchronousSocketChannel channel, MinecraftServer server) throws IOException {
        this.server = server;
        player.abilities().mayFly(true);
        player.packetHandler().sendPacket(makeLoginPacket());
        player.packetHandler().sendPacket(new ClientboundSetDefaultSpawnPositionPacket(new Location(0, 0, 0), 0f));
        player.packetHandler().sendPacket(new ClientboundSetChunkCenterPacket(0, 0));
        player.packetHandler().sendPlayerAbilities(player.abilities());
        player.packetHandler().sendDifficulty(this.server.levelDat().difficulty(), this.server.levelDat().difficultyLocked());
        player.packetHandler().sendExpData(player.totalExpPoints(), player.expLevel(), player.expProgress());
        player.packetHandler().sendPluginMessage(new BrandChannel().brand("koufu"));
        player.packetHandler().teleport(player.location());
        World world = server.world("world");
        player.packetHandler().sendPacket(new ClientboundWorldTimePacket().fields(10L, 10L));
        /*for(int x=-5;x<=5;x++) {
            for(int z=-5;z<=5;z++) {
                if (26 < x * x + z * z) continue;
                player.handler().sendChunk(world.chunk(x,z));
            }
        }*/
        player.packetHandler().sendChunk(world.chunk(0, 0));
        player.teleport(player.location().y(256).x(5).z(5));
        AtomicBoolean bool = new AtomicBoolean(true);
        RootCommandNode rootNode = (
                RootCommandNode.root().then(
                        LiteralCommandNode.literal("ktp").then(
                                IntegerArgumentNode.integer("x").then(
                                        IntegerArgumentNode.integer("y").then(
                                                IntegerArgumentNode.integer("z").execute(((executor, command) -> {
                                                    Location location = new Location(player.world(), (Integer) command.args("x"), (Integer) command.args("y"), (Integer) command.args("z"));
                                                    player.teleport(location);
                                                    player.packetHandler().sendSystemMessage(new TextComponent(String.format("teleported:{%d,%d,%d}", location.blockX(), location.blockY(), location.blockZ())));
                                                }))
                                        )
                                )
                        )
                ).then(
                        LiteralCommandNode.literal("stop").execute(((executor, command) -> {
                            bool.set(false);
                        }))
                )
        );
        player.inventory().set(PlayerInventory.PlayerArmor.HEAD,new ItemStack(Material.DIAMOND_HELMET,1, ItemMeta.defaultItemMeta(Material.DIAMOND_HELMET)));
        player.packetHandler().sendPacket(new ClientboundSetContainerContentsPacket(0,new InventoryView(player.inventory(), (byte)0),null));
        player.packetHandler().sendCommandNode(rootNode);
        new KeepAliveHandler(player).handleAsync();
        AbstractPacket pak = null;
        while ((pak = player.packetHandler().read(PlayPackets.getPackets())) != null && bool.get()) {
            if (pak instanceof ServerboundMovementPacket packet) {
                if (!(pak instanceof ServerboundSetPlayerRotationPacket)) {
                    player.location(((ServerboundMovementPacket) pak).toLocation(player.location()));
                }
            } else if (pak instanceof ServerboundChatCommandPacket packet) {
                try {
                    Command command = Command.parse(packet.command(), rootNode);
                    command.node().execute(player, command);
                } catch (CommandException e) {
                    player.packetHandler().sendSystemMessage(e.messageComponent());
                }
            }
        }
        while (bool.get()) {
        }
        channel.close();
    }

    private AbstractPacket makeLoginPacket() {
        return new ClientboundLoginPacket().fields(player.entityId(), server.serverProperties().hardcore(), (byte) Player.GameMode.Creative.id(), (byte) (player.previousGameMode() != null ? player.previousGameMode().id() : -1),
                new Identifier[]{new Identifier("overworld"), new Identifier("overworld_caves"), new Identifier("the_nether"), new Identifier("the_end")}, registryCodec, new Identifier("overworld"), new Identifier("overworld"), (long) 1,
                server.serverProperties().maxPlayers(), 3, 5, false, server.gameRules().doImmediateRespawn.value(), false, false, false, null, null);
    }
}
