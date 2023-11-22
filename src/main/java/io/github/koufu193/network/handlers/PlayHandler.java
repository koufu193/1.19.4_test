package io.github.koufu193.network.handlers;


import io.github.koufu193.core.files.NBTFiles;
import io.github.koufu193.core.files.PlayerDataReader;
import io.github.koufu193.core.game.commands.nodes.LiteralCommandNode;
import io.github.koufu193.core.game.commands.nodes.RootCommandNode;
import io.github.koufu193.core.game.commands.nodes.arguments.IntegerArgumentNode;
import io.github.koufu193.core.game.commands.nodes.arguments.StringArgumentNode;
import io.github.koufu193.core.game.data.Difficulty;
import io.github.koufu193.core.game.data.Identifier;
import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.core.game.data.Material;
import io.github.koufu193.core.game.data.component.ChatColor;
import io.github.koufu193.core.game.data.component.TextComponent;
import io.github.koufu193.core.game.data.inventory.*;
import io.github.koufu193.core.game.data.item.ItemMeta;
import io.github.koufu193.core.game.data.item.ItemStack;
import io.github.koufu193.core.game.entities.Entity;
import io.github.koufu193.core.game.entities.Player;
import io.github.koufu193.core.game.entities.interfaces.IEntity;
import io.github.koufu193.core.game.entities.interfaces.IPlayer;
import io.github.koufu193.core.game.network.listener.ExecuteCommandPacketListener;
import io.github.koufu193.core.game.network.listener.KeepAlivePacketListener;
import io.github.koufu193.core.game.network.listener.PacketListener;
import io.github.koufu193.core.game.network.listener.PacketListeners;
import io.github.koufu193.core.game.network.listener.debug.UndefinedPacketAlerter;
import io.github.koufu193.core.game.world.World;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.handlers.play.KeepAliveHandler;
import io.github.koufu193.network.packets.AbstractPacket;
import io.github.koufu193.network.packets.play.*;
import io.github.koufu193.network.packets.play.channels.BrandChannel;
import io.github.koufu193.server.MinecraftServer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;
import org.jglrxavpok.hephaistos.nbt.NBT;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.*;

public class PlayHandler implements IHandler {
    private final Player player;
    private MinecraftServer server;
    public PlayHandler(@NotNull Player player) {
        this.player = player;
    }

