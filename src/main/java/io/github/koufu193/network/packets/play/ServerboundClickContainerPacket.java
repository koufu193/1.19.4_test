package io.github.koufu193.network.packets.play;

import io.github.koufu193.core.game.data.inventory.ClickType;
import io.github.koufu193.core.game.data.inventory.DragType;
import io.github.koufu193.core.game.data.item.ItemStack;
import io.github.koufu193.core.game.parser.Parsers;
import io.github.koufu193.core.game.parser.v1194.V1194ClickTypeParser;
import io.github.koufu193.core.game.parser.v1194.V1194DragTypeParser;
import io.github.koufu193.core.game.parser.v1194.V1194Parsers;
import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class ServerboundClickContainerPacket extends InventoryPacket {
    private static final Parsers PARSERS= V1194Parsers.getParsers();
    private ClickType clickType;
    private DragType dragType;

    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.Byte, DataTypes.VarInt, DataTypes.Short, DataTypes.Byte, DataTypes.VarInt, new DataTypes.DataType<SlotData>() {
            @Override
            public byte[] encode(SlotData value) {
                ByteArrayOutputStream output = new ByteArrayOutputStream(this.size(value));
                output.writeBytes(DataTypes.Short.encode(value.number()));
                output.writeBytes(DataTypes.Item.encode(value.data()));
                return output.toByteArray();
            }

            @Override
            public int size(SlotData value) {
                return DataTypes.Short.size(value.number()) + DataTypes.Item.size(value.data());
            }

            @Override
            public SlotData decode(ByteBuffer buffer) {
                return new SlotData(DataTypes.Short.decode(buffer), DataTypes.Item.decode(buffer));
            }
        }.array(), DataTypes.Item);
    }

    @Override
    public ServerboundClickContainerPacket fields(Object... fields) {
        super.fields(fields);
        this.clickType = PARSERS.clickTypeParser().parse(mode(), button(), slot());
        this.dragType = PARSERS.dragTypeParser().parse(mode(), button(), slot());
        return this;
    }

    public byte windowId() {
        return (byte) fields()[0];
    }

    public int stateId() {
        return (int) fields()[1];
    }

    public ItemStack carriedItem() {
        return (ItemStack) fields()[6];
    }

    public boolean hasCarriedItem() {
        return this.carriedItem() != null;
    }

    public SlotData[] changedSlots() {
        SlotData[] slots = (SlotData[]) fields()[5];
        return Arrays.copyOf(slots, slots.length);
    }

    public short slot() {
        return (short) fields()[2];
    }

    public byte button() {
        return (byte) fields()[3];
    }

    public int mode() {
        return (int) fields()[4];
    }

    @Override
    public int packetId() {
        return 0x0b;
    }

    public boolean isClickAction() {
        return this.clickType != null;
    }

    public boolean isDragAction() {
        return this.dragType != null;
    }

    public @Nullable ClickType clickType() {
        return this.clickType;
    }

    public @Nullable DragType dragType() {
        return this.dragType;
    }

    public record SlotData(short number, @NotNull ItemStack data) {
        public SlotData(short number, @Nullable ItemStack data) {
            this.number = number;
            this.data = (data == null) ? ItemStack.AIR : data;
        }
    }
}
