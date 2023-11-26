package io.github.koufu193.network.packets.play;

import io.github.koufu193.core.files.NBTFiles;
import io.github.koufu193.core.files.ServerProperties;
import io.github.koufu193.core.game.data.Identifier;
import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.core.game.entities.interfaces.IPlayer;
import io.github.koufu193.core.game.gamerule.GameRules;
import io.github.koufu193.core.game.world.World;
import io.github.koufu193.core.files.world.level.generation.WorldGenSettings;
import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import io.github.koufu193.server.MinecraftServer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jglrxavpok.hephaistos.nbt.NBT;

public class ClientboundLoginPacket extends AbstractPacket {
    ClientboundLoginPacket(){}
    public ClientboundLoginPacket(@NotNull IPlayer player){
        this(player,player.server());
    }
    public ClientboundLoginPacket(@NotNull IPlayer player,@NotNull MinecraftServer server){
        this(player,server,server.serverProperties(),server.gameRules(),player.world(),player.world().worldGenSettings(),player.lastDeathLocation());
    }
    public ClientboundLoginPacket(@NotNull IPlayer player, @NotNull MinecraftServer server , @NotNull ServerProperties properties, @NotNull GameRules gameRules, @NotNull World world, @NotNull WorldGenSettings genSettings,@Nullable Location lastDeathLocation){
        this(
                player.entityId(),
                properties.hardcore(),
                (byte) player.gameMode().id(),
                (byte) player.previousGameMode().id(),
                server.worlds().stream().map(World::name).toArray(Identifier[]::new),
                NBTFiles.registryCodec(),
                world.dimensionType().id(),
                world.name(),
                genSettings.hashedSeed(),
                properties.maxPlayers(),
                player.viewDistance(),
                player.viewDistance(),
                false,
                gameRules.doImmediateRespawn.value(),
                world.isDebugWorld(),
                world.isFlatWorld(),
                lastDeathLocation!=null,
                lastDeathLocation!=null?lastDeathLocation.world().name():null,
                lastDeathLocation
        );
    }
    public ClientboundLoginPacket(
            int entityId,
            boolean hardcore,
            byte gameMode,
            byte prevGameMode,
            @NotNull Identifier[] dimensions,
            @NotNull NBT registryCodec,
            @NotNull Identifier dimensionType,
            @NotNull Identifier dimensionName,
            long hashedSeed,
            int maxPlayers,
            int viewDistance,
            int simulationsDistance,
            boolean reduceDebugInfo,
            boolean enableRespawnScreen,
            boolean debug,
            boolean flat,
            boolean hasDeathLocation,
            @Nullable Identifier deathDimensionName,
            @Nullable Location deathLocation){
        fields(entityId,hardcore,gameMode,prevGameMode,dimensions,registryCodec,dimensionType,dimensionName,hashedSeed,maxPlayers,viewDistance,simulationsDistance,reduceDebugInfo,enableRespawnScreen,debug,flat,hasDeathLocation,deathDimensionName,deathLocation);
    }
    @NotNull
    @Override
    public PacketFormat format() {
        return PacketFormat.of(
                DataTypes.Int, DataTypes.Bool, DataTypes.Byte,DataTypes.Byte,DataTypes.Identifier.array(),DataTypes.NBT,DataTypes.Identifier,DataTypes.Identifier,
                DataTypes.Long,DataTypes.VarInt,DataTypes.VarInt,DataTypes.VarInt,DataTypes.Bool,DataTypes.Bool,DataTypes.Bool,DataTypes.Bool,DataTypes.Bool,
                DataTypes.Identifier.encodeOptional(a->(Boolean)a[16]).encodeIfNonnull().decodeOptional(a->(Boolean)a[16]),DataTypes.LongLocation.encodeOptional(a->(Boolean)a[16]).encodeIfNonnull().decodeOptional(a->(Boolean)a[16])
        );
    }

    @Override
    public int packetId() {
        return 0x28;
    }
}
