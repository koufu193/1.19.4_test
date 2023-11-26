package io.github.koufu193.core.game.entities;

import io.github.koufu193.core.game.commands.Command;
import io.github.koufu193.core.game.commands.nodes.RootCommandNode;
import io.github.koufu193.core.game.data.GameProfile;
import io.github.koufu193.core.game.data.Identifier;
import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.core.game.data.Material;
import io.github.koufu193.core.game.data.component.TextComponent;
import io.github.koufu193.core.game.data.inventory.InventoryView;
import io.github.koufu193.core.game.data.inventory.PlayerInventory;
import io.github.koufu193.core.game.data.item.ItemMeta;
import io.github.koufu193.core.game.data.item.ItemStack;
import io.github.koufu193.core.game.entities.player.ChunkSender;
import io.github.koufu193.core.game.entities.player.CommandManager;
import io.github.koufu193.core.game.entities.player.InventoryHandler;
import io.github.koufu193.core.game.entities.player.PlayerPacketHandler;
import io.github.koufu193.core.game.entities.player.movement.PlayerMovementHandler;
import io.github.koufu193.core.game.entities.player.v1194.V1194PlayerPacketHandler;
import io.github.koufu193.core.game.network.listener.PacketListeners;
import io.github.koufu193.core.game.world.World;
import io.github.koufu193.exceptions.CommandException;
import io.github.koufu193.network.PacketDecoder;
import io.github.koufu193.network.PacketEncoder;
import io.github.koufu193.server.MinecraftServer;
import io.github.koufu193.util.ConvertibleToNBTCompound;
import io.github.koufu193.util.NBTUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jglrxavpok.hephaistos.nbt.*;
import org.jglrxavpok.hephaistos.nbt.mutable.MutableNBTCompound;

import java.io.IOException;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.*;

public class Player extends Entity implements io.github.koufu193.core.game.entities.interfaces.IPlayer {
    private final AsynchronousSocketChannel channel;
    protected GameMode gameMode;
    protected GameMode previousGameMode;
    protected Property property;
    protected PlayerAbilities abilities;
    protected int totalExpPoints;
    protected int expLevel;
    protected float expProgress;
    private GameProfile profile;
    private InventoryView openingView;
    private Location lastDeathLocation;
    private final PlayerPacketHandler packetHandler;
    private final PlayerMovementHandler movementHandler = new PlayerMovementHandler(this);
    private final PlayerInventory inventory;
    private final InventoryHandler inventoryHandler;
    private final CommandManager commandManager = new CommandManager(this);
    private final ChunkSender chunkSender = new ChunkSender(this);

    public Player(MinecraftServer server, AsynchronousSocketChannel channel, int entityId, MutableNBTCompound nbt, GameProfile profile) {
        super(server, entityId, nbt, server.world(readWorldUUID(nbt)));
        this.channel = channel;
        this.gameMode = GameMode.fromId(
                Objects.requireNonNullElseGet(nbt.getInt("playerGameType"), () -> server.serverProperties().defaultGameMode().id())
        );
        this.previousGameMode = GameMode.fromId(
                Objects.requireNonNullElseGet(nbt.getInt("previousPlayerGameType"), GameMode.Undefined::id)
        );
        this.abilities = Optional.ofNullable(nbt.getCompound("abilities"))
                .map(PlayerAbilities::new)
                .orElseGet(PlayerAbilities::new);
        this.totalExpPoints = Objects.requireNonNullElse(nbt.getInt("XpTotal"), 0);
        this.expLevel = Objects.requireNonNullElse(nbt.getInt("XpLevel"), 0);
        this.expProgress = Objects.requireNonNullElse(nbt.getFloat("XpP"), 0.0f);
        this.profile = profile;
        this.inventory = loadInventory(nbt);
        this.lastDeathLocation = Optional.ofNullable(nbt.getCompound("lastDeathLocation"))
                .map(value->
                        NBTUtils.convertIntPositionToLocation(value.getList("pos"))
                                .world(server.world(new Identifier(value.getString("dimension")))))
                .orElse(null);
        this.inventoryHandler = new InventoryHandler(this);
        this.packetHandler = new V1194PlayerPacketHandler(this, new PacketListeners(this), this.channel, new PacketEncoder(), new PacketDecoder());
    }

    @Override
    public NBTCompound toCompound() {
        return NBT.Compound(root -> {
            root.setAll(super.toCompound());
            root.setInt("playerGameType", this.gameMode.id());
            if (this.previousGameMode != null){
                root.setInt("previousPlayerGameType", this.previousGameMode.id());
            }else root.remove("previousPlayerGameType");
            root.set("abilities", this.abilities.toCompound());
            root.setInt("XpTotal", this.totalExpPoints);
            root.setInt("XpLevel", this.expLevel);
            root.setFloat("XpP", this.expProgress);
            if(this.lastDeathLocation!=null){
                root.set("lastDeathLocation",NBT.Compound(lastDeathLocation->{
                    lastDeathLocation.setIntArray("pos",new int[]{(int) this.lastDeathLocation.x(), (int) this.lastDeathLocation.y(), (int) this.lastDeathLocation.z()});
                    lastDeathLocation.setString("dimension",this.lastDeathLocation.world().name().toString());
                }));
            }else root.remove("lastDeathLocation");
        });
    }

