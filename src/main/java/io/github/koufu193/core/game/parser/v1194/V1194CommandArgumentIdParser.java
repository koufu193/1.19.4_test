package io.github.koufu193.core.game.parser.v1194;

import io.github.koufu193.core.game.data.Identifier;
import io.github.koufu193.core.game.parser.CommandArgumentIdParser;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class V1194CommandArgumentIdParser implements CommandArgumentIdParser {
    private static final HashMap<Identifier,Integer> ids=new HashMap<>(){{
        put(new Identifier("brigadier:bool"),0);
        put(new Identifier("brigadier:integer"),3);
        put(new Identifier("brigadier:string"),5);
    }};
    private static final V1194CommandArgumentIdParser PARSER =new V1194CommandArgumentIdParser();
    private V1194CommandArgumentIdParser(){}
    @Override
    public int parse(@NotNull Identifier argumentId) {
        return ids.get(argumentId);
    }
    public static CommandArgumentIdParser getParser(){
        return PARSER;
    }
}
