package io.github.koufu193.network.packets.play;

import io.github.koufu193.core.game.data.Difficulty;
import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import org.jetbrains.annotations.NotNull;

public class ClientboundChangeDifficultyPacket extends AbstractPacket {
    public ClientboundChangeDifficultyPacket(@NotNull Difficulty difficulty,boolean locked){
        fields((byte)difficulty.ordinal(),locked);
    }
    public ClientboundChangeDifficultyPacket(){
        this(Difficulty.Normal,false);
    }
    @NotNull
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.Byte,DataTypes.Bool);
    }

    @Override
    public int packetId() {
        return 0x0c;
    }
}
