package io.github.koufu193.network.packets.play;

import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import org.jetbrains.annotations.NotNull;

public class ServerboundPlayerAbilitiesPacket extends AbstractPacket {
    @NotNull
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.Byte);
    }
    public boolean flying(){
        return (flag()&0x02)!=0;
    }
    private byte flag(){
        return (byte)fields()[0];
    }

    @Override
    public int packetId() {
        return 0x1c;
    }
}
