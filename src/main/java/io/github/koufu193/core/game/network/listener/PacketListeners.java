package io.github.koufu193.core.game.network.listener;

import io.github.koufu193.core.game.entities.Player;
import io.github.koufu193.network.packets.AbstractPacket;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class PacketListeners {
    private final List<PacketListener> listeners = new ArrayList<>();
    private final Player player;

    public PacketListeners(@NotNull Player player) {
        this(player, new PacketListener[0]);
    }

    public PacketListeners(@NotNull Player player, @NotNull PacketListener... listeners) {
        this.player = player;
        addAll(listeners);
    }

    public void onReceive(@NotNull AbstractPacket packet) {
        this.listeners.forEach(listener -> listener.onReceive(player,packet));
    }

    public void onSend(@NotNull AbstractPacket packet) {
        this.listeners.forEach(listener -> listener.afterSend(player,packet));
    }

    public void clearListeners() {
        this.listeners.clear();
    }

    public void add(@NotNull PacketListener listener) {
        this.listeners.add(listener);
    }

    public void addAll(@NotNull PacketListener... listeners) {
        for (PacketListener listener : listeners) {
            if (listener == null) throw new IllegalArgumentException("lister must not be null");
        }
        this.listeners.addAll(Arrays.asList(listeners));
    }
}
