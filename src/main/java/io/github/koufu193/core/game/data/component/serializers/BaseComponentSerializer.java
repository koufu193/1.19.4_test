package io.github.koufu193.core.game.data.component.serializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import io.github.koufu193.core.game.data.component.BaseComponent;
import io.github.koufu193.core.game.data.component.ChatColor;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public abstract class BaseComponentSerializer {
    protected void deserialize(JsonObject object, BaseComponent.Builder<?,?> builder, JsonDeserializationContext context){
        builder
                .bold(getOrNull(object,"bold"))
                .underline(getOrNull(object,"underline"))
                .italic(getOrNull(object,"italic"))
                .obfuscated(getOrNull(object,"obfuscated"))
                .strikethrough(getOrNull(object,"strikethrough"))
                .color(object.has("color")?ChatColor.byString(object.get("color").getAsString()):null);
    }
    private static Boolean getOrNull(JsonObject object,String name){
        return object.has(name)?object.get(name).getAsBoolean():null;
    }
    protected void serialize(JsonObject object, BaseComponent<?,?> component, JsonSerializationContext context){
        if(!component.colorDefault())object.addProperty("color",component.color().toColorString());
        if(!component.boldDefault()) object.addProperty("bold",component.bold());
        if(!component.italicDefault()) object.addProperty("italic",component.italic());
        if(!component.obfuscatedDefault()) object.addProperty("obfuscated",component.obfuscated());
        if(!component.strikethroughDefault()) object.addProperty("strikethrough",component.strikethrough());
        if(!component.underlineDefault()) object.addProperty("underline",component.underline());
    }

}
