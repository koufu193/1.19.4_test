package io.github.koufu193.network.packets.play.channels;

import io.github.koufu193.core.game.data.Identifier;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

public final class PluginChannels {
    private static final Map<Identifier, Supplier<IPluginChannel>> channels=new HashMap<>(){{
        put(new Identifier("minecraft:brand"),BrandChannel::new);
        put(new Identifier("minecraft:register"),RegisterChannel::new);
    }};
    public static IPluginChannel get(Identifier identifier){

        return channels.getOrDefault(identifier,()->new IPluginChannel() {
            @Override
            public Identifier channel() {
                return identifier;
            }

            @Override
            public byte[] bytes() {
                return new byte[0];
            }

            @Override
            public void load(ByteBuffer buffer) {

            }
        }).get();//Objects.requireNonNull(channels.get(identifier),"Invalid Identifier "+identifier).get();
    }
}
