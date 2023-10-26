package io.github.koufu193.network.packets.play.channels;

import io.github.koufu193.core.game.data.Identifier;

import java.nio.ByteBuffer;

public interface IPluginChannel {
    Identifier channel();
    byte[] bytes();
    void load(ByteBuffer buffer);
}
