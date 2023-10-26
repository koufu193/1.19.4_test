package io.github.koufu193.network.handlers;

import io.github.koufu193.server.MinecraftServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public interface IHandler {
    void handle(AsynchronousSocketChannel channel, MinecraftServer server) throws IOException, ExecutionException, InterruptedException, TimeoutException;
}
