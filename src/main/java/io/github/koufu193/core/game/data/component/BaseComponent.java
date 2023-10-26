package io.github.koufu193.core.game.data.component;

import com.google.gson.JsonObject;

public abstract class BaseComponent<T extends BaseComponent<T,Builder>,Builder extends BaseComponent.Builder<T,Builder>> {

    public abstract Builder builder();
    public static abstract class Builder<T extends BaseComponent<T, Builder>,Builder extends BaseComponent.Builder<T,Builder>>{
        public abstract T build();
    }
}
