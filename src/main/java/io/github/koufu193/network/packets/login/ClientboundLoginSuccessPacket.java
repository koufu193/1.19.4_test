package io.github.koufu193.network.packets.login;

import io.github.koufu193.core.game.data.GameProfile;
import io.github.koufu193.core.properties.Properties;
import io.github.koufu193.core.properties.Property;
import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.UUID;

/**
 * ログインが成功したことを示すパケット
 */
public class ClientboundLoginSuccessPacket extends AbstractPacket {
    ClientboundLoginSuccessPacket(){}

    /**
     * @param uuid プレーヤーのUUID
     * @param name プレーヤーの名前
     * @param properties　プレーヤーの属性
     */
    public ClientboundLoginSuccessPacket(@NotNull UUID uuid,@NotNull String name,@NotNull Properties properties){
        fields(uuid,name,properties);
    }

    /**
     * @param profile プレーヤーのプロファイル
     */
    public ClientboundLoginSuccessPacket(@NotNull GameProfile profile){
        this(profile.id(),profile.name(),profile.properties());
    }
    @NotNull
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.UUID, DataTypes.String, DataTypes.Properties);
    }

    @Override
    public int packetId() {
        return 0x02;
    }
}
