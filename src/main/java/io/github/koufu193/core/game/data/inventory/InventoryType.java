package io.github.koufu193.core.game.data.inventory;

public enum InventoryType {
    PLAYER(0),HORSE(1);
    private final int type;
    InventoryType(int type){
        this.type=type;
    }

    public int typeId() {
        return type;
    }
}
