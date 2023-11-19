package io.github.koufu193.core.game.data;

public record Angle(byte angle) {
    public Angle(int angle){
        this((byte) (angle&0xff));
    }
    public Angle(float angle){
        this(toByteAngle(angle));
    }
    private static byte toByteAngle(float angle){
        angle%=360;
        //if(angle<0) angle=360+angle;
        return (byte) (angle*(double)256/360);
    }
}
