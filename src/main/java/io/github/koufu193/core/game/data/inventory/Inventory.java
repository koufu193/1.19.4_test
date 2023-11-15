package io.github.koufu193.core.game.data.inventory;

import io.github.koufu193.core.game.data.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Inventory {
    InventoryType type();
    @NotNull
    ItemStack get(int slotId);
    ItemStack[] getAllContents();
}
