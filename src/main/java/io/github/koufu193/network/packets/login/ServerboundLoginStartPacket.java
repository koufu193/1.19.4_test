package io.github.koufu193.network.packets.login;

import io.github.koufu193.core.game.data.GameProfile;
import io.github.koufu193.core.properties.Properties;
import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.UUID;

public class ServerboundLoginStartPacket extends AbstractPacket {
    @NotNull
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.String, DataTypes.Bool.encodeOptional(a-> a[2] !=null),DataTypes.UUID.decodeOptional(a->(Boolean)a[1]).encodeIfNonnull());
    }

    @Override
    public int packetId() {
        return 0x00;
    }

    /**
     * プレーヤーの名前を取得
     * @return プレーヤーの名前
     */
    @NotNull
    public String name(){
        return (String)fields()[0];
    }

    /**
     * プレーヤーのUUIDを取得
     * @return プレーヤーのUUID
     */
    @Nullable
    public UUID uuid(){
        return (UUID)fields()[2];
    }

    /**
     * UUIDが提供されているかどうか調べる
     * @return UUIDが提供されているならtrue,そうでないならfalse
     */
    public boolean hasUUID(){
        return fields()[2]!=null;
    }

    /**
     * 提供された情報から{@link GameProfile}を生成する<br>
     * UUIDが提供されていなければUUIDはランダムになる
     */
    public GameProfile toProfile(){
        String name= name();
        UUID uuid= uuid();
        if(uuid==null) uuid=UUID.randomUUID();
        return new GameProfile(uuid,name, Properties.EMPTY);
    }
}
