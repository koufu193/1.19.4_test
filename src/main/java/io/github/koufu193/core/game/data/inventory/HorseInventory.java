package io.github.koufu193.core.game.data.inventory;

import io.github.koufu193.core.game.data.item.ItemStack;
import io.github.koufu193.core.game.entities.interfaces.IHorse;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class HorseInventory implements Inventory {
    private final IHorse entity;
    private final ItemStack[] items;
    public HorseInventory(@NotNull IHorse horse,int slotSize){
        this.entity=horse;
        this.items =new ItemStack[slotSize];
        Arrays.fill(items,ItemStack.AIR);
    }
    @Override
    public InventoryType type() {
        return InventoryType.HORSE;
    }

    @NotNull
    @Override
    public ItemStack get(int slotId) {
        return this.items[slotId];
    }

    @Override
    public void set(int slotId, @Nullable ItemStack item) {
        if(item==null) item=ItemStack.AIR;
        items[slotId]=item;
    }

    public int size(){
        return this.items.length;
    }

    @Override
    public ItemStack[] getAllContents() {
        return Arrays.copyOf(items, items.length);
    }

    public IHorse entity(){
        return this.entity;
    }
}
