package io.github.koufu193.core.game.commands.ids;

import io.github.koufu193.core.game.data.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class V1194ParserIds implements ParserIds{
    private static final HashMap<Identifier,Integer> ids=new HashMap<>(){{
        put(new Identifier("brigadier:string"),5);
    }};
    private static final V1194ParserIds INSTANCE=new V1194ParserIds();
    private V1194ParserIds(){}
    @Override
    public int getId(@NotNull Identifier id) {
        return ids.get(id);
    }
    public static ParserIds instance(){
        return INSTANCE;
    }
}