    @Override
    public void handle(AsynchronousSocketChannel channel, MinecraftServer server) throws IOException, InterruptedException {
        this.server = server;
        player.packetHandler().sendPacket(makeLoginPacket());
        player.abilities().mayFly(true);
        player.packetHandler().sendPacket(new ClientboundSetDefaultSpawnPositionPacket(new Location(0, 0, 0), 0f));
        player.packetHandler().sendPacket(new ClientboundSetChunkCenterPacket(0, 0));
        player.packetHandler().sendPlayerAbilities(player.abilities());
        player.packetHandler().sendDifficulty(this.player.world().difficulty(), player.world().difficultyLocked());
        player.packetHandler().sendExpData(player.totalExpPoints(), player.expLevel(), player.expProgress());
        player.packetHandler().sendPluginMessage(new BrandChannel().brand("koufu"));
        player.packetHandler().teleport(player.location());
        player.packetHandler().sendPacket(new ClientboundWorldTimePacket(10L, -10L));
        /*for(int x=-5;x<=5;x++) {
            for(int z=-5;z<=5;z++) {
                if (26 < x * x + z * z) continue;
                player.handler().sendChunk(world.chunk(x,z));
            }
        }*/
        List<World> worlds=new ArrayList<>(server.worlds());
        worlds.forEach(a-> System.out.println(a.nameId()));
        player.chunkHandler().load(0, 0);
        player.teleport(player.location().y(256).x(5).z(5));
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
                        LiteralCommandNode.literal("kickme").then(
                                StringArgumentNode.quotableString("color").then(
                                        StringArgumentNode.quotableString("text").execute((executor, command) -> {
                                            player.kick(new TextComponent((String) command.args("text"), ChatColor.byString((String) command.args("color"))));
                                            server.quit(player);
                                        })
                                )
                        )
                ).then(
                        LiteralCommandNode.literal("held").then(
                                IntegerArgumentNode.integer("id", 0, 8).execute(((executor, command) -> {
                                    int value = (Integer) command.args("id");
                                    ((io.github.koufu193.core.game.entities.interfaces.IPlayer) executor).packetHandler().sendPacket(new ClientboundSetHeldSlotPacket((byte) value));
                                }))
                        )
                ).then(
                        LiteralCommandNode.literal("open").execute(((executor, command) -> {
                            IPlayer player1 = (IPlayer) executor;
                            Inventory inventory = new GenericInventory(9);
                            for (int i = 0; i < 9; i++) {
                                inventory.set(i, new ItemStack(Material.ACACIA_LOG, (int) (Math.random() * 10 + 1), ItemMeta.defaultItemMeta(Material.ACACIA_BOAT)));
                            }
                            player1.openInventory(new InventoryView(inventory, new TextComponent("hello")));
                        }))
                ).then(
                        LiteralCommandNode.literal("revChunk").then(
                                IntegerArgumentNode.integer("x").then(
                                        IntegerArgumentNode.integer("z").execute(((executor, command) -> {
                                            IPlayer player1 = (IPlayer) executor;
                                            player1.chunkHandler().load((Integer)command.args("x"),(Integer)command.args("z"));
                                        }))))
                )
        );
        List<ClientboundUpdatePlayerInfoPacket.PlayerActions> actionsList=new ArrayList<>();
        server.onlinePlayers().forEach(player1 -> {
            actionsList.add(
                    new ClientboundUpdatePlayerInfoPacket.PlayerActions(
                            player1.uniqueId(),
                            new ClientboundUpdatePlayerInfoPacket.AddPlayerAction(player1),
                            new ClientboundUpdatePlayerInfoPacket.UpdateDisplayNameAction(new TextComponent(player1.name())),
                            new ClientboundUpdatePlayerInfoPacket.ListPlayerAction(true)
                    )
            );
        });
        ClientboundUpdatePlayerInfoPacket p=new ClientboundUpdatePlayerInfoPacket(
                actionsList.toArray(ClientboundUpdatePlayerInfoPacket.PlayerActions[]::new)
        );
        server.onlinePlayers().forEach(player1 -> {
            player1.packetHandler().sendPacket(p);
        });
        server.onlinePlayers().forEach(player1->{
            if(player1==player) return;
            player1.packetHandler().sendPacket(new ClientboundJoinPlayerPacket(player));
            player.packetHandler().sendPacket(new ClientboundJoinPlayerPacket(player1));
        });
        player.inventory().set(PlayerInventory.PlayerArmor.HEAD, new ItemStack(Material.DIAMOND_HELMET, 1, ItemMeta.defaultItemMeta(Material.DIAMOND_HELMET)));
        Arrays.stream(player.inventory().getAllContents()).filter(a->a.type()!=Material.AIR).forEach(a-> System.out.println(a.type().id()));
        player.packetHandler().sendPacket(new ClientboundSetContainerContentsPacket((byte) 0, 0, new InventoryView(player.inventory()), null));
        player.setCommands(rootNode);
        AbstractPacket pak = null;
        PacketListeners listeners = this.player.packetHandler().listeners();
        listeners.add(new ExecuteCommandPacketListener());
        listeners.add(new KeepAlivePacketListener());
        listeners.add(new UndefinedPacketAlerter());
        new KeepAliveHandler(player).handleAsync();
        while ((pak = player.packetHandler().handlePacket()) != null && player.isOnline()) {
            if(pak instanceof ServerboundMovementPacket packet){
                Location o=player.location();
                player.location(packet.toLocation(player.location()),packet.onGround());
                AbstractPacket packet1;
                Location location=player.location();
                if(!player.chunkHandler().isLoaded(location.chunkX(),location.chunkZ())) player.chunkHandler().load(location.chunkX(),location.chunkZ());
                try {
                    packet1 = make(player, packet, o);
                }catch (IllegalArgumentException e){
                    packet1=new ClientboundTeleportEntityPacket(player);
                }
                AbstractPacket finalPacket = packet1;
                AbstractPacket oa=null;
                if(!(packet instanceof ServerboundSetPlayerPositionPacket)) oa=new ClientboundSetHeadRotationPacket(player);
                AbstractPacket finalOa = oa;
                server.onlinePlayers().forEach(player1 -> {
                    if(player1==player) return;
                    player1.packetHandler().sendPacket(finalPacket);
                    if(finalOa !=null) player1.packetHandler().sendPacket(finalOa);
                });
            }
        }
        while (player.isOnline()) {
        }
        channel.close();
    }
    private AbstractPacket make(@NotNull IEntity entity, @NotNull ServerboundMovementPacket packet,@NotNull Location before){
        if(packet instanceof ServerboundSetPlayerRotationPacket) return new ClientboundUpdateEntityRotationPacket(entity);
        if(packet instanceof ServerboundSetPlayerPositionPacket) return new ClientboundUpdateEntityPositionPacket(entity,before);
        return new ClientboundUpdateEntityPositionAndRotationPacket(entity,before);
    }

    private AbstractPacket makeLoginPacket() {
        return new ClientboundLoginPacket(this.player);
    }
}
