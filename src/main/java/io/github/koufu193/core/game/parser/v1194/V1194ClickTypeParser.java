package io.github.koufu193.core.game.parser.v1194;

import io.github.koufu193.core.game.data.inventory.ClickType;
import io.github.koufu193.core.game.parser.ClickTypeParser;

public class V1194ClickTypeParser implements ClickTypeParser {
    private static final ClickTypeParser PARSER=new V1194ClickTypeParser();
    public static ClickTypeParser getParser(){
        return PARSER;
    }
    public ClickType parse(int mode, byte button, short slot){
        if(mode<0||mode==5||6<mode) return null;
        ClickType type=switch (mode){
            case 0->zeroMode(button,slot);
            case 1->oneMode(button,slot);
            case 2->twoMode(button,slot);
            case 3->threeMode(button,slot);
            case 4->fourMode(button, slot);
            case 6->sixMode(button,slot);
            default -> null;
        };
        if(type==null) return ClickType.UNKNOWN;
        return type;
    }
    private static ClickType zeroMode(byte button,short slot){
        return switch (button){
            case 0->slot==-999?ClickType.OUTSIDE_LEFT:ClickType.LEFT;
            case 1->slot==-999?ClickType.OUTSIDE_RIGHT:ClickType.RIGHT;
            default -> null;
        };
    }
    private static ClickType oneMode(byte button,short slot){
        return switch (button){
            case 0->ClickType.SHIFT_LEFT;
            case 1->ClickType.SHIFT_RIGHT;
            default -> null;
        };
    }
    private static ClickType twoMode(byte button,short slot){
        return switch (button){
            case 40->ClickType.OFFHAND;
            default -> 0<=slot&&slot<=8?ClickType.NUMBER_KEY:null;
        };
    }
    private static ClickType threeMode(byte button,short slot){
        return button==2?ClickType.MIDDLE_CLICK:null;
    }
    private static ClickType fourMode(byte button,short slot){
        return switch (button){
            case 0->ClickType.DROP_KEY;
            case 1->ClickType.DROP_AND_CONTROL_KEY;
            default -> null;
        };
    }
    private static ClickType sixMode(byte button,short slot){
        return switch (button){
            case 0->ClickType.DOUBLE_CLICK;
            case 1->ClickType.PICKUP_ALL;
            default -> null;
        };
    }
}
