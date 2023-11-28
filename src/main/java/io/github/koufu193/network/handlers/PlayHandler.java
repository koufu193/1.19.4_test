package io.github.koufu193.network.handlers;


import io.github.koufu193.core.game.commands.nodes.LiteralCommandNode;
import io.github.koufu193.core.game.commands.nodes.RootCommandNode;
import io.github.koufu193.core.game.commands.nodes.arguments.IntegerArgumentNode;
import io.github.koufu193.core.game.commands.nodes.arguments.StringArgumentNode;
import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.core.game.data.Material;
import io.github.koufu193.core.game.data.block.BlockMeta;
import io.github.koufu193.core.game.data.component.ChatColor;
import io.github.koufu193.core.game.data.component.TextComponent;
import io.github.koufu193.core.game.data.inventory.*;
import io.github.koufu193.core.game.data.item.ItemMeta;
import io.github.koufu193.core.game.data.item.ItemStack;
import io.github.koufu193.core.game.entities.Player;
import io.github.koufu193.core.game.entities.interfaces.IEntity;
import io.github.koufu193.core.game.entities.interfaces.IPlayer;
import io.github.koufu193.core.game.network.listener.ExecuteCommandPacketListener;
import io.github.koufu193.core.game.network.listener.KeepAlivePacketListener;
import io.github.koufu193.core.game.network.listener.PacketListeners;
import io.github.koufu193.core.game.network.listener.debug.UndefinedPacketAlerter;
import io.github.koufu193.core.game.world.World;
import io.github.koufu193.core.game.world.chunk.block.Block;
import io.github.koufu193.network.handlers.play.KeepAliveHandler;
import io.github.koufu193.network.packets.AbstractPacket;
import io.github.koufu193.network.packets.play.*;
import io.github.koufu193.network.packets.play.channels.BrandChannel;
import io.github.koufu193.server.MinecraftServer;
import org.jetbrains.annotations.NotNull;
import org.jglrxavpok.hephaistos.nbt.NBTCompound;

import java.io.IOException;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

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
        player.packetHandler().sendPluginMessage(new BrandChannel("koufu"));
        player.packetHandler().teleport(player.location());
        player.packetHandler().sendPacket(new ClientboundWorldTimePacket(10L, -10L));
        player.teleport(player.location().y(256).x(5).z(5));
        AtomicBoolean zz=new AtomicBoolean(false);
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
                                            server.onQuit(player);
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
                                            player1.chunkSender().load((Integer)command.args("x"),(Integer)command.args("z"));
                                        }))))
                ).then(LiteralCommandNode.literal("change").execute(((executor, command) -> {
                    zz.set(!zz.get());
                })))
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
        player.inventory().set(3,new ItemStack(Material.DEBUG_STICK,1,ItemMeta.defaultItemMeta(Material.DEBUG_STICK)));
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
                if(!player.chunkSender().isLoaded(location.chunkX(),location.chunkZ())) player.chunkSender().load(location.chunkX(),location.chunkZ());
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
                Block it=player.location().add(0,-1,0).getBlock();
                if(it!=null){
                    if(!zz.get()&&it.type().isAir()) {
                        int v=(int) (Math.random()*200)*2+1;
                        player.packetHandler().sendPacket(new ClientboundBlockUpdatePacket(it.location(), v));
                        player.location().chunk().setBlock(it.location().blockX(), it.location().blockY(), it.location().blockZ(), new BlockMeta() {
                            @Override
                            public Material material() {
                                return Material.GOLD_BLOCK;
                            }

                            @Override
                            public NBTCompound toCompound() {
                                return NBTCompound.EMPTY;
                            }
                        });
                    } else if (zz.get()) {
                        player.packetHandler().sendPacket(new ClientboundBlockUpdatePacket(it.location(), Material.AIR));
                        player.location().chunk().setBlock(player.location().blockX(), player.location().blockY() - 1, player.location().blockZ(), new BlockMeta() {
                            @Override
                            public Material material() {
                                return Material.AIR;
                            }

                            @Override
                            public NBTCompound toCompound() {
                                return NBTCompound.EMPTY;
                            }
                        });
                    }
                }
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
