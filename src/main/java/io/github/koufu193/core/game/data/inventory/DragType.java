package io.github.koufu193.core.game.data.inventory;

public enum DragType {
    START_LEFT,START_MIDDLE,START_RIGHT,
    ADD_SLOT_LEFT,ADD_SLOT_MIDDLE,ADD_SLOT_RIGHT,
    END_LEFT,END_MIDDLE,END_RIGHT,UNKNOWN;
    public boolean isStart(){
        return this==START_LEFT||this==START_MIDDLE||this==START_RIGHT;
    }
    public boolean isEnd(){
        return this==END_LEFT||this==END_MIDDLE||this==END_RIGHT;
    }
    public boolean isAddSlot(){
        return this==ADD_SLOT_LEFT||this==ADD_SLOT_MIDDLE||this==ADD_SLOT_RIGHT;
    }
}
