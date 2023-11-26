package io.github.koufu193.core.properties;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Properties {
    public static final Properties EMPTY=new Properties().immutable();
    private final Map<String, Property> properties=new HashMap<>();
    public Properties(){}
    public Properties(@NotNull Property... properties){addAll(properties);}

    public Map<String, Property> asUnmodifiableMap() {
        return Collections.unmodifiableMap(properties);
    }
    public boolean contains(String key){
        return properties.containsKey(key);
    }
    public void add(Property property){
        properties.put(property.name(),property);
    }
    public void remove(String key){
        properties.remove(key);
    }
    public void addAll(Property[] properties){
        for(Property property:properties) this.add(property);
    }
    public final Properties immutable(){
        return new Properties(){
            @Override
            public void add(Property property) {}

            @Override
            public void remove(String key) {}

            @Override
            public void addAll(Property[] properties) {}
        };
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Properties that)) return false;
        return properties.equals(that.properties);
    }

    @Override
    public int hashCode() {
        return properties.hashCode();
    }
}
