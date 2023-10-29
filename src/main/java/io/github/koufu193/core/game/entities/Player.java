package io.github.koufu193.core.game.entities;

import io.github.koufu193.core.game.data.GameProfile;
import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.core.game.entities.handlers.PlayerPacketHandler;
import io.github.koufu193.core.game.entities.handlers.V1194PlayerPacketHandler;
import io.github.koufu193.core.game.entities.handlers.movement.PlayerMovementHandler;
import io.github.koufu193.core.game.entities.interfaces.IPlayer;

import io.github.koufu193.core.game.world.World;
import io.github.koufu193.server.MinecraftServer;
import org.jetbrains.annotations.NotNull;
import org.jglrxavpok.hephaistos.nbt.NBT;
import org.jglrxavpok.hephaistos.nbt.NBTCompound;
import org.jglrxavpok.hephaistos.nbt.mutable.MutableNBTCompound;

import java.nio.channels.AsynchronousSocketChannel;
import java.util.Objects;

public class Player extends Entity implements IPlayer{
    private final AsynchronousSocketChannel channel;
    protected GameMode gameMode;
    protected GameMode previousGameMode;
    protected Property property;
    protected PlayerAbilities abilities;
    protected int totalExpPoints;
    protected int expLevel;
    protected float expProgress;
    private GameProfile profile;
    private final PlayerPacketHandler packetHandler;
    private final PlayerMovementHandler movementHandler=new PlayerMovementHandler(this);
    public Player(MinecraftServer server,AsynchronousSocketChannel channel, int entityId, MutableNBTCompound nbt,GameProfile profile) {
        super(server,entityId,nbt,server.worldFromDimension(Objects.requireNonNull(nbt.getString("Dimension"))));
        this.channel=channel;
        this.gameMode=GameMode.fromId((int)nbt.getOrPut("playerGameType",()->NBT.Int(server.serverProperties().defaultGameMode().id())).getValue());
        Integer preGameMode=nbt.getInt("previousPlayerGameType");
        this.previousGameMode=preGameMode!=null?GameMode.fromId(preGameMode):null;
        if(nbt.getCompound("abilities")==null) nbt.put("abilities",NBT.Compound(a->{}));
        this.abilities=new PlayerAbilities(nbt.getCompound("abilities"));
        nbt.put("abilities",this.abilities.nbt());
        this.totalExpPoints=(int)nbt.getOrPut("XpTotal",()->NBT.Int(0)).getValue();
        this.expLevel=(int)nbt.getOrPut("XpLevel",()->NBT.Int(0)).getValue();
        this.expProgress=(float)nbt.getOrPut("XpP",()->NBT.Float(0)).getValue();
        this.packetHandler =new V1194PlayerPacketHandler(this.channel);
        this.profile=profile;
    }

    @Override
    public GameMode gameMode() {
        return this.gameMode;
    }

    @Override
    public GameMode previousGameMode() {
        return this.previousGameMode;
    }

    @Override
    public void gameMode(@NotNull GameMode gameMode) {
        if(this.gameMode==gameMode) return;
        this.previousGameMode=this.gameMode;
        this.gameMode=gameMode;
        this.nbt.setInt("playerGameType",this.gameMode.id());
        this.nbt.setInt("previousPlayerGameType",this.previousGameMode.id());
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
        if(progress<0||1<progress) throw new IllegalArgumentException("Experience progress must be between 0 and 1");
        this.expProgress=progress;
        this.nbt.setFloat("XpP",progress);
        this.packetHandler.sendExpData(this.totalExpPoints,this.expLevel,this.expProgress);
    }

    @Override
    public void expLevel(int level) {
        if(level<0) throw new IllegalArgumentException("Experience level must not be negative");
        this.expLevel=level;
        this.nbt.setInt("XpLevel",level);
        this.packetHandler.sendExpData(this.totalExpPoints,this.expLevel,this.expProgress);
    }

    @Override
    public void totalExpPoints(int points) {
        if(points<0) throw new IllegalArgumentException("Total experience points must not be negative");
        this.totalExpPoints=points;
        this.nbt.setInt("XpTotal",points);
        this.packetHandler.sendExpData(this.totalExpPoints,this.expLevel,this.expProgress);
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
        return this.property.viewDistance();
    }

    @Override
    public ChatMode chatMode() {
        return this.property.chatMode();
    }

    public Property property() {
        return property;
    }
    public PlayerAbilities abilities(){
        return this.abilities;
    }

    protected void property(Property property) {
        this.property = property;
    }

