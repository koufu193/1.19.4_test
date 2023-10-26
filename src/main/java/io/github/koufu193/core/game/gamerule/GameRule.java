package io.github.koufu193.core.game.gamerule;

public sealed class GameRule<T> permits BoolGameRule, IntGameRule {
    private final String name;
    private T value;
    public GameRule(String name,T value){
        this.name=name;
        this.value=value;
    }
    public String name(){
        return this.name;
    }
    public T value(){
        return this.value;
    }
    public void value(T value){
        this.value=value;
    }
}
