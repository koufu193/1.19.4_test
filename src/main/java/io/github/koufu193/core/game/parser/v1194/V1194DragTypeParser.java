package io.github.koufu193.core.game.parser.v1194;

import io.github.koufu193.core.game.data.inventory.DragType;
import io.github.koufu193.core.game.parser.DragTypeParser;

public class V1194DragTypeParser implements DragTypeParser {
    private static final DragTypeParser PARSER=new V1194DragTypeParser();
    public static DragTypeParser getParser(){
        return PARSER;
    }
    public DragType parse(int mode, byte button, short slot){
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
}
