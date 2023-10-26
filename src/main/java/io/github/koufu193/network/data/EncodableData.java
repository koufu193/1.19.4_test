package io.github.koufu193.network.data;

public interface EncodableData<T>{
    byte[] encode(T value);
    int size(T value);
}
