package io.github.koufu193.core.game.data.inventory;

public enum InventoryType {
    PLAYER(46),HORSE(1),
    GENERIC_9x1(9*1),GENERIC_9x2(9*2),GENERIC_9x3(9*3),GENERIC_9x4(9*4),GENERIC_9x5(9*5),GENERIC_9x6(9*6),GENERIC_3x3(3*3);
    private final int defaultSize;
    InventoryType(int defaultSize){
        this.defaultSize=defaultSize;
    }
    public int defaultSize(){
        return this.defaultSize;
    }
}
