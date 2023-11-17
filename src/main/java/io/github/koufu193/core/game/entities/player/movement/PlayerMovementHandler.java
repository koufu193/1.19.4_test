package io.github.koufu193.core.game.entities.player.movement;

import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.core.game.entities.Player;
import org.jetbrains.annotations.NotNull;

public class PlayerMovementHandler {
    private final Player player;
    public PlayerMovementHandler(@NotNull Player player){
        this.player=player;
    }

    public Player player() {
        return player;
    }
    public boolean canMoveTo(@NotNull Location location){
        if(player.world()!=location.world()||location.world()==null) return false;
        return true;
    }
}
