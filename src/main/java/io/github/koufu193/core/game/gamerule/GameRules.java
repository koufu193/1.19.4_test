package io.github.koufu193.core.game.gamerule;

import java.util.HashMap;
import java.util.Map;

public class GameRules {
    private final Map<String,GameRule<?>> gameRules=new HashMap<>();
    public final BoolGameRule doImmediateRespawn=make("doImmediateRespawn",false);
    private BoolGameRule make(String name,boolean value){
        BoolGameRule gameRule=new BoolGameRule(name,value);
        this.gameRules.put(name,gameRule);
        return gameRule;
    }
    private IntGameRule make(String name,int value) {
        IntGameRule gameRule = new IntGameRule(name, value);
        this.gameRules.put(name, gameRule);
        return gameRule;
    }

    public GameRule<?> getGameRule(String name) {
        return this.gameRules.get(name);
    }
}
