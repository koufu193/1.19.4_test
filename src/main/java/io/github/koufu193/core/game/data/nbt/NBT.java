package io.github.koufu193.core.game.data.nbt;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.*;

public class NBT {
    private static final Set<Class<?>> acceptClasses = new HashSet<>(Arrays.asList(
            Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class, String.class, NBT.class
    ));
    private final HashMap<String, Object> map = new HashMap<>();
    private final Set<NBT> childNbts = new HashSet<>();

    public Object get(String name) {
        return Objects.requireNonNull(map.get(name));
    }

    public int getInt(String name) {
        return (Integer) Objects.requireNonNull(map.get(name));
    }

    public short getShort(String name) {
        return (Short) Objects.requireNonNull(map.get(name));
    }

    public double getDouble(String name) {
        return (Double) Objects.requireNonNull(map.get(name));
    }

    public float getFloat(String name) {
        return (Float) Objects.requireNonNull(map.get(name));
    }

    public String getString(String name) {
        return (String) Objects.requireNonNull(map.get(name));
    }

    public long getLong(String name) {
        return (Long) Objects.requireNonNull(map.get(name));
    }

    public byte getByte(String name) {
        return (Byte) Objects.requireNonNull(map.get(name));
    }

    public byte[] getBytes(String name) {
        return (byte[]) Objects.requireNonNull(map.get(name));
    }

    public int[] getInts(String name) {
        return (int[]) Objects.requireNonNull(map.get(name));
    }

    public long[] getLongs(String name) {
        return (long[]) Objects.requireNonNull(map.get(name));
    }

    public NBT getCompound(String name) {
        return (NBT) Objects.requireNonNull(map.get(name));
    }

    public NBT[] getNBTs(String name) {
        NBT[] nbts = (NBT[]) Objects.requireNonNull(map.get(name));
        return Arrays.copyOf(nbts, nbts.length);
    }

    public double[] getDoubles(String name) {
        return (double[]) Objects.requireNonNull(map.get(name));
    }

    public String[] getStrings(String name) {
        return (String[]) Objects.requireNonNull(map.get(name));
    }

    public float[] getFloats(String name) {
        return (float[]) Objects.requireNonNull(map.get(name));
    }

    public short[] getShorts(String name) {
        return (short[]) Objects.requireNonNull(map.get(name));
    }

    public NBT put(String name, byte b) {
        _put(name, b);
        return this;
    }

    public NBT put(String name, int i) {
        _put(name, i);
        return this;
    }

    public NBT put(String name, long l) {
        _put(name, l);
        return this;
    }

    public NBT put(String name, double d) {
        _put(name, d);
        return this;
    }

    public NBT put(String name, float f) {
        _put(name, f);
        return this;
    }

    public NBT put(String name, short s) {
        _put(name, s);
        return this;
    }

    public NBT put(String name, String s) {
        _put(name, s);
        return this;
    }

    public NBT put(String name, int[] is) {
        _put(name, Arrays.copyOf(is, is.length));
        return this;
    }

    public NBT put(String name, byte[] bs) {
        _put(name, Arrays.copyOf(bs, bs.length));
        return this;
    }

    public NBT put(String name, NBT n) {
        if (containsNbt(n)) throw new IllegalArgumentException("Argument NBT is a child of this NBT");
        _put(name, n);
        childNbts.add(n);
        return this;
    }

    public NBT put(String name, long[] ls) {
        _put(name, Arrays.copyOf(ls, ls.length));
        return this;
    }

    public NBT put(String name, short[] ss) {
        _put(name, Arrays.copyOf(ss, ss.length));
        return this;
    }

    public NBT put(String name, String[] ss) {
        for (String str : ss) {
            if (str == null) throw new NullPointerException("null string");
        }
        _put(name, Arrays.copyOf(ss, ss.length));
        return this;
    }

    public NBT put(String name, float[] fs) {
        _put(name, Arrays.copyOf(fs, fs.length));
        return this;
    }

    public NBT put(String name, double[] ds) {
        _put(name, Arrays.copyOf(ds, ds.length));
        return this;
    }

