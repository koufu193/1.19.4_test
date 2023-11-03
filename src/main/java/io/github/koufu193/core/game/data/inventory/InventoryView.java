package io.github.koufu193.core.game.data.inventory;

import org.jetbrains.annotations.NotNull;

public class InventoryView {
    private final Inventory inventory;
    private byte windowId;

    public InventoryView(@NotNull Inventory inventory,byte windowId) {
        this.inventory = inventory;
        this.windowId=windowId;
    }
    public Inventory inventory(){
        return this.inventory;
    }
    public byte windowId(){
        return this.windowId;
    }
}