    public ChunkSender chunkSender() {
        return chunkSender;
    }


    private static UUID readWorldUUID(@NotNull NBTCompoundLike nbt) {
        return new UUID(nbt.getLong("WorldUUIDMost"), nbt.getLong("WorldUUIDLeast"));
    }

    private static PlayerInventory loadInventory(NBTCompoundLike nbt) {
        int selectedSlot = Objects.requireNonNullElse(nbt.getInt("SelectedItemSlot"), 0);
        PlayerInventory playerInventory = new PlayerInventory();
        List<? extends NBT> inventoryNBT= Objects.requireNonNullElseGet(nbt.getList("Inventory"), ()->new NBTList<>(NBTType.TAG_Compound)).asListView();
        inventoryNBT.stream()
                .map(itemNBT->(NBTCompoundLike)itemNBT)
                .forEach(itemNBT -> playerInventory.set(itemNBT.getByte("Slot"), makeItemStack(itemNBT)));
        playerInventory.selectedSlot(selectedSlot);
        return playerInventory;
    }

    private static ItemStack makeItemStack(NBTCompoundLike nbt) {
        NBTCompound compound = Objects.requireNonNullElse(nbt.getCompound("tag"), NBTCompound.EMPTY);
        Material material = Material.fromId(nbt.getString("id"));
        byte amount = nbt.getByte("Count");
        return new ItemStack(material, amount, ItemMeta.defaultItemMeta(material, compound));
    }

    @NotNull
    @Override
    public GameMode gameMode() {
        return this.gameMode;
    }

    @NotNull
    @Override
    public GameMode previousGameMode() {
        return this.previousGameMode;
    }

    @Override
    public void gameMode(@NotNull GameMode gameMode) {
        if (this.gameMode == gameMode) return;
        if (gameMode == GameMode.Undefined) throw new IllegalArgumentException("gameMode must not be Undefined");
        this.previousGameMode = this.gameMode;
        this.gameMode = gameMode;
    }

    @Override
    public boolean mayFly() {
        return this.abilities.mayFly();
    }

    @Override
    public float walkSpeed() {
        return this.abilities.walkSpeed();
    }

    @Override
    public float flySpeed() {
        return this.abilities.flySpeed();
    }

    @Override
    public float expProgress() {
        return this.expProgress;
    }

    @Override
    public int expLevel() {
        return this.expLevel;
    }

    @Override
    public int totalExpPoints() {
        return this.totalExpPoints;
    }

    @Override
    public void expProgress(float progress) {
        if (progress < 0 || 1 < progress)
            throw new IllegalArgumentException("Experience progress must be between 0 and 1");
        this.expProgress = progress;
        this.packetHandler.sendExpData(this.totalExpPoints, this.expLevel, this.expProgress);
    }

    @Override
    public void expLevel(int level) {
        if (level < 0) throw new IllegalArgumentException("Experience level must not be negative");
        this.expLevel = level;
        this.packetHandler.sendExpData(this.totalExpPoints, this.expLevel, this.expProgress);
    }

    @Override
    public void totalExpPoints(int points) {
        if (points < 0) throw new IllegalArgumentException("Total experience points must not be negative");
        this.totalExpPoints = points;
        this.packetHandler.sendExpData(this.totalExpPoints, this.expLevel, this.expProgress);
    }

    @Override
    public MainHand mainHand() {
        return this.property.mainHand();
    }

    @Override
    public String locale() {
        return this.property.lang();
    }

    @Override
    public int viewDistance() {
        return this.property != null ? this.property.viewDistance() : server.serverProperties().viewDistance();
    }

    @Override
    public ChatMode chatMode() {
        return this.property.chatMode();
    }

    public Property property() {
        return property;
    }

    public PlayerAbilities abilities() {
        return this.abilities;
    }

    public void property(Property property) {
        this.property = property;
    }

    @Override
    public void teleport(@NotNull Location location) {
        this.packetHandler.teleport(location);
        this.location = location;
    }

    public PlayerPacketHandler packetHandler() {
        return packetHandler;
    }

    @Override
    public String name() {
        return this.profile.name();
    }

    @Override
    public GameProfile profile() {
        return this.profile;
    }

    protected void name(@NotNull String name) {
        this.profile = new GameProfile(profile.id(), name, profile.properties());
    }

    public void location(@NotNull Location location) {
        this.location = location;
    }

    public AsynchronousSocketChannel channel() {
        return channel;
    }

    @Override
    public World world() {
        return this.location != null ? location.world() : null;
    }

    @Override
    public PlayerMovementHandler movementHandler() {
        return this.movementHandler;
    }

    @Override
    public PlayerInventory inventory() {
        return this.inventory;
    }

    public void setCommands(@NotNull RootCommandNode root) {
        this.commandManager.root(root);
        this.packetHandler.sendCommandNode(root);
    }

