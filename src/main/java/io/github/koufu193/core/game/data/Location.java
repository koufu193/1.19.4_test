package io.github.koufu193.core.game.data;

import io.github.koufu193.core.game.world.World;
import io.github.koufu193.core.game.world.chunk.Chunk;

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
}
