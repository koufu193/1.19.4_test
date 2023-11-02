package io.github.koufu193.core.game.data;

import net.minecraft.network.protocol.game.ServerboundChatCommandPacket;
import org.jetbrains.annotations.NotNull;

public class Identifier{
    private static final String DEFAULT_NAMESPACE="minecraft";
    private final String namespace;
    private final String value;
    private final String identifier;
    public Identifier(@NotNull String namespace,@NotNull String value){
        checkNamespaceOrThrow(namespace);
        checkValueOrThrow(value);
        this.namespace=namespace;
        this.value=value;
        this.identifier=namespace+":"+value;
    }
    public Identifier(@NotNull String identifier){
        String[] splitIdentifier=identifier.split(":",2);
        String namespace;
        String value;
        if(splitIdentifier.length==1){
            namespace=DEFAULT_NAMESPACE;
            value=splitIdentifier[0];
        }else{
            namespace=splitIdentifier[0];
            value=splitIdentifier[1];
        }
        checkNamespaceOrThrow(namespace);
        checkValueOrThrow(value);
        this.namespace=namespace;
        this.value=value;
        this.identifier=namespace+":"+value;
    }

    @Override
    public String toString() {
        return this.identifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Identifier that)) return false;
        return this.identifier.equals(that.identifier);
    }

    @Override
    public int hashCode() {
        return this.identifier.hashCode();
    }

    public String value() {
        return this.value;
    }

    public String namespace() {
        return this.namespace;
    }
    private static void checkNamespaceOrThrow(@NotNull String namespace){
        if (!namespace.matches("^[a-z0-9.-_]+$")) throw new IllegalArgumentException("Illegal namespace " + namespace);
    }
    private static void checkValueOrThrow(@NotNull String value){
        if (!value.matches("^[a-z0-9.-_/]+$")) throw new IllegalArgumentException("Illegal value " + value);
    }
}
