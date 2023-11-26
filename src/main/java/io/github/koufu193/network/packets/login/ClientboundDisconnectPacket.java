package io.github.koufu193.network.packets.login;

import io.github.koufu193.core.game.data.component.TextComponent;
import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import org.jetbrains.annotations.NotNull;

/**
 * ログイン処理中にクライアントを切断するときに使うパケット
 */
public class ClientboundDisconnectPacket extends AbstractPacket {
    ClientboundDisconnectPacket(){}

    /**
     * @param reason 切断理由
     */
    public ClientboundDisconnectPacket(@NotNull TextComponent reason){
        this.fields(reason);
    }
    @NotNull
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.Chat);
    }

    @Override
    public int packetId() {
        return 0x00;
    }
}
