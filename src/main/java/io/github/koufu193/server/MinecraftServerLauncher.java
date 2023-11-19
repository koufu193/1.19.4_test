package io.github.koufu193.server;

import io.github.koufu193.network.handlers.HandshakeHandler;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public final class MinecraftServerLauncher {
    private AsynchronousServerSocketChannel socketChannel;
    MinecraftServer server=new MinecraftServer(new File("./server"));
    public void start() throws IOException {
        if(socketChannel!=null) throw new IllegalStateException("server has already initialized");
        socketChannel=AsynchronousServerSocketChannel.open().bind(new InetSocketAddress("127.0.0.1",25565));
        accept(socketChannel);
    }
    private void accept(AsynchronousServerSocketChannel channel){
        if(channel==null) throw new IllegalStateException("server has not initialized");
        channel.accept(null,new CompletionHandler<AsynchronousSocketChannel, Void>() {
            @Override
            public void completed(AsynchronousSocketChannel result, Void attachment) {
                accept(channel);
                try (result) {
                    new HandshakeHandler().handle(result,server);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable exc, Void attachment) {
                exc.printStackTrace();
            }
        });
    }
    public void close() throws IOException {
        if(socketChannel==null) throw new IllegalStateException("server has not initialized");
        socketChannel.close();
        socketChannel=null;
    }

    public static void main(String[] args) {
        MinecraftServerLauncher server=new MinecraftServerLauncher();
        try{
            server.start();
            while (true){}
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
