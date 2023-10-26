package io.github.koufu193.network.data;

import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;

public interface DecodableData<T>{
    T decode(ByteBuffer buffer);
}
