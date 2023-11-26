package io.github.koufu193.core.files;

import io.github.koufu193.core.game.data.Difficulty;
import io.github.koufu193.core.game.entities.Player;
import io.github.koufu193.util.FileUtils;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Properties;

/**
 * server.properties
 */
public class ServerProperties {
    private final boolean debug;
    private final boolean hardcore;
    private final int networkCompressionThreshold;
    private final Player.GameMode defaultGameMode;
    private final int maxPlayers;
    private final Path levelFolder;
    private final String levelName;
    private final Difficulty difficulty;
    private final int viewDistance;

    public boolean debug() {
        return debug;
    }

    public boolean hardcore() {
        return hardcore;
    }

    public int networkCompressionThreshold() {
        return networkCompressionThreshold;
    }

    @NotNull

    public Player.GameMode defaultGameMode() {
        return defaultGameMode;
    }

    public int maxPlayers() {
        return maxPlayers;
    }

    @NotNull

    public Path levelFolder() {
        return levelFolder;
    }

    @NotNull

    public String levelName() {
        return levelName;
    }

    @NotNull

    public Difficulty difficulty() {
        return difficulty;
    }

    public int viewDistance() {
        return viewDistance;
    }

    public ServerProperties(@NotNull Path file) {
        writeDefaultProperties(file);
        FileUtils.throwIfNotAFile(file);
        try (BufferedInputStream input = new BufferedInputStream(Files.newInputStream(file))) {
            Properties loadedProperties = new Properties();
            loadedProperties.load(input);
            //apply settings
            this.debug = Boolean.parseBoolean((String) loadedProperties.getOrDefault("debug", "false"));
            this.hardcore = Boolean.parseBoolean((String) loadedProperties.getOrDefault("hardcore", "false"));
            this.networkCompressionThreshold = Integer.parseInt((String) loadedProperties.getOrDefault("network-compression-threshold", "256"));
            this.defaultGameMode = Player.GameMode.fromString((String) loadedProperties.getOrDefault("gamemode", "survival"));
            this.maxPlayers = Integer.parseInt((String) loadedProperties.getOrDefault("max-players", "20"));
            this.levelFolder = file.getParent().resolve("world" + loadedProperties.getOrDefault("level-name", ""));
            this.levelName = (String) loadedProperties.getOrDefault("level-name", "");
            this.difficulty = Difficulty.valueOfWithIgnoringCase(((String) loadedProperties.getOrDefault("difficulty", "normal")));
            this.viewDistance = Integer.parseInt((String) loadedProperties.getOrDefault("view-distance", "10"));
            if (this.maxPlayers < 0) throw new RuntimeException("max-players must be 0 at least");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeDefaultProperties(@NotNull Path file) {
        if (Files.exists(file)) return;
        try (BufferedInputStream defaultServerProperties = new BufferedInputStream(
                Objects.requireNonNull(
                        Thread.currentThread().getContextClassLoader().getResourceAsStream("server.properties"),
                        "default server.properties not found"
                )
        )) {
            FileUtils.write(file, defaultServerProperties.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
