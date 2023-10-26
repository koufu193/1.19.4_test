package io.github.koufu193.core.game.world.material;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import io.github.koufu193.core.game.data.Identifier;
import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.core.game.world.chunk.block.Block;
import io.github.koufu193.core.game.world.chunk.block.converters.DefaultBlockConverter;
import io.github.koufu193.core.game.world.chunk.block.converters.IBlockConverter;
import io.github.koufu193.core.game.world.chunk.block.interfaces.IBlock;
import org.jglrxavpok.hephaistos.mca.BlockState;

import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Material<block extends IBlock>{
    private static final Map<String,Material<?>> idMap=new HashMap<>();
    private static final Map<String,String> EMPTY= Collections.emptyMap();
    private static final IBlockConverter<Block> DEFAULT_CONVERTER=new DefaultBlockConverter();
    //<editor-fold desc="Materials" defaultstate="collapsed">
    public static final Material<Block> AIR=new Material<>(BlockState.AIR);
    public static final Material<Block> DIRT=new Material<>(new BlockState("minecraft:dirt",EMPTY));
    public static final Material<Block> STONE= new Material<>(new BlockState("minecraft:stone", EMPTY));
    //</editor-fold>
    private final Identifier id;
    private final BlockState defaultState;
    private final int maxStack;
    private final IBlockConverter<block> converter;
    private Material(BlockState defaultState){
        this(defaultState,64);
    }
    private Material(BlockState defaultState,IBlockConverter<block> converter){
        this(defaultState,64,converter);
    }
    private Material(BlockState defaultState,int maxStack){
        this(defaultState,maxStack, (IBlockConverter<block>) DEFAULT_CONVERTER);
    }
    private Material(BlockState defaultState,int maxStack,IBlockConverter<block> converter){
        this.id=Identifier.from(defaultState.component1());
        this.defaultState=defaultState;
        this.maxStack=maxStack;
        this.converter=converter;
        idMap.put(id.toString(),this);
    }

    public BlockState defaultState() {
        return defaultState;
    }

    public Identifier id() {
        return id;
    }

    public int maxStack() {
        return maxStack;
    }
    public block convert(Location location, BlockState state){
        return this.converter.convert(location,state,this);
    }
    public int blockId(){
        return fromIdentifier(id());
    }
    public static Material<?> fromId(String id){
        if(!id.contains(":")) id="minecraft:"+id;
        return Objects.requireNonNullElse(idMap.get(id),STONE);
    }
    public static Material<?> fromId(Identifier id){
        return idMap.get(id.toString());
    }
    public static Integer fromIdentifier(Identifier id){
        return fromKey(id.toString());
    }
    private static HashMap<String,Integer> ids;
    static {
        try(BufferedReader reader=new BufferedReader(new InputStreamReader(Objects.requireNonNull(Material.class.getClassLoader().getResourceAsStream("blocks.json"),"blocks.json not found")))) {
            JsonArray arr=new Gson().fromJson(reader, JsonArray.class);
            ids=new HashMap<>(arr.size());
            for(JsonElement element:arr){
                ids.put("minecraft:"+element.getAsJsonObject().get("name").getAsString(),element.getAsJsonObject().get("defaultState").getAsInt());
            }
        }catch (IOException ignored){}
    }
    public static Integer fromKey(String key){
        if(!key.contains(":")) key="minecraft:"+key;
        return ids.get(key);

    }

}
