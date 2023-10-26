package io.github.koufu193.core.game.data.component.serializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import io.github.koufu193.core.game.data.component.BaseComponent;

public abstract class BaseComponentSerializer {
    protected void deserialize(JsonObject object, BaseComponent<?,?> component, JsonDeserializationContext context){

    }
    protected void serialize(JsonObject object, BaseComponent<?,?> component, JsonSerializationContext context){

    }

}
