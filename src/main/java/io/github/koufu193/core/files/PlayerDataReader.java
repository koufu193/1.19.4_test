package io.github.koufu193.core.files;

import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.core.game.entities.interfaces.IPlayer;
import io.github.koufu193.core.game.world.World;
import io.github.koufu193.server.MinecraftServer;
import io.github.koufu193.util.FileUtils;
import io.github.koufu193.util.NBTUtils;
import org.jetbrains.annotations.NotNull;
import org.jglrxavpok.hephaistos.nbt.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

/**
 * world/playerdataフォルダを読み書きするクラス
 * @param server サーバー
 * @param playerDataFolder プレーヤーデータがあるフォルダ
 */
public record PlayerDataReader(@NotNull MinecraftServer server,@NotNull Path playerDataFolder) {
    public PlayerDataReader {
        FileUtils.createDirectories(playerDataFolder);
    }

    /**
     * プレーヤーデータを読み込む
     * @param playerUuid 読み込むプレーヤーのUUID
     * @return 読み込まれたデータ<br>ファイルが存在しなかった場合はデータを作る(書き込みはしない)
     */
    @NotNull
    public NBTCompound getPlayerNBT(@NotNull UUID playerUuid) {
        Path playerFile = getPlayerNBTFile(playerUuid);
        if(Files.notExists(playerFile)) return makePlayerNBT(playerUuid);
        try (NBTReader playerNBTReader = new NBTReader(playerFile, CompressedProcesser.GZIP)) {
            return (NBTCompound) playerNBTReader.read();
        } catch (IOException | NBTException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * プレーヤーデータを書き込む
     * @param playerUuid 書き込むプレーヤーのUUID
     * @param playerNBT 書き込むデータ
     */
    public void writePlayerNBT(@NotNull UUID playerUuid, @NotNull NBTCompound playerNBT){
        Path playerFile=getPlayerNBTFile(playerUuid);
        try (NBTWriter writer = new NBTWriter(playerFile, CompressedProcesser.GZIP)) {
            writer.writeNamed("",playerNBT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * プレーヤーから書き込む
     * @param player 書き込むプレーヤーのデータ
     */
    public void writePlayerNBT(@NotNull IPlayer player){
        writePlayerNBT(player.uniqueId(),player.toCompound());
    }

    @NotNull
    private Path getPlayerNBTFile(@NotNull UUID uuid){
        return this.playerDataFolder.resolve(uuid+".dat");
    }
    @NotNull
    private NBTCompound makePlayerNBT(@NotNull UUID uuid){
        Location spawnLocation=server.defaultSpawnWorld().defaultSpawnLocation();
        World spawnWorld=server.defaultSpawnWorld();
        return NBT.Compound((compound)->{
            compound.put("NBTDimension",NBT.String(String.valueOf(spawnWorld.name())));
            compound.put("UUID",NBTUtils.convertUUIDtoIntsNBT(uuid));
            compound.put("Pos", NBTUtils.toDoubleNBTList(spawnLocation.x(),spawnLocation.y(),spawnLocation.z()));
            compound.put("Rotation",NBTUtils.toFloatNBTList(spawnLocation.yaw(),spawnLocation.pitch()));
            compound.put("Dimension",NBT.String(spawnWorld.name().toString()));
            compound.put("WorldUUIDMost",NBT.Long(spawnWorld.uid().getMostSignificantBits()));
            compound.put("WorldUUIDLeast",NBT.Long(spawnWorld.uid().getLeastSignificantBits()));
        });
    }
}
