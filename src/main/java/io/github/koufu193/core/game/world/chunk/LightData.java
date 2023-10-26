package io.github.koufu193.core.game.world.chunk;

import io.github.koufu193.core.game.world.World;
import org.jglrxavpok.hephaistos.mca.ChunkColumn;
import org.jglrxavpok.hephaistos.mca.ChunkSection;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.stream.IntStream;

public record LightData(int chunkX,int chunkZ,BitSet skyLightMask, BitSet blockLightMask, BitSet emptySkyLightMask,
                        BitSet emptyBlockLightMask,
                        List<byte[]> skyLightsArray, List<byte[]> blockLightsArray) {
    public static LightData from(Chunk chunk) {
        return from(chunk.column());
    }

    public static LightData from(ChunkColumn column) {
        int size = column.getSections().size() + 2;
        BitSet skyMask = new BitSet(size);
        BitSet blockMask = new BitSet(size);
        BitSet emptySkyMask = new BitSet(size);
        BitSet emptyBlockMask = new BitSet(size);
        ArrayList<byte[]> skyLights = new ArrayList<>();
        ArrayList<byte[]> blockLights = new ArrayList<>();
        int binIndex = 0;
        for (int chunkY = World.MIN_Y / 16; chunkY <= World.MAX_Y / 16; chunkY++) {
            ChunkSection section = column.getSection((byte) chunkY);
            if (hasNonZeroData(section.getSkyLights())) {
                skyMask.set(binIndex);
                skyLights.add(section.getSkyLights());
            } else {
                emptySkyMask.set(binIndex);
            }
            if (hasNonZeroData(section.getBlockLights())) {
                blockMask.set(binIndex);
                blockLights.add(section.getBlockLights());
            } else {
                emptyBlockMask.set(binIndex);
            }
            binIndex++;
        }
        return new LightData(column.getX(),column.getZ(),skyMask, blockMask, emptySkyMask, emptyBlockMask, skyLights, blockLights);
    }

    private static boolean hasNonZeroData(byte[] bytes) {
        return IntStream.range(0, bytes.length).map(i -> bytes[i]).anyMatch(b -> b != 0);
    }

}
