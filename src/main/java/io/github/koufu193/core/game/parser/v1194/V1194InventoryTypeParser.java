package io.github.koufu193.core.game.parser.v1194;

import io.github.koufu193.core.game.data.inventory.InventoryType;
import io.github.koufu193.core.game.parser.InventoryTypeParser;
import org.bukkit.block.Chest;
import org.bukkit.block.Dispenser;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class V1194InventoryTypeParser implements InventoryTypeParser {
    private static final InventoryTypeParser PARSER=new V1194InventoryTypeParser();
    private final Map<InventoryType,Integer> idMap=new HashMap<>(){{
        put(InventoryType.GENERIC_9x1,0);
        put(InventoryType.GENERIC_9x2,1);
        put(InventoryType.GENERIC_9x3,2);
        put(InventoryType.GENERIC_9x4,3);
        put(InventoryType.GENERIC_9x5,4);
        put(InventoryType.GENERIC_9x6,5);
        put(InventoryType.GENERIC_3x3,6);
    }};
    public static InventoryTypeParser getParser(){
        return PARSER;
    }
    @Override
    public int parse(@NotNull InventoryType type) {
        if(type==InventoryType.PLAYER||type==InventoryType.HORSE) throw new IllegalArgumentException(
                String.format("type must not be %s or %s",InventoryType.PLAYER.name(),InventoryType.HORSE.name())
        );
        return idMap.getOrDefault(type,0);
    }
}
