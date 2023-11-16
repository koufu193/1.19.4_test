package io.github.koufu193.core.game.data.inventory;

import io.github.koufu193.core.game.data.item.ItemStack;
import org.bukkit.craftbukkit.v1_19_R2.CraftServer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class GenericInventory implements Inventory{
    private static final Set<InventoryType> genericInventoryTypes=new HashSet<>(){{
        add(InventoryType.GENERIC_9x6);
        add(InventoryType.GENERIC_9x5);
        add(InventoryType.GENERIC_9x4);
        add(InventoryType.GENERIC_9x3);
        add(InventoryType.GENERIC_9x2);
        add(InventoryType.GENERIC_9x1);
        add(InventoryType.GENERIC_3x3);
    }};
    private final InventoryType type;
    private final ItemStack[] items;
    public GenericInventory(@NotNull InventoryType type){
        if(!isGenericInventoryType(type)) throw new IllegalArgumentException(String.format("'%s' is not Generic InventoryType",type.name()));
        this.type=type;
        this.items=new ItemStack[type.defaultSize()];
        Arrays.fill(items,ItemStack.AIR);
    }
    public GenericInventory(int size){
        this(Objects.requireNonNull(findInventoryType(size),()->String.format("size of %d not found",size)));
    }
    @Override
    public InventoryType type() {
        return this.type;
    }

    @NotNull
    @Override
    public ItemStack get(int slotId) {
        return this.items[slotId];
    }

    @Override
    public void set(int slotId, @Nullable ItemStack item) {
        if(item==null) item=ItemStack.AIR;
        this.items[slotId]=item;
    }

    @Override
    public ItemStack[] getAllContents() {
        return Arrays.copyOf(this.items,this.items.length);
    }

    @Override
    public int size() {
        return this.type.defaultSize();
    }

    private static boolean isGenericInventoryType(@NotNull InventoryType type){
        return genericInventoryTypes.contains(type);
    }
    private static InventoryType findInventoryType(int size){
        if(size<0||InventoryType.GENERIC_9x6.defaultSize()<size)
            throw new IllegalArgumentException(String.format("size must be between %d and %d",1,InventoryType.GENERIC_9x6.defaultSize()));
        if(size%9!=0) throw new IllegalArgumentException("size must be a multiple of 9");
        for(InventoryType type:genericInventoryTypes) {
            if (type == InventoryType.GENERIC_3x3) continue;
            if (size == type.defaultSize()) return type;
        }
        return null;
    }
}
