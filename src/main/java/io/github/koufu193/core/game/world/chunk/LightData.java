package io.github.koufu193.core.game.world.chunk;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.stream.IntStream;

public record LightData(int chunkX,int chunkZ,BitSet skyLightMask, BitSet blockLightMask, BitSet emptySkyLightMask,
                        BitSet emptyBlockLightMask,
                        List<byte[]> skyLightsArray, List<byte[]> blockLightsArray) {

    public static LightData from(@NotNull Chunk chunk) {
        int size = chunk.sectionSize() + 2;
        BitSet skyMask = new BitSet(size);
        BitSet blockMask = new BitSet(size);
        BitSet emptySkyMask = new BitSet(size);
        BitSet emptyBlockMask = new BitSet(size);
        ArrayList<byte[]> skyLights = new ArrayList<>();
        ArrayList<byte[]> blockLights = new ArrayList<>();
        int binIndex = 0;
        for (ChunkSection section: chunk.sections()) {
            byte[] skylight=section.getSkyLights();
            byte[] blocklight= section.getBlockLights();
            if (hasNonZeroData(skylight)) {
                skyMask.set(binIndex);
                skyLights.add(skylight);
            } else {
                emptySkyMask.set(binIndex);
            }
            if (hasNonZeroData(blocklight)) {
                blockMask.set(binIndex);
                blockLights.add(blocklight);
            } else {
                emptyBlockMask.set(binIndex);
            }
            binIndex++;
        }
        return new LightData(chunk.chunkX(), chunk.chunkZ(),skyMask, blockMask, emptySkyMask, emptyBlockMask, skyLights, blockLights);
    }

    private static boolean hasNonZeroData(byte[] bytes) {
        return IntStream.range(0, bytes.length).map(i -> bytes[i]).anyMatch(b -> b != 0);
    }

}
