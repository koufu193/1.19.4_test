package io.github.koufu193.core.game.entities;

import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.core.game.entities.interfaces.IEntity;
import io.github.koufu193.core.game.world.World;
import io.github.koufu193.server.MinecraftServer;
import io.github.koufu193.util.NBTUtils;
import org.jetbrains.annotations.NotNull;
import org.jglrxavpok.hephaistos.nbt.NBT;
import org.jglrxavpok.hephaistos.nbt.NBTByte;
import org.jglrxavpok.hephaistos.nbt.NBTCompound;
import org.jglrxavpok.hephaistos.nbt.NBTCompoundLike;
import org.jglrxavpok.hephaistos.nbt.mutable.MutableNBTCompound;

import java.util.Objects;
import java.util.UUID;

public class Entity implements IEntity {
    protected final MinecraftServer server;
    protected final int entityId;
    protected final UUID uniqueId;
    protected final NBTCompoundLike nbt;
    protected String customName;
    protected boolean customNameVisitable;
    protected Location location;
    protected boolean onGround;

    public Entity(@NotNull MinecraftServer server, int entityId,@NotNull NBTCompoundLike nbt,@NotNull World world) {
        this.nbt = nbt;
        this.server = server;
        this.entityId = entityId;
        this.customName = nbt.getString("CustomName");
        this.customNameVisitable = Objects.requireNonNullElse(nbt.getBoolean("CustomNameVisible"), false);
        this.uniqueId = NBTUtils.convertIntsNBTToUUID(Objects.requireNonNull(nbt.getIntArray("UUID")));
        Location rotation = NBTUtils.convertFloatRotationToLocation(Objects.requireNonNull(nbt.getList("Rotation")));
        this.location = NBTUtils.convertDoublePositionToLocation(Objects.requireNonNull(nbt.getList("Pos")))
                .yaw(rotation.yaw()).pitch(rotation.pitch())
                .world(world);
        this.onGround=Objects.requireNonNullElse(nbt.getBoolean("OnGround"),false);
    }

    @Override
    public int entityId() {
        return this.entityId;
    }

    @NotNull
    @Override
    public UUID uniqueId() {
        return this.uniqueId;
    }


    @Override
    public String customName() {
        return this.customName;
    }

    public void customName(String customName) {
        this.customName = customName;
    }

    @Override
    public void customNameVisitable(boolean visitable) {
        this.customNameVisitable = visitable;
    }
    @Override
    public boolean onGround() {
        return this.onGround;
    }


    @NotNull
    @Override
    public Location location() {
        return this.location.clone();
    }

    @Override
    public boolean customNameVisitable() {
        return this.customNameVisitable;
    }

    @Override
    public void teleport(@NotNull Location location) {
    }

    @NotNull
    @Override
    public MinecraftServer server() {
        return this.server;
    }

    public void onGround(boolean onGround){
        this.onGround=onGround;
    }
    public void location(@NotNull Location location,boolean onGround){
        this.location=location;
        this.onGround=onGround;
    }
    public void location(@NotNull Location location){
        this.location(location,this.onGround);
    }

    @Override
    public NBTCompound toCompound() {
        return NBT.Compound(root->{
            root.setAll(this.nbt);
            if(customName!=null&&!"".equals(customName)){
                root.setString("customName",this.customName);
            }else root.remove("customName");
            root.set("CustomNameVisible",NBT.Boolean(this.customNameVisitable));
            root.set("OnGround",NBT.Boolean(this.onGround));
            root.set("UUID",NBTUtils.convertUUIDtoIntsNBT(this.uniqueId));
            root.set("Rotation",NBTUtils.toFloatNBTList(this.location.yaw(),this.location.pitch()));
            root.set("Pos",NBTUtils.toDoubleNBTList(this.location.x(),this.location.y(),this.location.z()));
        });
    }
}
