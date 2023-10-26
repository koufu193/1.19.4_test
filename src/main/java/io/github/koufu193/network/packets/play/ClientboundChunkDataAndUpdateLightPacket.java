package io.github.koufu193.network.packets.play;

import io.github.koufu193.core.game.world.World;
import io.github.koufu193.core.game.world.chunk.Chunk;
import io.github.koufu193.core.game.world.chunk.LightData;
import io.github.koufu193.core.game.world.chunk.palette.DirectPalette;
import io.github.koufu193.core.game.world.chunk.palette.IndirectPalette;
import io.github.koufu193.core.game.world.chunk.palette.SingleValuePalette;
import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import org.jetbrains.annotations.NotNull;
import org.jglrxavpok.hephaistos.mca.*;
import org.jglrxavpok.hephaistos.nbt.NBT;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.*;

public class ClientboundChunkDataAndUpdateLightPacket extends AbstractPacket {
    public ClientboundChunkDataAndUpdateLightPacket(@NotNull Chunk chunk){
        fields(chunk);
    }
    ClientboundChunkDataAndUpdateLightPacket(){
        fields((Object) null);
    }
    @Override
    public PacketFormat format() {
        return PacketFormat.of(
                new DataTypes.DataType<Chunk>() {
                    @Override
                    public byte[] encode(Chunk chunk) {
                        ByteArrayOutputStream output = new ByteArrayOutputStream(4096 * 16);
                        DataOutputStream data = new DataOutputStream(output);
                        ChunkColumn column = chunk.column();
                        //List<BlockEntityData> blockEntities=new ArrayList<>(128);
                        try {
                            data.writeInt(chunk.chunkX());
                            data.writeInt(chunk.chunkZ());
                            data.write(DataTypes.NBT.encode(NBT.Compound(root -> {
                                root.setLongArray("MOTION_BLOCKING", column.getMotionBlockingHeightMap().compact());
                            })));
                            writeDataStructure(column, data);
                            data.write(DataTypes.VarInt.encode(0));
                            //light
                            data.write((byte) 0);
                            data.write(DataTypes.Light.encode(LightData.from(chunk)));
                        } catch (IOException ignored) {
                        } catch (AnvilException e) {
                            throw new RuntimeException(e);
                        }
                        return output.toByteArray();
                    }

                    @Override
                    @Deprecated
                    public int size(Chunk value) {
                        return 0;
                    }

                    @Override
                    public Chunk decode(ByteBuffer buffer) {
                        return null;
                    }
                }
        );
    }

    @Override
    public int packetId() {
        return 0x24;
    }

    private static void writeDataStructure(ChunkColumn column, DataOutputStream output) throws IOException, AnvilException {
        ByteArrayOutputStream sectionOutput = new ByteArrayOutputStream();
        for (int j = World.MIN_Y/16; j<=World.MAX_Y/16; j++) {
            ChunkSection section = column.getSection((byte) j);
            BlockState[] states = getAllStates(section);
            int nonAirBlockCount = countNonAirBlocks(states);
            String[] identifiers = getUsedIdentifiers(states).toArray(String[]::new);
            sectionOutput.writeBytes(DataTypes.Short.encode((short)nonAirBlockCount));
            int bits= countBitsToStore(identifiers.length);
            if(identifiers.length==1) new SingleValuePalette(identifiers[0]).write(sectionOutput);
            else if(bits<=8) new IndirectPalette(states).write(sectionOutput);
            else new DirectPalette(states).write(sectionOutput);
            //biomes
            new SingleValuePalette((int)(Math.random()*60)).write(sectionOutput);
        }
        byte[] data = sectionOutput.toByteArray();
        output.write(DataTypes.VarInt.encode(data.length));
        output.write(data);
    }

    private static BlockState getState(BlockState[] states, int x, int y, int z) {
        return states[(y << 8) | (z << 4) | (x)];
    }

    private static int countBitsToStore(int number) {
        int bits = 1;
        while (true) {
            number >>>= 1;
            if (number != 0) bits++;
            else return bits;
        }
    }

    private static int countNonAirBlocks(BlockState[] states) {
        return (int) Arrays.stream(states).filter(state -> !state.component1().contains("air")).count();
    }

    private static BlockState[] getAllStates(ChunkSection section) throws AnvilException {
        BlockState[] states = new BlockState[16 * 16 * 16];
        if (section.getEmpty()) {
            Arrays.fill(states, BlockState.AIR);
            return states;
        }
        for (int y = 0; y < 16; y++) {
            for (int z = 0; z < 16; z++) {
                for (int x = 0; x< 16; x++) {
                    states[(y<<8)|(z<<4)|x] = section.get(x, y, z);
                }
            }
        }
        return states;
    }

    private static Set<String> getUsedIdentifiers(BlockState[] states) {
        Set<String> identifiers = new HashSet<>();
        Arrays.stream(states).forEach(state -> identifiers.add(state.component1()));
        return identifiers;
    }

    private record BlockEntityData(byte packedXZ, short y, int type, NBT data) {
        public BlockEntityData(int x, int y, int z, int type, NBT data) {
            this((byte) (((x & 15) << 4) | (z & 15)), (short) y, type, data);
        }
    }
}
