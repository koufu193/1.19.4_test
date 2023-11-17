package io.github.koufu193.core.game.entities.interfaces;

import io.github.koufu193.core.game.commands.CommandExecutor;
import io.github.koufu193.core.game.data.GameProfile;
import io.github.koufu193.core.game.data.component.TextComponent;
import io.github.koufu193.core.game.data.inventory.InventoryView;
import io.github.koufu193.core.game.data.inventory.PlayerInventory;
import io.github.koufu193.core.game.entities.Player;
import io.github.koufu193.core.game.entities.player.PlayerPacketHandler;
import io.github.koufu193.core.game.entities.player.movement.PlayerMovementHandler;
import io.github.koufu193.core.game.world.World;
import org.jetbrains.annotations.NotNull;

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
    PlayerPacketHandler packetHandler();
    String name();
    GameProfile profile();
    World world();
    PlayerMovementHandler movementHandler();
    PlayerInventory inventory();
    void kick(@NotNull TextComponent reason);
    boolean isOnline();
    void openInventory(@NotNull InventoryView view);
    void closeInventory();
    void dispatchCommand(@NotNull String command);
    enum MainHand{
        Left,Right
    }
    enum ChatMode{
        Enabled,OnlyCommands,Hidden
    }

}
