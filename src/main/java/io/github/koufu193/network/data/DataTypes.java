package io.github.koufu193.network.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.github.koufu193.core.game.data.Angle;
import io.github.koufu193.core.game.data.Identifier;
import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.core.game.data.Material;
import io.github.koufu193.core.game.data.component.TextComponent;
import io.github.koufu193.core.game.data.item.ItemMeta;
import io.github.koufu193.core.game.data.item.ItemStack;
import io.github.koufu193.core.game.world.chunk.LightData;
import io.github.koufu193.core.properties.Properties;
import io.github.koufu193.core.properties.Property;
import io.github.koufu193.network.packets.play.channels.PluginChannelRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jglrxavpok.hephaistos.nbt.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Predicate;

public final class DataTypes {
    public static final DataType<Integer> VarInt = new DataType<>() {
        private static final int SEGMENT_BITS = 0x7f;
        private static final int CONTINUE_BIT = 0x80;

        @Override
        public Integer decode(ByteBuffer buffer) {
            int value = 0;
            int position = 0;
            byte currentByte;
            while (true) {
                currentByte = buffer.get();
                value |= (currentByte & SEGMENT_BITS) << position;
                if ((currentByte & CONTINUE_BIT) == 0) break;
                position += 7;
                if (32 <= position) throw new RuntimeException("VarInt is too big");
            }
            return value;
        }

        @Override
        public byte[] encode(Integer value) {
            int int_value = value;
            ByteArrayOutputStream output = new ByteArrayOutputStream(this.size(value));
            while (true) {
                if ((int_value & ~SEGMENT_BITS) == 0) {
                    output.write(int_value);
                    break;
                }
                output.write((int_value & SEGMENT_BITS) | CONTINUE_BIT);
                int_value >>>= 7;
            }
            return output.toByteArray();
        }

        @Override
        public int size(Integer value) {
            int int_value = value;
            int size = 1;
            while (true) {
                if ((int_value & ~SEGMENT_BITS) == 0) return size;
                int_value >>>= 7;
                size++;
            }
        }
    };
    public static final DataType<String> String = new DataType<>() {
        @Override
        public java.lang.String decode(ByteBuffer buffer) {
            int length = DataTypes.VarInt.decode(buffer);
            byte[] bytes = new byte[length];
            buffer.get(bytes);
            return new String(bytes);
        }

        @Override
        public byte[] encode(java.lang.String value) {
            byte[] data = value.getBytes(StandardCharsets.UTF_8);
            byte[] length = DataTypes.VarInt.encode(data.length);
            return ByteBuffer.allocate(length.length + data.length).put(length).put(data).array();
        }

        @Override
        public int size(java.lang.String value) {
            byte[] data = value.getBytes(StandardCharsets.UTF_8);
            return DataTypes.VarInt.size(data.length) + data.length;
        }
    };
    public static final DataType<Byte> Byte = new DataType<>() {
        @Override
        public java.lang.Byte decode(ByteBuffer buffer) {
            return buffer.get();
        }

        @Override
        public byte[] encode(java.lang.Byte value) {
            return new byte[]{value};
        }

        @Override
        public int size(java.lang.Byte value) {
            return java.lang.Byte.BYTES;
        }
    };
    public static final DataType<Short> Short = new DataType<>() {
        @Override
        public java.lang.Short decode(ByteBuffer buffer) {
            return buffer.getShort();
        }

        @Override
        public byte[] encode(java.lang.Short value) {
            return ByteBuffer.allocate(java.lang.Short.BYTES).putShort(value).array();
        }

        @Override
        public int size(java.lang.Short value) {
            return java.lang.Short.BYTES;
        }
    };
    public static final DataType<Long> Long = new DataType<>() {
        @Override
        public java.lang.Long decode(ByteBuffer buffer) {
            return buffer.getLong();
        }

        @Override
        public byte[] encode(java.lang.Long value) {
            return ByteBuffer.allocate(java.lang.Long.BYTES).putLong(value).array();
        }

        @Override
        public int size(java.lang.Long value) {
            return java.lang.Long.BYTES;
        }
    };
    public static final DataType<UUID> UUID = new DataType<>() {
        @Override
        public java.util.UUID decode(ByteBuffer buffer) {
            return new UUID(buffer.getLong(), buffer.getLong());
        }

        @Override
        public byte[] encode(java.util.UUID value) {
            ByteBuffer buffer = ByteBuffer.wrap(new byte[16]);
            buffer.putLong(value.getMostSignificantBits());
            buffer.putLong(value.getLeastSignificantBits());
            return buffer.array();
        }

        @Override
        public int size(java.util.UUID value) {
            return java.lang.Long.BYTES * 2;
        }
    };
    public static final DataType<NBT> NBT = new DataType<>() {
        @Override
        public org.jglrxavpok.hephaistos.nbt.NBT decode(ByteBuffer buffer) {
            try (NBTReader reader = new NBTReader(new InputStream() {
                @Override
                public int read() {
                    if (!buffer.hasRemaining()) return -1;
                    return java.lang.Byte.toUnsignedInt(buffer.get());
                }

                @Override
                public int read(@NotNull byte[] b, int off, int len) {
                    len = Math.min(len, buffer.remaining());
                    buffer.get(b, off, len);
                    return len;
                }
            }, CompressedProcesser.NONE)) {
                return reader.read();
            } catch (IOException | NBTException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public byte[] encode(NBT value) {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            try (NBTWriter writer = new NBTWriter(output, CompressedProcesser.NONE)) {
                writer.writeNamed("", value);
                return output.toByteArray();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public int size(org.jglrxavpok.hephaistos.nbt.NBT value) {
            try {
                return value.toByteArray().length + 3;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    };
    public static final DataType<Integer> Int = new DataType<>() {
        @Override
        public Integer decode(ByteBuffer buffer) {
            return buffer.getInt();
        }

        @Override
        public byte[] encode(Integer value) {
            return ByteBuffer.allocate(Integer.BYTES).putInt(value).array();
        }

        @Override
        public int size(Integer value) {
            return Integer.BYTES;
        }
    };
    public static final DataType<Boolean> Bool = new DataType<>() {
        @Override
        public Boolean decode(ByteBuffer buffer) {
            return buffer.get() == 1;
        }

        @Override
        public byte[] encode(Boolean value) {
            return new byte[]{(byte) (value ? 1 : 0)};
        }

        @Override
        public int size(Boolean value) {
            return java.lang.Byte.BYTES;
        }
    };
    public static final DataType<Identifier> Identifier = new DataType<>() {
        @Override
        public Identifier decode(ByteBuffer buffer) {

            return new Identifier(DataTypes.String.decode(buffer));
        }

        @Override
        public byte[] encode(Identifier value) {
            return DataTypes.String.encode(value.toString());
        }

        @Override
        public int size(Identifier value) {
            return DataTypes.String.size(value.toString());
        }
    };
    public static final DataType<Location> Position = new DataType<>() {
        @Override
        public Location decode(ByteBuffer buffer) {
            return new Location(null,buffer.getLong());
        }

        @Override
        public byte[] encode(Location value) {
            return DataTypes.Long.encode(value.toLong());
        }

        @Override
        public int size(Location value) {
            return java.lang.Long.BYTES;
        }
    };
    public static final DataType<Angle> Angle = new DataType<>() {
        @Override
        public io.github.koufu193.core.game.data.Angle decode(ByteBuffer buffer) {
            return new Angle(buffer.get());
        }

        @Override
        public byte[] encode(io.github.koufu193.core.game.data.Angle value) {
            return new byte[]{value.angle()};
        }

        @Override
        public int size(io.github.koufu193.core.game.data.Angle value) {
            return java.lang.Byte.BYTES;
        }
    };
    public static final DataType<Float> Float = new DataType<>() {
        @Override
        public java.lang.Float decode(ByteBuffer buffer) {
            return buffer.getFloat();
        }

        @Override
        public byte[] encode(java.lang.Float value) {
            return ByteBuffer.allocate(java.lang.Float.BYTES).putFloat(value).array();
        }

        @Override
        public int size(java.lang.Float value) {
            return java.lang.Float.BYTES;
        }
    };
    public static final DataType<Double> Double = new DataType<>() {
        @Override
        public java.lang.Double decode(ByteBuffer buffer) {
            return buffer.getDouble();
        }

        @Override
        public byte[] encode(java.lang.Double value) {
            return ByteBuffer.allocate(java.lang.Double.BYTES).putDouble(value).array();
        }

        @Override
        public int size(java.lang.Double value) {
            return java.lang.Double.BYTES;
        }
    };
    public static final DataType<BitSet> BitSet = new DataType<>() {
        @Override
        public java.util.BitSet decode(ByteBuffer buffer) {
            long[] arr = new long[VarInt.decode(buffer)];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = buffer.getLong();
            }
            return java.util.BitSet.valueOf(arr);
        }

        @Override
        public byte[] encode(java.util.BitSet value) {
            long[] arr = value.toLongArray();
            byte[] len = VarInt.encode(arr.length);
            ByteBuffer buff = ByteBuffer.allocate(len.length + arr.length * java.lang.Long.BYTES).put(len);
            Arrays.stream(arr).forEach(buff::putLong);
            return buff.array();
        }

        @Override
        public int size(java.util.BitSet value) {
            long[] arr = value.toLongArray();
            return DataTypes.VarInt.size(arr.length) + arr.length * java.lang.Long.BYTES;
        }
    };
    public static final DataType<LightData> Light = new DataType<>() {

        @Override
        public byte[] encode(LightData value) {
            List<byte[]> data = new ArrayList<>();
            data.add(DataTypes.BitSet.encode(value.skyLightMask()));
            data.add(DataTypes.BitSet.encode(value.blockLightMask()));
            data.add(DataTypes.BitSet.encode(value.emptySkyLightMask()));
            data.add(DataTypes.BitSet.encode(value.emptyBlockLightMask()));
            data.add(DataTypes.VarInt.encode(value.skyLightsArray().size()));
            value.skyLightsArray().forEach(skyLight -> {
                data.add(DataTypes.VarInt.encode(skyLight.length));
                data.add(skyLight);
            });
            data.add(DataTypes.VarInt.encode(value.blockLightsArray().size()));
            value.blockLightsArray().forEach(blockLight -> {
                data.add(DataTypes.VarInt.encode(blockLight.length));
                data.add(blockLight);
            });
            ByteArrayOutputStream output = new ByteArrayOutputStream(calculateSize(data));
            for (byte[] d : data) {
                output.writeBytes(d);
            }
            return output.toByteArray();
        }

        @Override
        public int size(LightData value) {
            int size = (DataTypes.BitSet.size(value.skyLightMask()) +
                    DataTypes.BitSet.size(value.blockLightMask()) +
                    DataTypes.BitSet.size(value.emptySkyLightMask()) +
                    DataTypes.BitSet.size(value.emptyBlockLightMask()));
            size += DataTypes.VarInt.size(value.skyLightsArray().size());
            for (byte[] array : value.skyLightsArray()) {
                size += (DataTypes.VarInt.size(array.length) + array.length);
            }
            size += DataTypes.VarInt.size(value.blockLightsArray().size());
            for (byte[] array : value.blockLightsArray()) {
                size += (DataTypes.VarInt.size(array.length) + array.length);
            }
            return size;
        }

        private static int calculateSize(List<byte[]> data) {
            int count = 0;
            for (byte[] d : data) {
                count += d.length;
            }
            return count;
        }

        @Override
        public LightData decode(ByteBuffer buffer) {
            java.util.BitSet skyLightMask = DataTypes.BitSet.decode(buffer);
            java.util.BitSet blockLightMask = DataTypes.BitSet.decode(buffer);
            java.util.BitSet emptySkyLightMask = DataTypes.BitSet.decode(buffer);
            java.util.BitSet emptyBlockLightMask = DataTypes.BitSet.decode(buffer);
            int skyLightsArrayLength = DataTypes.VarInt.decode(buffer);
            List<byte[]> skyLightsArray = new ArrayList<>(skyLightsArrayLength);
            for (int i = 0; i < skyLightsArrayLength; i++) {
                byte[] data = new byte[DataTypes.VarInt.decode(buffer)];
                buffer.get(data);
                skyLightsArray.set(i, data);
            }
            int blockLightsArrayLength = DataTypes.VarInt.decode(buffer);
            List<byte[]> blockLightsArray = new ArrayList<>(skyLightsArrayLength);
            for (int i = 0; i < blockLightsArrayLength; i++) {
                byte[] data = new byte[DataTypes.VarInt.decode(buffer)];
                buffer.get(data);
                blockLightsArray.set(i, data);
            }
            return new LightData(0, 0, skyLightMask, blockLightMask, emptySkyLightMask, emptyBlockLightMask, skyLightsArray, blockLightsArray);
        }
    };
    public static final DataType<Long> VarLong = new DataType<>() {
        private static final int SEGMENT_BITS = 0x7F;
        private static final int CONTINUE_BIT = 0x80;

        @Override
        public java.lang.Long decode(ByteBuffer buffer) {
            long value = 0;
            int position = 0;
            byte currentByte;
            while (true) {
                currentByte = buffer.get();
                value |= (long) (currentByte & SEGMENT_BITS) << position;
                if ((currentByte & CONTINUE_BIT) == 0) break;
                position += 7;
                if (position >= 64) throw new RuntimeException("VarLong is too big");
            }
            return value;
        }

        @Override
        public byte[] encode(java.lang.Long value) {
            ByteArrayOutputStream output = new ByteArrayOutputStream(java.lang.Long.BYTES);
            while (true) {
                if ((value & ~((long) SEGMENT_BITS)) == 0) {
                    output.write((byte) (long) value);
                    return output.toByteArray();
                }
                output.write((byte) ((value & SEGMENT_BITS) | CONTINUE_BIT));
                value >>>= 7;
            }
        }

        @Override
        public int size(java.lang.Long value) {
            int size = 1;
            while (true) {
                if ((value & ~((long) SEGMENT_BITS)) == 0) return size;
                value >>>= 7;
                size++;
            }
        }
    };
    public static final DataType<byte[]> ByteArray = new DataType<>() {
        @Override
        public byte[] decode(ByteBuffer buffer) {
            byte[] bytes = new byte[buffer.remaining()];
            buffer.get(bytes);
            return bytes;
        }

        @Override
        public byte[] encode(byte[] value) {
            return value;
        }

        @Override
        public int size(byte[] value) {
            return value.length;
        }
    };
    public static final DataType<io.github.koufu193.network.packets.play.channels.PluginChannel> PluginChannel = new DataType<>() {
        @Override
        public io.github.koufu193.network.packets.play.channels.PluginChannel decode(ByteBuffer buffer) {
            io.github.koufu193.network.packets.play.channels.PluginChannel channel = PluginChannelRegistry.get(DataTypes.Identifier.decode(buffer));
            channel.load(buffer);
            return channel;
        }

        @Override
        public byte[] encode(io.github.koufu193.network.packets.play.channels.PluginChannel value) {
            ByteBuffer buffer = ByteBuffer.allocate(this.size(value));
            buffer.put(DataTypes.Identifier.encode(value.channel()));
            buffer.put(value.bytes());
            return buffer.array();
        }

        @Override
        public int size(io.github.koufu193.network.packets.play.channels.PluginChannel value) {
            return DataTypes.Identifier.size(value.channel()) + value.bytes().length;
        }
    };
    public static final DataType<TextComponent> Chat = new DataType<>() {
        private static final Gson gson = new Gson();
        private static final TypeToken<TextComponent> TYPE_TOKEN = TypeToken.get(TextComponent.class);

        @Override
        public TextComponent decode(ByteBuffer buffer) {
            return gson.fromJson(DataTypes.String.decode(buffer), TYPE_TOKEN);
        }

        @Override
        public byte[] encode(TextComponent value) {
            return DataTypes.String.encode(gson.toJson(value));
        }

        @Override
        public int size(TextComponent value) {
            return DataTypes.String.size(gson.toJson(value));
        }
    };
    public static final DataType<Properties> Properties = new DataTypes.DataType<>() {
        @Override
        public byte[] encode(Properties value) {
            ByteArrayOutputStream output = new ByteArrayOutputStream(this.size(value));
            output.writeBytes(DataTypes.VarInt.encode(value.asUnmodifiableMap().size()));
            for (Property property : value.asUnmodifiableMap().values()) {
                output.writeBytes(DataTypes.String.encode(property.name()));
                output.writeBytes(DataTypes.String.encode(property.value()));
                output.writeBytes(DataTypes.Bool.encode(property.isSigned()));
                if (property.isSigned()) output.writeBytes(DataTypes.String.encode(property.signature()));
            }
            return output.toByteArray();
        }

        @Override
        public int size(Properties value) {
            int size = DataTypes.VarInt.size(value.asUnmodifiableMap().size());
            for (Property property : value.asUnmodifiableMap().values()) {
                size += (DataTypes.String.size(property.name()) + DataTypes.String.size(property.value()) + DataTypes.Bool.size(property.isSigned()));
                if (property.isSigned()) size += DataTypes.String.size(property.signature());
            }
            return size;
        }

        @Override
        public Properties decode(ByteBuffer buffer) {
            Property[] properties = new Property[DataTypes.VarInt.decode(buffer)];
            for (int i = 0; i < properties.length; i++) {
                properties[i] = decodeProperty(buffer);
            }
            return new Properties(properties);
        }

        private Property decodeProperty(ByteBuffer buffer) {
            return new Property(DataTypes.String.decode(buffer), DataTypes.String.decode(buffer), DataTypes.Bool.decode(buffer) ? DataTypes.String.decode(buffer) : null);
        }
    };
    public static final DataType<ItemStack> Item = new DataType<>() {
        @Override
        public ItemStack decode(ByteBuffer buffer) {
            if (!DataTypes.Bool.decode(buffer)) return ItemStack.AIR;
            int itemId = DataTypes.VarInt.decode(buffer);
            if (itemId == -1) return null;
            Material material = Material.fromItemId(itemId);
            int amount = buffer.get();
            NBT nbt = DataTypes.NBT.decode(buffer);
            if (nbt instanceof NBTEnd) nbt = NBTCompound.EMPTY;
            return new ItemStack(material, amount, ItemMeta.defaultItemMeta(material, (NBTCompound) nbt));
        }

        @Override
        public byte[] encode(@Nullable ItemStack value) {
            if (value == null) value = ItemStack.AIR;
            ByteArrayOutputStream output = new ByteArrayOutputStream(size(value));
            output.writeBytes(DataTypes.Bool.encode(value.type() != Material.AIR));
            if (value.type() == Material.AIR) return output.toByteArray();
            output.writeBytes(DataTypes.VarInt.encode(value.type().itemId()));
            output.write(value.amount());
            NBTCompound nbt = value.itemMeta().toNBT();
            if (nbt.isEmpty()) output.write(0);
            else output.writeBytes(DataTypes.NBT.encode(nbt));
            return output.toByteArray();
        }

        @Override
        public int size(@Nullable ItemStack value) {
            if (value == null) value = ItemStack.AIR;
            if (value.type() == Material.AIR) return 1;
            int bytes = 1 + DataTypes.VarInt.size(value.type().itemId());
            bytes += (byte) value.amount();
            NBTCompound nbt = value.itemMeta().toNBT();
            ;
            if (nbt.isEmpty()) bytes += 1;
            else bytes += DataTypes.NBT.size(nbt);
            return bytes;
        }
    };
    public static final DataType<Location> Location = new DataType<>() {
        @Override
        public io.github.koufu193.core.game.data.Location decode(ByteBuffer buffer) {
            return new Location(buffer.getDouble(), buffer.getDouble(), buffer.getDouble(), buffer.getFloat(), buffer.getFloat());
        }

        @Override
        public byte[] encode(io.github.koufu193.core.game.data.Location value) {
            ByteArrayOutputStream output = new ByteArrayOutputStream(this.size(value));
            output.writeBytes(Double.encode(value.x()));
            output.writeBytes(Double.encode(value.y()));
            output.writeBytes(Double.encode(value.z()));
            output.writeBytes(Angle.encode(new Angle(value.yaw())));
            output.writeBytes(Angle.encode(new Angle(value.pitch())));
            return output.toByteArray();
        }

        @Override
        public int size(io.github.koufu193.core.game.data.Location value) {
            return Double.size(value.x()) + Double.size(value.y()) + Double.size(value.z())
                    + Angle.size(new Angle(value.yaw())) + Angle.size(new Angle(value.pitch()));
        }
    };

    public abstract static class DataType<T> implements DecodableData<T>, EncodableData<T> {
        public byte[] encode(T obj, Object[] data) {
            return encode(obj);
        }

        public byte[] encodeObj(Object obj, Object[] data) {
            return encode((T) obj, data);
        }

        public T decode(ByteBuffer buffer, Object[] data) {
            return decode(buffer);
        }

        public byte[] encodeObj(Object obj) {
            return encode((T) obj);
        }

        public int size(T value, Object[] data) {
            return this.size(value);
        }

        public final int sizeObj(Object obj) {
            return this.size((T) obj);
        }

        public final int sizeObj(Object obj, Object[] data) {
            return this.size((T) obj, data);
        }

        public final DataType<T> encodeOptional(Predicate<Object[]> option) {
            DataType<T> self = this;
            return new DataType<T>() {
                @Override
                public T decode(ByteBuffer buffer) {
                    return self.decode(buffer);
                }

                @Override
                public byte[] encode(T value) {
                    return self.encode(value);
                }


                @Override
                public T decode(ByteBuffer buffer, Object[] data) {
                    return self.decode(buffer, data);
                }

                @Override
                public byte[] encode(T obj, Object[] data) {
                    if (option.test(data)) return self.encode(obj, data);
                    return new byte[0];
                }

                @Override
                public int size(T value, Object[] data) {
                    return option.test(data) ? 0 : self.size(value, data);
                }

                @Override
                public int size(T value) {
                    return self.size(value);
                }
            };
        }

        public final DataType<T> encodeIfNonnull() {
            DataType<T> self = this;
            return new DataType<T>() {
                @Override
                public T decode(ByteBuffer buffer) {
                    return self.decode(buffer);
                }

                @Override
                public byte[] encode(T value) {
                    if (value == null) return new byte[0];
                    return self.encode(value);
                }

                @Override
                public T decode(ByteBuffer buffer, Object[] data) {
                    return self.decode(buffer, data);
                }

                @Override
                public byte[] encode(T obj, Object[] data) {
                    if (obj == null) return new byte[0];
                    return self.encode(obj, data);
                }

                @Override
                public int size(T value, Object[] data) {
                    return value != null ? self.size(value, data) : 0;
                }

                @Override
                public int size(T value) {
                    return value != null ? self.size(value) : 0;
                }
            };
        }

        public final DataType<T> decodeOptional(Predicate<Object[]> option) {
            DataType<T> self = this;
            return new DataType<T>() {
                @Override
                public T decode(ByteBuffer buffer) {
                    return self.decode(buffer);
                }

                @Override
                public byte[] encode(T value) {
                    return self.encode(value);
                }

                @Override
                public T decode(ByteBuffer buffer, Object[] data) {
                    if (option.test(data)) return self.decode(buffer, data);
                    return null;
                }

                @Override
                public byte[] encode(T obj, Object[] data) {
                    return self.encode(obj, data);
                }

                @Override
                public int size(T value) {
                    return self.size(value);
                }

                @Override
                public int size(T value, Object[] data) {
                    return self.size(value, data);
                }
            };
        }

        public final DataType<T[]> array() {
            DataType<T> self = this;
            return new DataType<>() {
                @Override
                public T[] decode(ByteBuffer buffer) {
                    T[] arr = (T[]) Array.newInstance(getClazz((T) null), DataTypes.VarInt.decode(buffer));
                    for (int i = 0; i < arr.length; i++) {
                        arr[i] = self.decode(buffer);
                    }
                    return arr;
                }

                @Override
                public byte[] encode(T[] values) {
                    ByteArrayOutputStream output = new ByteArrayOutputStream(this.size(values));
                    output.writeBytes(DataTypes.VarInt.encode(this.calculateLength(values)));
                    for (T value : values) {
                        output.writeBytes(self.encode(value));
                    }
                    return output.toByteArray();
                }

                @Override
                public T[] decode(ByteBuffer buffer, Object[] data) {
                    T[] arr = (T[]) Array.newInstance(getClazz((T) null), DataTypes.VarInt.decode(buffer));
                    for (int i = 0; i < arr.length; i++) {
                        arr[i] = self.decode(buffer, data);
                    }
                    return arr;
                }

                private Class<?> getClazz(T... v) {
                    return v.getClass().getComponentType();
                }

                @Override
                public byte[] encode(T[] values, Object[] data) {
                    ByteArrayOutputStream output = new ByteArrayOutputStream(this.size(values, data));
                    output.writeBytes(DataTypes.VarInt.encode(this.calculateLength(values, data)));
                    for (T value : values) {
                        if (self.size(value, data) != 0) output.writeBytes(self.encode(value, data));
                    }
                    return output.toByteArray();
                }

                @Override
                public int size(T[] values, Object[] data) {
                    int length = 0;
                    int dataSize = 0;
                    for (T value : values) {
                        int valueSize = self.size(value, data);
                        if (valueSize != 0) {
                            length++;
                            dataSize += valueSize;
                        }
                    }
                    return DataTypes.VarInt.size(length) + dataSize;
                }

                @Override
                public int size(T[] values) {
                    int length = 0;
                    int dataSize = 0;
                    for (T value : values) {
                        int valueSize = self.size(value);
                        if (valueSize != 0) {
                            length++;
                            dataSize += valueSize;
                        }
                    }
                    return DataTypes.VarInt.size(length) + dataSize;
                }

                private int calculateLength(T[] values) {
                    int length = 0;
                    for (T value : values) {
                        if (self.size(value) != 0) length++;
                    }
                    return length;
                }

                private int calculateLength(T[] values, Object[] obj) {
                    int length = 0;
                    for (T value : values) {
                        if (self.size(value, obj) != 0) length++;
                    }
                    return length;
                }
            };
        }
    }
}