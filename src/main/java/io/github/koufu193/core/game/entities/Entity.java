package io.github.koufu193.core.game.entities;

import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.core.game.entities.interfaces.IEntity;
import io.github.koufu193.core.game.world.World;
import io.github.koufu193.server.MinecraftServer;
import org.jetbrains.annotations.NotNull;
import org.jglrxavpok.hephaistos.collections.ImmutableIntArray;
import org.jglrxavpok.hephaistos.nbt.NBT;
import org.jglrxavpok.hephaistos.nbt.NBTList;
import org.jglrxavpok.hephaistos.nbt.mutable.MutableNBTCompound;

import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.UUID;

public class Entity implements IEntity {
    protected final MinecraftServer server;
    protected final int entityId;
    protected final UUID uniqueId;
    protected final MutableNBTCompound nbt;
    protected String customName;
    protected boolean customNameVisitable;
    protected Location location;
    public Entity(MinecraftServer server, int entityId, MutableNBTCompound nbt, World world) {
        this.server=server;
        this.entityId=entityId;
        this.customName=nbt.getString("CustomName");
        this.nbt=nbt;
        this.customNameVisitable= Objects.requireNonNullElse(nbt.getBoolean("CustomNameVisible"),false);
        ImmutableIntArray uuidInts=nbt.getIntArray("UUID");
        ByteBuffer buffer=ByteBuffer.allocate(Integer.BYTES*4)
                .putInt(uuidInts.get(0))
                .putInt(uuidInts.get(1))
                .putInt(uuidInts.get(2))
                .putInt(uuidInts.get(3));
        this.uniqueId=UUID.nameUUIDFromBytes(buffer.array());
        NBTList<NBT> pos=nbt.getList("Pos");
        NBTList<NBT> rotation=nbt.getList("Rotation");
        this.location=new Location(world, (Double) pos.get(0).getValue(),(Double)pos.get(1).getValue(),(Double)pos.get(2).getValue())
                .yaw((Float)rotation.get(0).getValue()).pitch((Float)rotation.get(1).getValue());
    }

    @Override
    public int entityId() {
        return this.entityId;
    }

    @Override
    public UUID uniqueId() {
        return this.uniqueId;
    }


    @Override
    public String customName() {
        return this.customName;
    }
    public void customName(String customName){
        this.customName=customName;
        if(customName==null||"".equals(customName)){
            this.nbt.remove("CustomName");
        }else this.nbt.setString("CustomName",this.customName);
    }

    @Override
    public void customNameVisitable(boolean visitable) {
        this.customNameVisitable=visitable;
        this.nbt.set("CustomNameVisible",NBT.Boolean(visitable));
    }

    @Override
    public MutableNBTCompound getNBT() {
        return this.nbt;
    }

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
}
