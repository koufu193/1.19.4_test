package io.github.koufu193.core.game.entities.interfaces;

import io.github.koufu193.core.game.commands.CommandExecutor;
import io.github.koufu193.core.game.data.GameProfile;
import io.github.koufu193.core.game.entities.Player;
import io.github.koufu193.core.game.entities.handlers.PlayerPacketHandler;
import io.github.koufu193.core.properties.Properties;

public interface IPlayer extends CommandExecutor,IEntity {
    Player.GameMode gameMode();
    Player.GameMode previousGameMode();
    Player.MainHand mainHand();
    String locale();
    int viewDistance();
    Player.ChatMode chatMode();
    void gameMode(Player.GameMode gameMode);
    boolean mayFly();
    float walkSpeed();
    float flySpeed();
    float expProgress();
    int expLevel();
    int totalExpPoints();
    void expProgress(float progress);
    void expLevel(int level);
    void totalExpPoints(int points);
    PlayerPacketHandler handler();
    String name();
    GameProfile profile();
}
