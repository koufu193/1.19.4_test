package io.github.koufu193.util;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public final class FileUtils {
    public static void throwIfNotAFile(@NotNull Path path){
        if(!Files.isRegularFile(path)){
            throw new IllegalArgumentException(String.format("'%s' is not a file",path.getFileName()));
        }
    }
    public static void throwIfNotADirectory(@NotNull Path path){
        if(!Files.isDirectory(path)){
            throw new IllegalArgumentException(String.format("'%s' is not a directory",path.getFileName()));
        }
    }
    public static void throwIfNotExists(@NotNull Path path){
        if(Files.notExists(path)) {
            throw new IllegalArgumentException(String.format("'%s' doesn't exist",path.getFileName()));
        }
    }
    public static void createDirectories(@NotNull Path path){
        if(Files.exists(path)){
            throwIfNotADirectory(path);
            return;
        }
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * ファイルを作成する
     * @param path 作成するファイルのパス
     * @return 既に作成されていたらfalse 新たに作成したらtrue
     */
    public static boolean createFile(@NotNull Path path){
        if(Files.exists(path)){
            throwIfNotAFile(path);
            return false;
        }
        try{
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static Stream<Path> children(@NotNull Path dir){
        throwIfNotExists(dir);
        throwIfNotADirectory(dir);
        try {
            return Files.list(dir);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static Stream<Path> childDirectories(@NotNull Path dir){
        return children(dir).filter(Files::isDirectory);
    }
    public static Stream<Path> childFiles(@NotNull Path dir){
        return children(dir).filter(Files::isRegularFile);
    }
    public static void write(@NotNull Path path,@NotNull byte[] bytes){
        if(Files.exists(path)) throwIfNotAFile(path);
        try {
            Files.write(path,bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void delete(@NotNull Path path){
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static byte[] readAllBytes(@NotNull Path path){
        throwIfNotExists(path);
        throwIfNotAFile(path);
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
