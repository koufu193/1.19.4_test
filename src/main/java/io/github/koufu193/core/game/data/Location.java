package io.github.koufu193.core.game.data;

import io.github.koufu193.core.game.world.World;
import io.github.koufu193.core.game.world.chunk.Chunk;

import java.util.Objects;

public class Location implements Cloneable{
    private World world;
    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;
    public Location(double x,double y,double z){
        this(null,x,y,z);
    }
    public Location(World world,double x,double y,double z){
        this(world,x,y,z,0f,0f);
    }
    public Location(World world,double x,double y,double z,float yaw,float pitch){
        this.world=world;
        this.x=x;
        this.y=y;
        this.z=z;
        this.yaw=yaw;
        this.pitch=pitch;
    }
    public Location(double x,double y,double z,float yaw,float pitch){
        this(null,x,y,z,yaw,pitch);
    }

    public Location world(World world) {
        this.world = world;
        return this;
    }

    public World world() {
        return world;
    }
    public int blockX(){
        return (int)x;
    }
    public int blockY(){
        return (int)y;
    }
    public int blockZ(){
        return (int)z;
    }
    public int chunkX(){
        return blockX()>>4;
    }
    public int chunkZ(){
        return blockZ()>>4;
    }
    public Chunk chunk(){
        if(this.world==null) return null;
        return world.chunk(chunkX(),chunkZ());
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    public float pitch() {
        return pitch;
    }

    public double z() {
        return z;
    }

    public float yaw() {
        return yaw;
    }

    public Location pitch(float pitch) {
        this.pitch = pitch;
        return this;
    }

    public Location x(double x) {
        this.x = x;
        return this;
    }

    public Location y(double y) {
        this.y = y;
        return this;
    }

    public Location yaw(float yaw) {
        this.yaw = yaw;
        return this;
    }

    public Location z(double z) {
        this.z = z;
        return this;
    }
    public Location add(double x,double y,double z){
        this.x+=x;
        this.y+=y;
        this.z+=z;
        return this;
    }
    public long toLong(){
        return ((long) (((int) x) & 0x3FFFFFF) << 38) | ((long) (((int) z) & 0x3FFFFFF) << 12) | (((int)y) & 0xFFF);
    }
    public static Location from(long l){
        return new Location((l>>38),(l<<52)>>52,(l<<26)>>26);
    }

    @Override
    public Location clone(){
        return new Location(world(),x(),y(),z(),yaw(),pitch());
    }

    @Override
    public String toString() {
        return "Location{" +
                "world=" + world +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", yaw=" + yaw +
                ", pitch=" + pitch +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location location)) return false;

        if (Double.compare(location.x, x) != 0) return false;
        if (Double.compare(location.y, y) != 0) return false;
        if (Double.compare(location.z, z) != 0) return false;
        if (Float.compare(location.yaw, yaw) != 0) return false;
        if (Float.compare(location.pitch, pitch) != 0) return false;
        return Objects.equals(world, location.world);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = world != null ? world.hashCode() : 0;
        temp = Double.doubleToLongBits(x);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(z);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (yaw != +0.0f ? Float.floatToIntBits(yaw) : 0);
        result = 31 * result + (pitch != +0.0f ? Float.floatToIntBits(pitch) : 0);
        return result;
    }
}
