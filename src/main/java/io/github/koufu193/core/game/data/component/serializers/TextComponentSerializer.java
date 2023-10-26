package io.github.koufu193.core.game.data.component.serializers;

import com.google.gson.*;
import io.github.koufu193.core.game.data.component.TextComponent;

import java.lang.reflect.Type;

public class TextComponentSerializer extends BaseComponentSerializer implements JsonSerializer<TextComponent>, JsonDeserializer<TextComponent> {
    @Override
    public TextComponent deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object=json.getAsJsonObject();
        if(!object.has("text")) throw new JsonParseException("Couldn't find 'text' property");
        TextComponent component=new TextComponent(object.get("text").getAsString());
        super.deserialize(object,component,context);
        return component;
    }

    @Override
    public JsonElement serialize(TextComponent src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object=new JsonObject();
        super.serialize(object,src,context);
        object.addProperty("text",src.text());
        return object;
    }
}
