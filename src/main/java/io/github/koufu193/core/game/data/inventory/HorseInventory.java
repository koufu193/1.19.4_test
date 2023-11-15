package io.github.koufu193.core.game.data.inventory;

import io.github.koufu193.core.game.data.item.ItemStack;
import io.github.koufu193.core.game.entities.interfaces.IHorse;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class HorseInventory implements Inventory {
    private final IHorse entity;
    private ItemStack[] slots;
    public HorseInventory(@NotNull IHorse horse,int slotSize){
        this.entity=horse;
        this.slots=new ItemStack[slotSize];
    }
    @Override
    public InventoryType type() {
        return InventoryType.HORSE;
    }

    @NotNull
    @Override
    public ItemStack get(int slotId) {
        return this.slots[slotId];
    }
    public int slotSize(){
        return this.slots.length;
    }

    @Override
    public ItemStack[] getAllContents() {
        return Arrays.copyOf(slots,slots.length);
    }
    public IHorse entity(){
        return this.entity;
    }
}
