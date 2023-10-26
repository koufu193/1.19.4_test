package io.github.koufu193.network.packets.play;

import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class ClientboundRemovePlayerInfoPacket extends AbstractPacket {
    public ClientboundRemovePlayerInfoPacket(){}
    public ClientboundRemovePlayerInfoPacket(@NotNull UUID... ids){
        fields((Object) ids);
    }
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.UUID.array());
    }

    @Override
    public int packetId() {
        return 0x39;
    }
}
