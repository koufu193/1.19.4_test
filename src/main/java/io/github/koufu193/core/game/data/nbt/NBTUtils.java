package io.github.koufu193.core.game.data.nbt;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;

final class NBTUtils {
    private static final HashMap<Class<?>, Integer> ids = new HashMap<>() {{
        put(Byte.class, NBT.Constants.TAG_BYTE);
        put(Short.class, NBT.Constants.TAG_SHORT);
        put(Integer.class, NBT.Constants.TAG_INT);
        put(Long.class, NBT.Constants.TAG_LONG);
        put(Float.class, NBT.Constants.TAG_FLOAT);
        put(Double.class, NBT.Constants.TAG_DOUBLE);
        put(String.class, NBT.Constants.TAG_STRING);
        put(byte[].class, NBT.Constants.TAG_BYTE_ARRAY);
        put(int[].class, NBT.Constants.TAG_INT_ARRAY);
        put(long[].class, NBT.Constants.TAG_LONG_ARRAY);
        put(NBT.class, NBT.Constants.TAG_COMPOUND);
    }};

    public static String readString(ByteBuffer buffer) {
        byte[] arr = new byte[Short.toUnsignedInt(buffer.getShort())];
        if (arr.length == 0) return "";
        buffer.get(arr);
        return new String(arr);
    }

    public static byte[] toBytes(String... ss) {
        int totalLen = 0;
        for (String s : ss) {
            if (s == null) throw new NullPointerException("null string");
            totalLen += s.length();
        }
        ByteBuffer buffer = ByteBuffer.allocate(2 * ss.length + totalLen);
        for (String s : ss) buffer.putShort((short) s.length()).put(s.getBytes(StandardCharsets.UTF_8));
        return buffer.array();
    }

    public static byte[] toBytes(byte... b) {
        return Arrays.copyOf(b, b.length);
    }

    public static byte[] toBytes(int... is) {
        ByteBuffer buffer = ByteBuffer.allocate(is.length * Integer.BYTES);
        for (int i : is) buffer.putInt(i);
        return buffer.array();
    }

    public static byte[] toBytes(long... ls) {
        ByteBuffer buffer = ByteBuffer.allocate(ls.length * Long.BYTES);
        for (long l : ls) buffer.putLong(l);
        return buffer.array();
    }

    public static byte[] toBytes(short... ss) {
        ByteBuffer buffer = ByteBuffer.allocate(ss.length * Short.BYTES);
        for (short s : ss) buffer.putShort(s);
        return buffer.array();
    }

    public static byte[] toBytes(float... fs) {
        ByteBuffer buffer = ByteBuffer.allocate(fs.length * Float.BYTES);
        for (float f : fs) buffer.putFloat(f);
        return buffer.array();
    }

    public static byte[] toBytes(double... ds) {
        ByteBuffer buffer = ByteBuffer.allocate(ds.length * Double.BYTES);
        for (double d : ds) buffer.putDouble(d);
        return buffer.array();
    }

    public static byte[] writeArray(Object array) {
        ByteArrayOutputStream output=new ByteArrayOutputStream();
        if(array.getClass().getComponentType()==Object.class){
            Object[] objs=(Object[])array;
            output.writeBytes(new byte[]{0});
            output.writeBytes(toBytes(objs.length));
            for(Object obj:objs){
                output.writeBytes(new byte[]{(byte) getId(obj)});
                output.writeBytes(_toBytes(obj));
            }
        }else{
            int arr_id = getId(array.getClass().getComponentType());
            output.writeBytes(new byte[]{(byte) arr_id});
            output.writeBytes(NBTUtils.toBytes(Array.getLength(array)));
            switch (arr_id){
                case NBT.Constants.TAG_INT -> output.writeBytes(toBytes((int[])array));
                case NBT.Constants.TAG_BYTE -> output.writeBytes(toBytes((byte[])array));
                case NBT.Constants.TAG_LONG -> output.writeBytes(toBytes((long[])array));
                case NBT.Constants.TAG_SHORT -> output.writeBytes(toBytes((short[])array));
                case NBT.Constants.TAG_STRING -> output.writeBytes(toBytes((String[])array));
                case NBT.Constants.TAG_DOUBLE -> output.writeBytes(toBytes((double[])array));
                case NBT.Constants.TAG_FLOAT -> output.writeBytes(toBytes((float[])array));
                case NBT.Constants.TAG_COMPOUND -> output.writeBytes(toBytes((NBT[])array));
                default -> throw new RuntimeException("invalid id "+arr_id);
            }
        }
        return output.toByteArray();
    }

    public static byte[] toBytes(NBT... nbts) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        for (NBT nbt : nbts) {
            if (nbt == null) throw new NullPointerException("null nbt");
            for (String key : nbt.keySet()) {
                Object obj=nbt.get(key);
                output.writeBytes(new byte[]{(byte) getId(obj)});
                output.writeBytes(NBTUtils.toBytes(key));
                output.writeBytes(_toBytes(obj));
            }
            output.writeBytes(new byte[]{0});
        }
        return output.toByteArray();
    }

    private static byte[] _toBytes(Object obj) {
        return switch (getId(obj)) {
            case NBT.Constants.TAG_BYTE -> toBytes((Byte) obj);
            case NBT.Constants.TAG_SHORT -> toBytes((Short) obj);
            case NBT.Constants.TAG_INT -> toBytes((Integer) obj);
            case NBT.Constants.TAG_LONG -> toBytes((Long) obj);
            case NBT.Constants.TAG_FLOAT -> toBytes((Float) obj);
            case NBT.Constants.TAG_DOUBLE -> toBytes((Double) obj);
            case NBT.Constants.TAG_STRING -> toBytes((String) obj);
            case NBT.Constants.TAG_INT_ARRAY -> addLength(toBytes((int[]) obj),((int[])obj).length);
            case NBT.Constants.TAG_LONG_ARRAY -> addLength(toBytes((long[]) obj),((long[])obj).length);
            case NBT.Constants.TAG_BYTE_ARRAY -> addLength(toBytes((byte[]) obj),((byte[])obj).length);
            case NBT.Constants.TAG_COMPOUND -> toBytes((NBT) obj);
            case NBT.Constants.TAG_LIST -> writeArray(obj);
            default -> throw new IllegalArgumentException("undefined id " + getId(obj));
        };
    }
    private static byte[] addLength(byte[] bytes,int len){
        return ByteBuffer.allocate(bytes.length+Integer.BYTES).putInt(len).put(bytes).array();
    }

    private static int getId(Object obj) {
        return NBTUtils.getId(obj.getClass());
    }

    private static int getId(Class<?> clazz) {
        Integer i = ids.get(clazz);
        if (i != null) return i;
        if (!clazz.isArray()) throw new IllegalArgumentException("undefined type " + clazz.getName());
        return NBT.Constants.TAG_LIST;
    }
}
