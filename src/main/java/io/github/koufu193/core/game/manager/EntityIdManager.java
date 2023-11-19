package io.github.koufu193.core.game.manager;

public class EntityIdManager {
    private int now=0;
    public int next(){
        return now++;
    }
}