    @Override
    public void teleport(@NotNull Location location) {
        this.packetHandler.teleport(location);
        this.location=location;
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

    protected void name(@NotNull String name){
        this.profile=new GameProfile(profile.id(),name,profile.properties());
    }
    public void location(@NotNull Location location){
        this.location=location;
    }

    public AsynchronousSocketChannel channel() {
        return channel;
    }

    @Override
    public World world() {
        return this.location!=null?location.world():null;
    }

    @Override
    public PlayerMovementHandler movementHandler() {
        return this.movementHandler;
    }

    public enum GameMode{
        Survival(0),Creative(1),Adventure(2),Spectator(3);
        private final int id;
        GameMode(int id){
            this.id=id;
        }

        public int id() {
            return id;
        }
        public static GameMode fromString(String mode){
            return switch (mode) {
                case "survival" -> Survival;
                case "creative" -> Creative;
                case "adventure" -> Adventure;
                case "spectator" -> Spectator;
                default -> throw new IllegalArgumentException("Invalid String " + mode);
            };
        }
        public static GameMode fromId(int id){
            if(id<0||3<id) throw new IllegalArgumentException("invalid id "+id);
            return GameMode.values()[id];
        }
    }
    public static class PlayerAbilities{
        public PlayerAbilities(NBTCompound nbt){
            if(nbt==null) nbt=NBT.Compound(root->{});
            this.nbt=nbt.toMutableCompound();
            this.flying= Boolean.TRUE.equals(this.nbt.getOrPut("flying", NBT::getFALSE).getValue());
            this.mayFly=Boolean.TRUE.equals(this.nbt.getOrPut("mayfly",NBT::getFALSE).getValue());
            this.invulnerable=Boolean.TRUE.equals(this.nbt.getOrPut("invulnerable",NBT::getFALSE).getValue());
            this.instabuild=Boolean.TRUE.equals(this.nbt.getOrPut("instabuild",NBT::getFALSE).getValue());
            this.flySpeed=(Float)this.nbt.getOrPut("flySpeed",()->NBT.Float(flySpeed)).getValue();
            this.walkSpeed=(Float)this.nbt.getOrPut("walkSpeed",()->NBT.Float(walkSpeed)).getValue();
            this.mayBuild=!Boolean.FALSE.equals(this.nbt.getOrPut("mayBuild",NBT::getTRUE).getValue());
        }
        public PlayerAbilities(){
            this.nbt=NBT.Compound(a->{}).toMutableCompound();
            this.walkSpeed(walkSpeed).flySpeed(flySpeed).flying(flying).instabuild(instabuild).invulnerable(invulnerable).mayFly(mayFly).mayBuild(mayBuild);
        }
        private final MutableNBTCompound nbt;
        private float flySpeed=0.05f;
        private float walkSpeed=0.1f;
        private boolean flying=false;
        private boolean mayFly=false;
        private boolean mayBuild=true;
        private boolean instabuild=false;
        private boolean invulnerable=false;

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

        NBTCompound nbt(){
            return nbt.toCompound();
        }

        public PlayerAbilities flying(boolean flying) {
            this.flying = flying;
            this.nbt.set("flying", NBT.Boolean(flying));
            return this;
        }

        public PlayerAbilities flySpeed(float flySpeed) {
            this.flySpeed = flySpeed;
            this.nbt.setFloat("flySpeed",flySpeed);
            return this;
        }

        public PlayerAbilities instabuild(boolean instabuild) {
            this.instabuild = instabuild;
            this.nbt.set("instabuild", NBT.Boolean(instabuild));
            return this;
        }

        public PlayerAbilities invulnerable(boolean invulnerable) {
            this.invulnerable = invulnerable;
            this.nbt.set("invulnerable", NBT.Boolean(invulnerable));
            return this;
        }

        public PlayerAbilities mayBuild(boolean mayBuild) {
            this.mayBuild = mayBuild;
            this.nbt.set("mayBuild", NBT.Boolean(mayBuild));
            return this;
        }

        public PlayerAbilities mayFly(boolean mayFly) {
            this.mayFly = mayFly;
            this.nbt.set("mayfly", NBT.Boolean(mayFly));
            return this;
        }

        public PlayerAbilities walkSpeed(float walkSpeed) {
            this.walkSpeed = walkSpeed;
            this.nbt.setFloat("walkSpeed",walkSpeed);
            return this;
        }
    }


    public record Property(String lang, byte viewDistance, ChatMode chatMode, boolean chatColors, byte display, MainHand mainHand, boolean filtering, boolean showList){

    }
    public enum MainHand{
        Left,Right
    }
    public enum ChatMode{
        Enabled,OnlyCommands,Hidden
    }
}