    public NBT put(String name, Object os) {
        if (!os.getClass().isArray()) throw new IllegalArgumentException("Argument is not Array");
        if (!acceptClasses.contains(os.getClass().arrayType()))
            throw new IllegalArgumentException("Unsupported Array Type " + os.getClass().arrayType().getName());
        if (os.getClass().arrayType() == NBT.class) put(name, (NBT) os);
        else if (os.getClass().arrayType() == NBT[].class) put(name, (NBT[]) os);
        else _put(name, os);
        return this;
    }

    public NBT put(String name, NBT[] ns) {
        for (NBT n : ns) {
            if (n == null) throw new NullPointerException("null NBT");
            if (containsNbt(n)) throw new IllegalArgumentException("Argument NBT is a child of this NBT");
        }
        NBT[] copied_ns = Arrays.copyOf(ns, ns.length);
        childNbts.add(new NBT().put("", copied_ns));
        _put(name, copied_ns);
        return this;
    }

    public int size() {
        return map.size();
    }

    public boolean contains(String name) {
        return map.containsKey(name);
    }

    public boolean containsDeeply(String name) {
        if (contains(name)) return true;
        for (Object o : map.values()) {
            if (o instanceof NBT nbt) {
                if (nbt.containsDeeply(name)) return true;
            }
        }
        return false;
    }

    public Set<String> keySet() {
        return map.keySet();
    }
    public byte[] toBytes(){
        ByteArrayOutputStream output=new ByteArrayOutputStream();
        output.writeBytes(new byte[]{0x0a,0,0});
        output.writeBytes(NBTUtils.toBytes(this));
        return output.toByteArray();
    }

    private void _put(String name, Object obj) {
        map.put(name, obj);
    }

    private boolean containsNbt(NBT nbt) {
        for (NBT n : childNbts) {
            if (n == nbt || n.containsNbt(nbt)) return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NBT nbt)) return false;

