package io.github.koufu193.server;

import io.github.koufu193.core.game.data.GameProfile;
import io.github.koufu193.core.game.entities.Player;
import io.github.koufu193.core.game.gamerule.GameRules;
import io.github.koufu193.core.game.world.World;
import io.github.koufu193.core.loaders.LevelDat;
import io.github.koufu193.core.loaders.PlayerDataLoader;
import io.github.koufu193.core.loaders.ServerProperties;
import io.github.koufu193.core.loaders.UserCaches;
import org.apache.commons.io.filefilter.FileFileFilter;
import org.jglrxavpok.hephaistos.nbt.NBT;
import org.jglrxavpok.hephaistos.nbt.NBTCompound;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

public class MinecraftServer {
    private final GameRules gameRules=new GameRules();
    private final UserCaches userCache;
    private final ServerProperties serverProperties;
    private final PlayerDataLoader playerDataLoader;
    private final LevelDat levelDat;

    private final HashMap<String, World> worlds=new HashMap<>();
    public MinecraftServer(File folder){
        folder.mkdirs();
        this.userCache= UserCaches.fromFolder(folder);
        this.serverProperties=ServerProperties.fromFolder(folder);
        this.playerDataLoader=PlayerDataLoader.fromWorldFolder(serverProperties.levelFolder());
        this.levelDat = LevelDat.fromFolder(serverProperties.levelFolder());
        for(File worldFolder:folder.listFiles(File::isDirectory)){
            try {
                World world = new World(worldFolder);
                worlds.put(world.name(),world);
            }catch (IllegalArgumentException ignored){}
        }
    }
    public GameRules gameRules(){return this.gameRules;}
    public Player loadPlayer(GameProfile profile, AsynchronousSocketChannel channel){
        NBTCompound nbt=this.playerDataLoader.get(profile.id());
        if(nbt==null) nbt=NBT.Compound(a->{});
        return new Player(this,channel,0,nbt.toMutableCompound(),profile);
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
        if("overworld".equals(dimension)) return this.world("world");
        return this.world(dimension);
    }
}
