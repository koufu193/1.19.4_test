package io.github.koufu193.core.game.data;

import org.jetbrains.annotations.NotNull;

public enum Difficulty {
    Peaceful,Easy,Normal,Hard;
    public static Difficulty valueOfWithIgnoringCase(@NotNull String name){
        for(Difficulty difficulty:values()){
            if(name.equalsIgnoreCase(difficulty.name())) return difficulty;
        }
        return null;
    }
    public static Difficulty valueOf(int id){
        if(id<0||values().length<=id) return null;
        return values()[id];
    }
}