        return map.equals(nbt.map);
    }

    @Override
    public int hashCode() {
        return map.hashCode();
    }

    public static NBT from(ByteBuffer buffer) {
        buffer.position(buffer.position() + 3);
        return _from(buffer);
    }

    private static NBT _from(ByteBuffer buffer) {
        NBT nbt = new NBT();
        while (true) {
            switch (buffer.get()) {
                case Constants.TAG_END:
                    return nbt;
                case Constants.TAG_BYTE:
                    nbt.put(NBTUtils.readString(buffer), buffer.get());
                    break;
                case Constants.TAG_SHORT:
                    nbt.put(NBTUtils.readString(buffer), buffer.getShort());
                    break;
                case Constants.TAG_INT:
                    nbt.put(NBTUtils.readString(buffer), buffer.getInt());
                    break;
                case Constants.TAG_LONG:
                    nbt.put(NBTUtils.readString(buffer), buffer.getLong());
                    break;
                case Constants.TAG_FLOAT:
                    nbt.put(NBTUtils.readString(buffer), buffer.getFloat());
                    break;
                case Constants.TAG_DOUBLE:
                    nbt.put(NBTUtils.readString(buffer), buffer.getDouble());
                    break;
                case Constants.TAG_STRING:
                    nbt.put(NBTUtils.readString(buffer), NBTUtils.readString(buffer));
                    break;
                case Constants.TAG_COMPOUND:
                    nbt.put(NBTUtils.readString(buffer), NBT._from(buffer));
                    break;
                case Constants.TAG_BYTE_ARRAY:
                    nbt._put(NBTUtils.readString(buffer), NBT.getArray(Constants.TAG_BYTE, buffer));
                    break;
                case Constants.TAG_INT_ARRAY:
                    nbt._put(NBTUtils.readString(buffer), NBT.getArray(Constants.TAG_INT, buffer));
                    break;
                case Constants.TAG_LONG_ARRAY:
                    nbt._put(NBTUtils.readString(buffer), NBT.getArray(Constants.TAG_LONG, buffer));
                    break;
                case Constants.TAG_LIST:
                    nbt._put(NBTUtils.readString(buffer), NBT.getArray(buffer.get(), buffer));
            }
        }
    }

    private static Object get(int id, ByteBuffer buffer) {
        if (id == 10) return NBT._from(buffer);
        return switch (buffer.get()) {
            case Constants.TAG_END -> null;
            case Constants.TAG_BYTE -> buffer.get();
            case Constants.TAG_SHORT -> buffer.getShort();
            case Constants.TAG_INT -> buffer.getInt();
            case Constants.TAG_LONG -> buffer.getLong();
            case Constants.TAG_FLOAT -> buffer.getFloat();
            case Constants.TAG_DOUBLE -> buffer.getDouble();
            case Constants.TAG_STRING -> NBTUtils.readString(buffer);
            case Constants.TAG_COMPOUND -> NBT._from(buffer);
            case Constants.TAG_BYTE_ARRAY -> NBT.getArray(Constants.TAG_BYTE, buffer);
            case Constants.TAG_INT_ARRAY -> NBT.getArray(Constants.TAG_INT, buffer);
            case Constants.TAG_LONG_ARRAY -> NBT.getArray(Constants.TAG_LONG, buffer);
            case Constants.TAG_LIST -> NBT.getArray(buffer.get(), buffer);
            default -> throw new IllegalArgumentException("Invalid Type Id " + id);
        };
    }

    private static Object getArray(int id, ByteBuffer buffer) {
        if (id <= 0) {
            Object[] arr = new Object[buffer.getInt()];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = get(buffer.get(), buffer);
            }
            return arr;
        } else {
            Object array=null;
            int length=buffer.getInt();
            switch (id){
                case Constants.TAG_BYTE -> {
                    byte[] arr=new byte[length];
                    for (int i = 0; i < arr.length; i++) arr[i] = (Byte)get(id,buffer);
                    array=arr;
                }
                case Constants.TAG_SHORT -> {
                    short[] arr=new short[length];
                    for (int i = 0; i < arr.length; i++) arr[i] = (Short)get(id,buffer);
                    array=arr;
                }
                case Constants.TAG_INT -> {
                    int[] arr=new int[length];
                    for (int i = 0; i < arr.length; i++) arr[i] = (Integer) get(id,buffer);
                    array=arr;
                }
                case Constants.TAG_LONG -> {
                    long[] arr=new long[length];
                    for (int i = 0; i < arr.length; i++) arr[i] = (Long)get(id,buffer);
                    array=arr;
                }
                case Constants.TAG_STRING -> {
                    String[] arr=new String[length];
                    for (int i = 0; i < arr.length; i++) arr[i] = (String) get(id,buffer);
                    array=arr;
                }
                case Constants.TAG_FLOAT -> {
                    float[] arr=new float[length];
                    for (int i = 0; i < arr.length; i++) arr[i] = (Float)get(id,buffer);
                    array=arr;
                }
                case Constants.TAG_DOUBLE -> {
                    double[] arr=new double[length];
                    for (int i = 0; i < arr.length; i++) arr[i] = (Double) get(id,buffer);
                    array=arr;
                }
                case Constants.TAG_COMPOUND -> {
                    NBT[] arr=new NBT[length];
                    for (int i = 0; i < arr.length; i++) arr[i] = (NBT) get(id,buffer);
                    array=arr;
                }
                default -> throw new RuntimeException("invalid id "+id);
            }
            return array;
        }
    }

    static final class Constants {
        public static final int TAG_END = 0;
        public static final int TAG_BYTE = 1;
        public static final int TAG_SHORT = 2;
        public static final int TAG_INT = 3;
        public static final int TAG_LONG = 4;
        public static final int TAG_FLOAT = 5;
        public static final int TAG_DOUBLE = 6;
        public static final int TAG_BYTE_ARRAY = 7;
        public static final int TAG_STRING = 8;
        public static final int TAG_LIST = 9;
        public static final int TAG_COMPOUND = 10;
        public static final int TAG_INT_ARRAY = 11;
        public static final int TAG_LONG_ARRAY = 12;
    }
}
