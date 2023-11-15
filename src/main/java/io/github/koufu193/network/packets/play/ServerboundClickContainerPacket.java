package io.github.koufu193.network.packets.play;

import io.github.koufu193.core.game.data.inventory.ClickType;
import io.github.koufu193.core.game.data.inventory.DragType;
import io.github.koufu193.core.game.data.item.ItemStack;
import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class ServerboundClickContainerPacket extends InventoryPacket{
    private ClickType clickType;
    private DragType dragType;
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.Byte, DataTypes.VarInt, DataTypes.Short, DataTypes.Byte, DataTypes.VarInt, new DataTypes.DataType<SlotData>() {
            @Override
            public byte[] encode(SlotData value) {
                ByteArrayOutputStream output=new ByteArrayOutputStream(this.size(value));
                output.writeBytes(DataTypes.Short.encode(value.number()));
                output.writeBytes(DataTypes.Item.encode(value.data()));
                return output.toByteArray();
            }

            @Override
            public int size(SlotData value) {
                return DataTypes.Short.size(value.number())+DataTypes.Item.size(value.data());
            }

            @Override
            public SlotData decode(ByteBuffer buffer) {
                return new SlotData(DataTypes.Short.decode(buffer),DataTypes.Item.decode(buffer));
            }
        }.array(),DataTypes.Item);
    }

    @Override
    public ServerboundClickContainerPacket fields(Object... fields) {
        super.fields(fields);
        this.clickType= parseClickType(mode(),button(),slot());
        this.dragType=parseDragType(mode(),button(),slot());
        return this;
    }

    public byte windowId(){
        return (byte) fields()[0];
    }
    public int stateId(){
        return (int) fields()[1];
    }
    public ItemStack carriedItem(){
        return (ItemStack) fields()[6];
    }
    public boolean hasCarriedItem(){
        return this.carriedItem()!=null;
    }
    public SlotData[] changedSlots(){
        SlotData[] slots= (SlotData[]) fields()[5];
        return Arrays.copyOf(slots,slots.length);
    }
    public short slot(){
        return (short) fields()[2];
    }
    public byte button(){
        return (byte)fields()[3];
    }
    public int mode(){
        return (int) fields()[4];
    }
    @Override
    public int packetId() {
        return 0x0b;
    }
    public boolean isClickAction(){
        return this.clickType!=null;
    }
    public boolean isDragAction(){
        return this.dragType!=null;
    }
    public @Nullable ClickType clickType(){
        return this.clickType;
    }
    public @Nullable DragType dragType(){
        return this.dragType;
    }
    public static DragType parseDragType(int mode, byte button, short slot){
        if(mode!=5) return null;
        if(button<0||10<button) return DragType.UNKNOWN;
        if((button+1)%4==0) return DragType.UNKNOWN;
        DragType[] lefts=new DragType[]{
                DragType.START_LEFT,DragType.ADD_SLOT_LEFT,DragType.END_LEFT,
        };
        DragType[] middles=new DragType[]{
                DragType.START_MIDDLE,DragType.ADD_SLOT_MIDDLE,DragType.END_MIDDLE
        };
        DragType[] rights=new DragType[]{
                DragType.START_RIGHT,DragType.ADD_SLOT_RIGHT,DragType.END_RIGHT
        };
        DragType type=null;
        if(button<3) type=lefts[button];
        else if(button<7) type=middles[button-4];
        else type=rights[button-8];
        if(!type.isAddSlot()&&slot!=-999) return DragType.UNKNOWN;
        return type;
    }
    private static ClickType parseClickType(int mode, byte button, short slot){
        if(mode==5) return null;
        ClickType type=switch (mode){
            case 0->zeroMode(button,slot);
            case 1->oneMode(button,slot);
            case 2->twoMode(button,slot);
            case 3->threeMode(button,slot);
            case 4->fourMode(button, slot);
            case 6->sixMode(button,slot);
            default -> null;
        };
        if(type==null) return ClickType.UNKNOWN;
        return type;
    }
    private static ClickType zeroMode(byte button,short slot){
        return switch (button){
            case 0->slot==-999?ClickType.OUTSIDE_LEFT:ClickType.LEFT;
            case 1->slot==-999?ClickType.OUTSIDE_RIGHT:ClickType.RIGHT;
            default -> null;
        };
    }
    private static ClickType oneMode(byte button,short slot){
        return switch (button){
            case 0->ClickType.SHIFT_LEFT;
            case 1->ClickType.SHIFT_RIGHT;
            default -> null;
        };
    }
    private static ClickType twoMode(byte button,short slot){
        return switch (button){
            case 40->ClickType.OFFHAND;
            default -> 0<=slot&&slot<=8?ClickType.NUMBER_KEY:null;
        };
    }
    private static ClickType threeMode(byte button,short slot){
        return button==2?ClickType.MIDDLE_CLICK:null;
    }
    private static ClickType fourMode(byte button,short slot){
        return switch (button){
            case 0->ClickType.DROP_KEY;
            case 1->ClickType.DROP_AND_CONTROL_KEY;
            default -> null;
        };
    }
    private static ClickType sixMode(byte button,short slot){
        return switch (button){
            case 0->ClickType.DOUBLE_CLICK;
            case 1->ClickType.PICKUP_ALL;
            default -> null;
        };
    }
    public record SlotData(short number,@NotNull ItemStack data){
        public SlotData(short number, @Nullable ItemStack data){
            this.number=number;
            this.data = (data == null) ? ItemStack.AIR : data;
        }
    }
}
