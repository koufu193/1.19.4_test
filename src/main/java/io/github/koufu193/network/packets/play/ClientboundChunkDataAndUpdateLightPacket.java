package io.github.koufu193.network.packets.play;

import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.core.game.data.Material;
import io.github.koufu193.core.game.world.chunk.Chunk;
import io.github.koufu193.core.game.world.chunk.ChunkSection;
import io.github.koufu193.core.game.world.chunk.LightData;
import io.github.koufu193.core.game.world.chunk.block.Block;
import io.github.koufu193.core.game.world.chunk.palette.DirectPalette;
import io.github.koufu193.core.game.world.chunk.palette.IndirectPalette;
import io.github.koufu193.core.game.world.chunk.palette.SingleValuePalette;
import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import io.github.koufu193.util.BitUtils;
import net.minecraft.network.protocol.game.PacketPlayOutBlockAction;
import org.jetbrains.annotations.NotNull;
import org.jglrxavpok.hephaistos.mca.AnvilException;
import org.jglrxavpok.hephaistos.mca.ChunkColumn;
import org.jglrxavpok.hephaistos.nbt.NBT;
import org.jglrxavpok.hephaistos.nbt.NBTCompound;
import org.jglrxavpok.hephaistos.nbt.NBTCompoundLike;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.*;

public class ClientboundChunkDataAndUpdateLightPacket extends AbstractPacket {
    public ClientboundChunkDataAndUpdateLightPacket(@NotNull Chunk chunk){
        fields(chunk);
    }
    public ClientboundChunkDataAndUpdateLightPacket(int chunkX,int chunkZ,@NotNull Chunk chunk){
        fields(chunkX,chunkZ,chunk);
    }
    ClientboundChunkDataAndUpdateLightPacket(){}
    @NotNull
    @Override
    public PacketFormat format() {
        return PacketFormat.of(
                DataTypes.Int,
                DataTypes.Int,
                new DataTypes.DataType<Chunk>() {
                    @Override
                    public byte[] encode(Chunk chunk) {
                        ByteArrayOutputStream output = new ByteArrayOutputStream(4096 * 16);
                        DataOutputStream data = new DataOutputStream(output);
                        //List<BlockEntityData> blockEntities=new ArrayList<>(128);
                        try {
                            data.write(DataTypes.NBT.encode(NBT.Compound(root -> {
                                root.setLongArray("MOTION_BLOCKING", chunk.getMotionBlocking());
                            })));
                            List<BlockEntityData> tileBlocks=writeDataStructure(chunk, data);
                            data.write(DataTypes.VarInt.encode(tileBlocks.size()));
                            //must fix
                            tileBlocks.forEach(block->{
                                output.write(block.packedXZ());
                                output.writeBytes(DataTypes.Short.encode(block.y()));
                                output.writeBytes(DataTypes.VarInt.encode(block.type()));
                                output.writeBytes(DataTypes.NBT.encode(block.data()));
                            });
                            //light
                            data.write((byte) 0);
                            data.write(DataTypes.Light.encode(LightData.from(chunk)));
                        } catch (IOException ignored) {
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

    private static List<BlockEntityData> writeDataStructure(Chunk chunk, DataOutputStream output) throws IOException{
        ByteArrayOutputStream sectionOutput = new ByteArrayOutputStream();
        List<BlockEntityData> tileBlocks=new ArrayList<>();
        for (ChunkSection section:chunk.sections()) {
            Material[] blocks=blocksToMaterials(section.blocks());
            int nonAirBlockCount = countNonAirBlocks(blocks);
            Material[] materials = getUsedMaterials(blocks).toArray(Material[]::new);
            sectionOutput.writeBytes(DataTypes.Short.encode((short)nonAirBlockCount));
            int bits= BitUtils.calculateBitsToStore(materials.length-1);
            if(materials.length==1) new SingleValuePalette(materials[0]).write(sectionOutput);
            else if(bits<=8) new IndirectPalette(blocks).write(sectionOutput);
            else new DirectPalette(blocks).write(sectionOutput);
            //biomes
            new SingleValuePalette((int)(Math.random()*60)).write(sectionOutput);
            tileBlocks.addAll(Arrays.stream(section.blocks()).filter(block->!block.nbt().isEmpty()).map(block -> {
                Location location=block.location();
                return new BlockEntityData((int)location.x(),(int)location.y(),(int)location.z(),block.type().blockId(),block.nbt());
            }).toList());
        }
        byte[] data = sectionOutput.toByteArray();
        output.write(DataTypes.VarInt.encode(data.length));
        output.write(data);
        return tileBlocks;
    }



    private static int countNonAirBlocks(@NotNull Material[] blocks) {
        return (int) Arrays.stream(blocks).filter(Material::isNonAir).count();
    }
    private static Material[] blocksToMaterials(@NotNull Block[] blocks){
        Material[] materials=new Material[blocks.length];
        for(int i=0;i<materials.length;i++){
            materials[i]=blocks[i].type();
        }
        return materials;
    }
    private static Set<Material> getUsedMaterials(@NotNull Material[] blocks) {
        return new HashSet<>(Arrays.asList(blocks));
    }

    private record BlockEntityData(byte packedXZ, short y, int type,@NotNull NBTCompound data) {
        public BlockEntityData(int x, int y, int z, int type,@NotNull NBTCompound data) {
            this((byte) (((x & 15) << 4) | (z & 15)), (short) y, type, data);
        }
    }
}
