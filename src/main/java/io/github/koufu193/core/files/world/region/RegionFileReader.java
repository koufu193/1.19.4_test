package io.github.koufu193.core.files.world.region;

import io.github.koufu193.core.game.world.World;
import io.github.koufu193.core.game.world.chunk.Chunk;
import io.github.koufu193.util.FileUtils;
import org.jetbrains.annotations.NotNull;
import org.jglrxavpok.hephaistos.nbt.CompressedProcesser;
import org.jglrxavpok.hephaistos.nbt.NBTCompound;
import org.jglrxavpok.hephaistos.nbt.NBTException;
import org.jglrxavpok.hephaistos.nbt.NBTReader;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;

public final class RegionFileReader implements AutoCloseable {
    private static final byte GZIP_COMPRESSION = 1;
    private static final byte ZLIB_COMPRESSION = 2;
    private static final byte NO_COMPRESSION = 3;
    private static final int selctor4Kib = 4 * 1024;
    private static final int CHUNKS_PER_REGION = 32 * 32;
    private final SeekableByteChannel channel;
    private final OffsetData[] offsets;
    private final int[] lastModifiedTimes;
    private final long size4Kib;

    public RegionFileReader(@NotNull Path path) throws IOException {
        this(createByteChannel(path));
    }

    public RegionFileReader(@NotNull SeekableByteChannel channel) throws IOException {
        this.channel = channel;
        this.size4Kib = channel.size() / selctor4Kib;
        this.offsets = readOffsets(channel);
        this.lastModifiedTimes = readLastModifiedTimes(channel);
    }


    @Override
    public void close() throws IOException {
        if (this.channel.isOpen()) this.channel.close();
    }

    public boolean isOpen() {
        return this.channel.isOpen();
    }

    /**
     * @param chunkX 0~31
     * @param chunkZ 0~31
     * @return 読まれたチャンク
     */
    @NotNull
    public Chunk readChunk(World world, int chunkX, int chunkZ) throws IOException, NBTException {
        OffsetData offset = this.offsets[packXZ(chunkX, chunkZ)];
        if (offset.notGenerated()) return new Chunk(world,chunkX,chunkZ);
        return readChunkFromFile(world, offset);
    }
    @NotNull
    private Chunk readChunkFromFile(@NotNull World world, @NotNull OffsetData offset) throws IOException, NBTException {
        if (offset.offset4Kib() < 0 || size4Kib <= offset.offset4Kib())
            throw new IndexOutOfBoundsException(String.format("offset#offset4Kib must be between %d and %d,actual %d", 0, size4Kib - 1, offset.offset4Kib));
        this.channel.position(offset.offsetBytes());
        return readChunk(world, this.channel);
    }
    @NotNull
    private static Chunk readChunk(@NotNull World world, @NotNull ByteChannel input) throws IOException, NBTException {
        NBTCompound chunkNBT = readChunkNBT(input);
        return new Chunk(world, World.MAX_Y_SECTION,chunkNBT);
    }

    private static NBTCompound readChunkNBT(@NotNull ByteChannel input) throws IOException, NBTException {
        long size = readSize(input);
        CompressedProcesser<?, ?> compressedProcessor = readCompression(input);
        ByteBuffer chunkData = ByteBuffer.allocate((int) (size - 1));
        input.read(chunkData);
        return (NBTCompound) new NBTReader(chunkData.array(), compressedProcessor).read();
    }

    private static OffsetData[] readOffsets(@NotNull ByteChannel input) throws IOException {
        OffsetData[] offsets = new OffsetData[CHUNKS_PER_REGION];
        byte[] data = new byte[4];
        ByteBuffer buffer = ByteBuffer.wrap(data);
        for (int i = 0; i < CHUNKS_PER_REGION; i++) {
            input.read(buffer);
            offsets[i] = new OffsetData(((data[0] & 0xff) << 16) | ((data[1] & 0xff) << 8) | (data[2] & 0xff), data[3]);
            buffer.position(0);
        }
        return offsets;
    }

    private static int[] readLastModifiedTimes(@NotNull ByteChannel input) throws IOException {
        int[] lastModifiedTimes = new int[CHUNKS_PER_REGION];
        byte[] data = new byte[4];
        ByteBuffer buffer = ByteBuffer.wrap(data);
        for (int i = 0; i < CHUNKS_PER_REGION; i++) {
            input.read(buffer);
            lastModifiedTimes[i] = (data[0] << 24) | (data[1] << 16) | (data[2] << 8) | (data[3] & 0xff);
        }
        return lastModifiedTimes;
    }

    private static long readSize(@NotNull ByteChannel input) throws IOException {
        byte[] size = new byte[4];
        ByteBuffer sizeBuffer = ByteBuffer.wrap(size);
        input.read(sizeBuffer);
        return ((long) (size[0] & 0xff) << 24) | ((size[1] & 0xff) << 16) | ((size[2] & 0xff) << 8) | (size[3] & 0xff);
    }

    private static CompressedProcesser<?, ?> readCompression(@NotNull ByteChannel input) throws IOException {
        byte[] compression = new byte[1];
        ByteBuffer compressionBuffer = ByteBuffer.wrap(compression);
        input.read(compressionBuffer);
        return switch (compression[0]) {
            case GZIP_COMPRESSION -> CompressedProcesser.GZIP;
            case ZLIB_COMPRESSION -> CompressedProcesser.ZLIB;
            case NO_COMPRESSION -> CompressedProcesser.NONE;
            default -> throw new RuntimeException(String.format("Invalid compression id %d", compression[0]));
        };
    }

    private static int packXZ(int chunkX, int chunkZ) {
        checkValidXZ(chunkX, chunkZ);
        return (chunkZ << 5) | (chunkX);
    }

    private static void checkValidXZ(int chunkX, int chunkZ) {
        if (chunkX < 0 || 31 < chunkX)
            throw new IllegalArgumentException(String.format("chunkX must be between %d and %d", 0, 31));
        if (chunkZ < 0 || 31 < chunkZ)
            throw new IllegalArgumentException(String.format("chunkZ must be between %d and %d", 0, 31));
    }

    private static SeekableByteChannel createByteChannel(@NotNull Path path) throws IOException {
        FileUtils.throwIfNotExists(path);
        FileUtils.throwIfNotAFile(path);
        return Files.newByteChannel(path);
    }

    private record OffsetData(int offset4Kib, int size4Kib) {
        public int offsetBytes() {
            return this.offset4Kib * selctor4Kib;
        }

        public boolean notGenerated() {
            return this.offset4Kib == 0 && this.size4Kib == 0;
        }
    }
}
