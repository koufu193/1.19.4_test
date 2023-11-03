package io.github.koufu193.core.game.data.inventory;

import io.github.koufu193.core.game.data.item.ItemStack;
import org.bukkit.craftbukkit.v1_19_R2.inventory.CraftInventoryPlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerInventory implements Inventory{
    private static final int ITEMS_LENGTH=36;
    private int selectedSlot;
    private final ItemStack[] items=new ItemStack[ITEMS_LENGTH];
    //head,chest,leg,foot offhand
    private final ItemStack[] extraItems=new ItemStack[5];
    private final ItemStack[] craftingInput=new ItemStack[4];
    private final ItemStack craftingOutput=ItemStack.AIR;
    {
        Arrays.fill(items,ItemStack.AIR);
        Arrays.fill(extraItems,ItemStack.AIR);
        Arrays.fill(craftingInput,ItemStack.AIR);
    }

    @NotNull
    @Override
    public ItemStack get(int slotId) {
        if(0<=slotId&&slotId<items.length) return this.items[slotId];
        PlayerArmor armor=PlayerArmor.findFromSlotId(slotId);
        if(armor==null) throw new IllegalArgumentException(String.format("invalid slotId %d",slotId));
        return this.get(armor);
    }

    @Override
    public ItemStack[] getAllContents() {
        List<ItemStack> contents=new ArrayList<>(items.length+extraItems.length+craftingInput.length+1);
        contents.add(this.craftingOutput);
        contents.addAll(Arrays.asList(this.craftingInput));
        contents.addAll(Arrays.asList(extraItems).subList(0,extraItems.length-1));
        contents.addAll(Arrays.asList(reverseForPacket(items)));
        contents.add(get(PlayerArmor.OFFHAND));
        return contents.toArray(ItemStack[]::new);
    }
    public int selectedSlot(){
        return this.selectedSlot;
    }
    public void selectedSlot(int selectedSlot){
        if(selectedSlot<0||9<=selectedSlot) throw new IllegalArgumentException(String.format("selectedSlot must be between %d and %d",0,8));
        this.selectedSlot=selectedSlot;
    }

    public ItemStack get(@NotNull PlayerArmor armor){
        return extraItems[armor.ordinal()];
    }
    public void set(@NotNull PlayerArmor armor,ItemStack item){
        if(item==null) item=ItemStack.AIR;
        extraItems[armor.ordinal()]=item.clone();
    }
    public void set(int slotId,ItemStack item){
        if(item==null) item=ItemStack.AIR;
        if(0<=slotId&&slotId<items.length){
            this.items[slotId]=item.clone();
            return;
        }
        PlayerArmor armor=PlayerArmor.findFromSlotId(slotId);
        if(armor==null) throw new IllegalArgumentException(String.format("invalid slotId %d",slotId));
        set(armor,item);
    }
    private static ItemStack[] reverseForPacket(@NotNull ItemStack[] items){
        if(items.length!=ITEMS_LENGTH) throw new IllegalArgumentException(String.format("items length %d must be %d",items.length,ITEMS_LENGTH));
        ItemStack[] result=new ItemStack[items.length];
        for(int i=0;i<2;i++){
            for(int j=0;j<9;j++){
                result[i*9+j]=items[(3-i)*9+j];
                result[(3-i)*9+j]=items[i*9+j];
            }
        }
        return result;
    }
    public enum PlayerArmor {
        HEAD(103),CHEST(102),LEGS(101),BOOTS(100),OFFHAND(-106);
        private final int slotId;
        PlayerArmor(int slotId){
            this.slotId=slotId;
        }
        public int slotId(){
            return this.slotId;
        }
        public static @Nullable PlayerArmor findFromSlotId(int slotId){
            for(PlayerArmor armor:values()){
                if(armor.slotId()==slotId) return armor;
            }
            return null;
        }
    }
}
