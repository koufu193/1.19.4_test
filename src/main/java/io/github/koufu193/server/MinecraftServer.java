package io.github.koufu193.server;

import io.github.koufu193.core.files.PlayerDataReader;
import io.github.koufu193.core.files.ServerProperties;
import io.github.koufu193.core.game.data.Difficulty;
import io.github.koufu193.core.game.data.GameProfile;
import io.github.koufu193.core.game.data.Identifier;
import io.github.koufu193.core.game.entities.Player;
import io.github.koufu193.core.game.entities.interfaces.IPlayer;
import io.github.koufu193.core.game.gamerule.GameRules;
import io.github.koufu193.core.game.manager.EntityIdManager;
import io.github.koufu193.core.game.world.World;
import io.github.koufu193.util.FileUtils;
import org.jetbrains.annotations.NotNull;
import org.jglrxavpok.hephaistos.nbt.NBTCompound;
import oshi.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class MinecraftServer {
    private final GameRules gameRules = new GameRules();
    private final ServerProperties serverProperties;
    private final PlayerDataReader playerDataReader;

    private final Map<Identifier, World> worlds = new HashMap<>();
    private final Map<UUID, World> worldsByUuid = new HashMap<>();
    private final Set<IPlayer> players = new HashSet<>();
    private final EntityIdManager entityIdManager = new EntityIdManager();
    private final String worldPrefix;

    public MinecraftServer(@NotNull Path serverFolder) {
        FileUtils.createDirectories(serverFolder);
        this.serverProperties = new ServerProperties(serverFolder.resolve("server.properties"));
        this.playerDataReader = new PlayerDataReader(this, serverProperties.levelFolder().resolve("playerdata"));
        this.worldPrefix = this.serverProperties.levelName() + "_";
        FileUtils.childDirectories(serverFolder).forEach(worldFolder->{
            System.out.println("try loading " + worldFolder.getFileName());
            try {
                World world = new World(this, worldFolder);
                addWorld(world);
                System.out.println("loaded world:" + world.name());
            } catch (IllegalArgumentException | IOException ignored) {
                ignored.printStackTrace();
            }
        });
    }

    public Set<IPlayer> onlinePlayers() {
        return Collections.unmodifiableSet(this.players);
    }

    public GameRules gameRules() {
        return this.gameRules;
    }

    protected Player loadPlayer(@NotNull GameProfile profile, @NotNull AsynchronousSocketChannel channel) {
        NBTCompound playerNBT = this.playerDataReader.getPlayerNBT(profile.id());
        Player result = new Player(this, channel, entityIdManager.next(), playerNBT.toMutableCompound(), profile);
        this.playerDataReader.writePlayerNBT(result.uniqueId(), result.toCompound());
        return result;
    }

    public Player join(@NotNull GameProfile profile, @NotNull AsynchronousSocketChannel channel) {
        Player player = loadPlayer(profile, channel);
        this.players.add(player);
        return player;
    }

    public void onQuit(@NotNull IPlayer player) {
        this.players.remove(player);
    }

    private void addWorld(@NotNull World world) {
        Identifier name = world.name();
        this.worlds.put(new Identifier(name.namespace(), name.value().replaceFirst("^" + worldPrefix, "")), world);
        this.worldsByUuid.put(world.uid(), world);
    }

    public ServerProperties serverProperties() {
        return this.serverProperties;
    }

    public Difficulty difficulty() {
        return this.serverProperties.difficulty();
    }


    public World world(@NotNull Identifier dimension) {
        return worlds.get(dimension);
    }

    public World world(@NotNull String name) {
        return this.world(new Identifier(name));
    }

    public Collection<World> worlds() {
        return this.worlds.values();
    }

    public World world(@NotNull UUID worldUUID) {
        return this.worldsByUuid.get(worldUUID);
    }

    public World defaultSpawnWorld() {
        return this.world(this.serverProperties.levelFolder().getFileName().toString());
    }
}
