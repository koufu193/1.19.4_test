package io.github.koufu193.core.game.network.listener;

import io.github.koufu193.core.game.entities.Player;
import io.github.koufu193.network.packets.AbstractPacket;
import io.github.koufu193.network.packets.play.ClientboundKeepAlivePacket;
import io.github.koufu193.network.packets.play.ServerboundKeepAlivePacket;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class KeepAlivePacketListener implements PacketListener{
    private final HashMap<UUID,List<Long>> ids=new HashMap<>();
    @Override
    public void onReceive(@NotNull Player player,@NotNull AbstractPacket packet) {
        if(!(packet instanceof ServerboundKeepAlivePacket packet1)) return;
        findOrCreateIds(player.uniqueId()).removeIf(id->id==packet1.id());
    }
    public void afterSend(@NotNull Player player,@NotNull AbstractPacket packet){
        if(!(packet instanceof ClientboundKeepAlivePacket packet1)) return;
        findOrCreateIds(player.uniqueId()).add(packet1.id());
    }
    public List<Long> findOrCreateIds(@NotNull UUID uuid){
        return ids.computeIfAbsent(uuid,a->new ArrayList<>());
    }
}
