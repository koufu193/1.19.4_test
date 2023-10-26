package io.github.koufu193.core.game.data;

public record Angle(byte angle) {
    public Angle(int angle){
        this((byte) (angle&0xff));
    }
}
