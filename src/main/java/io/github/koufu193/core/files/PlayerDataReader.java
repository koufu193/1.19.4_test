package io.github.koufu193.core.files;

import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.server.MinecraftServer;
import io.github.koufu193.util.NBTUtils;
import org.jetbrains.annotations.NotNull;
import org.jglrxavpok.hephaistos.nbt.*;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public record PlayerDataReader(MinecraftServer server, File playerDataFolder) {
    public PlayerDataReader {
        if (!playerDataFolder.exists()) playerDataFolder.mkdirs();
    }
    public NBTCompound get(@NotNull UUID uuid) {
        File f = new File(this.playerDataFolder, uuid + ".dat");
        if(!f.exists()) return make(uuid);
        try (NBTReader reader = new NBTReader(f, CompressedProcesser.GZIP)) {
            return (NBTCompound) reader.read();
        } catch (IOException | NBTException e) {
            throw new RuntimeException(e);
        }
    }
    private NBTCompound make(UUID uuid){
        Location spawnLocation=server.defaultSpawnWorld().defaultSpawnLocation();
        return NBT.Compound((compound)->{
            compound.put("Dimension",NBT.String(String.valueOf(spawnLocation.world().nameId())));
            compound.put("UUID",NBTUtils.convertUUIDtoIntsNBT(uuid));
            compound.put("Pos", NBTUtils.toDoubleNBTList(spawnLocation.x(),spawnLocation.y(),spawnLocation.z()));
            compound.put("Rotation",NBTUtils.toFloatNBTList(spawnLocation.yaw(),spawnLocation.pitch()));
            compound.put("WorldUUIDMost",NBT.Long(spawnLocation.world().uid().uid().getMostSignificantBits()));
            compound.put("WorldUUIDLeast",NBT.Long(spawnLocation.world().uid().uid().getLeastSignificantBits()));
        });
    }
    public void write(@NotNull UUID uuid,@NotNull NBTCompound nbt){
        File f = new File(this.playerDataFolder, uuid + ".dat");
        try (NBTWriter writer = new NBTWriter(f, CompressedProcesser.GZIP)) {
            writer.writeNamed("",nbt);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static PlayerDataReader fromWorldFolder(MinecraftServer server, File worldFolder){
        return new PlayerDataReader(server,new File(worldFolder,"playerdata"));
    }
}
