package io.github.koufu193.core.game.data;

import io.github.koufu193.core.properties.Properties;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public record GameProfile(@NotNull UUID id, @NotNull String name, Properties properties){
}
