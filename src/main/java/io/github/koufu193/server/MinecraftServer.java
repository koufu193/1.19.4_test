package io.github.koufu193.server;

import io.github.koufu193.core.files.PlayerDataReader;
import io.github.koufu193.core.files.ServerProperties;
import io.github.koufu193.core.files.UserCaches;
import io.github.koufu193.core.game.data.Difficulty;
import io.github.koufu193.core.game.data.GameProfile;
import io.github.koufu193.core.game.data.Identifier;
import io.github.koufu193.core.game.data.Material;
import io.github.koufu193.core.game.data.inventory.PlayerInventory;
import io.github.koufu193.core.game.data.item.ItemMeta;
import io.github.koufu193.core.game.data.item.ItemStack;
import io.github.koufu193.core.game.entities.Player;
import io.github.koufu193.core.game.entities.interfaces.IPlayer;
import io.github.koufu193.core.game.gamerule.GameRules;
import io.github.koufu193.core.game.manager.EntityIdManager;
import io.github.koufu193.core.game.world.World;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import org.jglrxavpok.hephaistos.nbt.NBTCompound;

import java.io.File;
import java.io.IOException;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.*;

public class MinecraftServer {
    private final GameRules gameRules = new GameRules();
    private final UserCaches userCache;
    private final ServerProperties serverProperties;
    private final PlayerDataReader playerDataReader;

    private final Map<Identifier, World> worlds = new HashMap<>();
    private final Map<UUID,World> worldsByUuids=new HashMap<>();
    private final Set<IPlayer> players = new HashSet<>();
    private final EntityIdManager entityIdManager = new EntityIdManager();
    private final String worldPrefix;

    public MinecraftServer(File folder) {
        folder.mkdirs();
        this.userCache = UserCaches.fromFolder(folder);
        this.serverProperties = ServerProperties.fromFolder(folder);
        this.playerDataReader = PlayerDataReader.fromWorldFolder(this, serverProperties.levelFolder());
        this.worldPrefix = this.serverProperties.levelName()+"_";
        for (File worldFolder : folder.listFiles(File::isDirectory)) {
            System.out.println("try loading "+worldFolder.getName());
            try {
                World world = new World(this, worldFolder);
                addWorld(world);
                System.out.println("loaded world:"+world.nameId());
            } catch (IllegalArgumentException | IOException ignored) {
                ignored.printStackTrace();
            }
        }
    }

    public Set<IPlayer> onlinePlayers() {
        return Collections.unmodifiableSet(this.players);
    }

    public GameRules gameRules() {
        return this.gameRules;
    }

    public Player loadPlayer(@NotNull GameProfile profile, @NotNull AsynchronousSocketChannel channel) {
        NBTCompound nbt = this.playerDataReader.get(profile.id());
        Player result = new Player(this, channel, entityIdManager.next(), nbt.toMutableCompound(), profile);
        result.inventory().set(PlayerInventory.PlayerArmor.HEAD, new ItemStack(Material.ACACIA_BOAT, 1, ItemMeta.defaultItemMeta(Material.ACACIA_BOAT)));
        this.playerDataReader.write(result.uniqueId(), result.createMutableNBT().toCompound());
        return result;
    }

    public Player join(@NotNull GameProfile profile, @NotNull AsynchronousSocketChannel channel) {
        Player player = loadPlayer(profile, channel);
        this.players.add(player);
        return player;
    }

    public void quit(@NotNull IPlayer player) {
        this.players.remove(player);
    }
    private void addWorld(@NotNull World world){
        this.worlds.put(new Identifier(world.nameId().toString().replaceFirst("^"+worldPrefix,"")), world);
        this.worldsByUuids.put(world.uid().uid(),world);
    }
    public UserCaches userCache() {
        return userCache;
    }

    public ServerProperties serverProperties() {
        return this.serverProperties;
    }
    public Difficulty difficulty(){
        return this.serverProperties.difficulty();
    }



    public World world(@NotNull Identifier dimension){
        return worlds.get(dimension);
    }
    public World world(@NotNull String name) {
        return this.world(new Identifier(name));
    }
    public Collection<World> worlds() {
        return this.worlds.values();
    }

    public World worldFromDimension(String dimension) {
        if ("minecraft:overworld".equals(dimension)) return this.world("world");
        return this.world(dimension);
    }
    public World world(@NotNull UUID worldUUID){
        return this.worldsByUuids.get(worldUUID);
    }

    public World defaultSpawnWorld() {
        return this.world(this.serverProperties.levelFolder().getName());
    }
}
