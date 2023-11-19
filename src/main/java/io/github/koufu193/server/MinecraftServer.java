package io.github.koufu193.server;

import io.github.koufu193.core.game.data.GameProfile;
import io.github.koufu193.core.game.data.Material;
import io.github.koufu193.core.game.data.inventory.PlayerInventory;
import io.github.koufu193.core.game.data.item.ItemMeta;
import io.github.koufu193.core.game.data.item.ItemStack;
import io.github.koufu193.core.game.entities.Player;
import io.github.koufu193.core.game.entities.interfaces.IPlayer;
import io.github.koufu193.core.game.gamerule.GameRules;
import io.github.koufu193.core.game.manager.EntityIdManager;
import io.github.koufu193.core.game.world.World;
import io.github.koufu193.core.files.LevelDat;
import io.github.koufu193.core.files.PlayerDataReader;
import io.github.koufu193.core.files.ServerProperties;
import io.github.koufu193.core.files.UserCaches;
import org.jetbrains.annotations.NotNull;
import org.jglrxavpok.hephaistos.nbt.NBTCompound;

import java.io.File;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.*;

public class MinecraftServer {
    private final GameRules gameRules=new GameRules();
    private final UserCaches userCache;
    private final ServerProperties serverProperties;
    private final PlayerDataReader playerDataReader;
    private final LevelDat levelDat;

    private final HashMap<String, World> worlds=new HashMap<>();
    private final Set<IPlayer> players=new HashSet<>();
    private final EntityIdManager entityIdManager=new EntityIdManager();
    public MinecraftServer(File folder){
        folder.mkdirs();
        this.userCache= UserCaches.fromFolder(folder);
        this.serverProperties=ServerProperties.fromFolder(folder);
        this.playerDataReader = PlayerDataReader.fromWorldFolder(this,serverProperties.levelFolder());
        this.levelDat = LevelDat.fromFolder(serverProperties.levelFolder());
        for(File worldFolder:folder.listFiles(File::isDirectory)){
            try {
                World world = new World(worldFolder);
                worlds.put(world.name(),world);
            }catch (IllegalArgumentException ignored){}
        }
    }

    public Set<IPlayer> onlinePlayers() {
        return Collections.unmodifiableSet(this.players);
    }

    public GameRules gameRules(){
        return this.gameRules;
    }
    public Player loadPlayer(@NotNull GameProfile profile,@NotNull AsynchronousSocketChannel channel){
        NBTCompound nbt=this.playerDataReader.get(profile.id());
        Player result=new Player(this,channel,entityIdManager.next(),nbt.toMutableCompound(),profile);
        result.inventory().set(PlayerInventory.PlayerArmor.HEAD,new ItemStack(Material.ACACIA_BOAT,1, ItemMeta.defaultItemMeta(Material.ACACIA_BOAT)));
        this.playerDataReader.write(result.uniqueId(),result.createMutableNBT().toCompound());
        return result;
    }
    public Player join(@NotNull GameProfile profile,@NotNull AsynchronousSocketChannel channel){
        Player player=loadPlayer(profile,channel);
        this.players.add(player);
        return player;
    }
    public void quit(@NotNull IPlayer player){
        this.players.remove(player);
    }
    public UserCaches userCache() {
        return userCache;
    }

    public ServerProperties serverProperties() {
        return serverProperties;
    }

    public LevelDat levelDat() {
        return levelDat;
    }
    public World world(String name){
        return this.worlds.get(name);
    }
    public Collection<World> worlds(){
        return this.worlds.values();
    }
    public World worldFromDimension(String dimension){
        if("minecraft:overworld".equals(dimension)) return this.world("world");
        return this.world(dimension);
    }
    public World defaultSpawnWorld(){
        return this.world(this.serverProperties.levelFolder().getName());
    }
}