    @Override
    public void kick(@NotNull TextComponent reason) {
        this.packetHandler.kick(reason);
        try {
            channel.close();
        } catch (IOException ignored) {
        }
    }

    @Override
    public boolean isOnline() {
        return channel.isOpen();
    }

    @Override
    public void openInventory(@NotNull InventoryView view) {
        if (this.openingView != null) closeInventory();
        this.inventoryHandler.open(view);
        this.openingView = view;
    }

    public void onClose() {
        if (this.openingView == null) return;
        this.inventoryHandler.onClose(this.openingView);
        this.openingView = null;
    }

    @Override
    public void closeInventory() {
        if (this.openingView == null) return;
        this.inventoryHandler.close(this.openingView);
        this.openingView = null;
    }

    @Override
    public void dispatchCommand(@NotNull String command) {
        try {
            Command c = Command.parse(command, this.commandManager.root());
            c.execute(this);
        } catch (CommandException e) {
            this.packetHandler.sendSystemMessage(e.messageComponent());
        }
    }

    @Nullable
    @Override
    public Location lastDeathLocation() {
        return this.lastDeathLocation;
    }

    public enum GameMode {
        Survival(0), Creative(1), Adventure(2), Spectator(3), Undefined(-1);
        private final int id;

        GameMode(int id) {
            this.id = id;
        }

        public int id() {
            return id;
        }

        public static GameMode fromString(String mode) {
            return switch (mode) {
                case "survival" -> Survival;
                case "creative" -> Creative;
                case "adventure" -> Adventure;
                case "spectator" -> Spectator;
                default -> throw new IllegalArgumentException("Invalid String " + mode);
            };
        }

        public static GameMode fromId(int id) {
            for(GameMode mode:values()){
                if(mode.id==id) return mode;
            }
            return null;
        }
    }

    public static class PlayerAbilities implements ConvertibleToNBTCompound {
        public PlayerAbilities(@NotNull NBTCompound nbt) {
            this.mayFly = Objects.requireNonNullElse(nbt.getBoolean("mayfly"), false);
            this.mayBuild = Objects.requireNonNullElse(nbt.getBoolean("mayBuild"), true);
            this.flying = Objects.requireNonNullElse(nbt.getBoolean("flying"), false);
            this.invulnerable = Objects.requireNonNullElse(nbt.getBoolean("invulnerable"), false);
            this.instabuild = Objects.requireNonNullElse(nbt.getBoolean("instabuild"), false);
            this.flySpeed = Objects.requireNonNullElse(nbt.getFloat("flySpeed"), flySpeed);
            this.walkSpeed = Objects.requireNonNullElse(nbt.getFloat("walkSpeed"), walkSpeed);
        }

        @Override
        public NBTCompound toCompound() {
            return NBT.Compound(root -> {
                root.set("mayfly", NBT.Boolean(this.mayFly));
                root.set("mayBuild", NBT.Boolean(this.mayBuild));
                root.set("flying", NBT.Boolean(this.flying));
                root.set("invulnerable", NBT.Boolean(this.invulnerable));
                root.set("instabuild", NBT.Boolean(this.instabuild));
                root.setFloat("flySpeed", this.flySpeed);
                root.setFloat("walkSpeed", this.walkSpeed);
            });
        }

        public PlayerAbilities() {
        }

        private float flySpeed = 0.05f;
        private float walkSpeed = 0.1f;
        private boolean flying = false;
        private boolean mayFly = false;
        private boolean mayBuild = true;
        private boolean instabuild = false;
        private boolean invulnerable = false;

        public float flySpeed() {
            return flySpeed;
        }

        public float walkSpeed() {
            return walkSpeed;
        }

        public boolean mayBuild() {
            return mayBuild;
        }

        public boolean instabuild() {
            return instabuild;
        }

        public boolean invulnerable() {
            return invulnerable;
        }

        public boolean mayFly() {
            return mayFly;
        }

        public boolean flying() {
            return flying;
        }

        public PlayerAbilities flying(boolean flying) {
            this.flying = flying;
            return this;
        }

        public PlayerAbilities flySpeed(float flySpeed) {
            this.flySpeed = flySpeed;
            return this;
        }

        public PlayerAbilities instabuild(boolean instabuild) {
            this.instabuild = instabuild;
            return this;
        }

        public PlayerAbilities invulnerable(boolean invulnerable) {
            this.invulnerable = invulnerable;
            return this;
        }

        public PlayerAbilities mayBuild(boolean mayBuild) {
            this.mayBuild = mayBuild;
            return this;
        }

        public PlayerAbilities mayFly(boolean mayFly) {
            this.mayFly = mayFly;
            return this;
        }

        public PlayerAbilities walkSpeed(float walkSpeed) {
            this.walkSpeed = walkSpeed;
            return this;
        }

    }

    public record Property(String lang, byte viewDistance, ChatMode chatMode, boolean chatColors, byte display,
                           MainHand mainHand, boolean filtering, boolean showList) {

    }
}
