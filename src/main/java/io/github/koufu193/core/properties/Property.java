package io.github.koufu193.core.properties;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public record Property(@NotNull String name,@NotNull String value,@Nullable String signature) {
    public Property(@NotNull String name,@NotNull String value){
        this(name,value,null);
    }
    public boolean isSigned() {
        return this.signature != null;
    }
}
