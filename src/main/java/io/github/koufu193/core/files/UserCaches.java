package io.github.koufu193.core.files;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.*;

public final class UserCaches {
    private final File cache;
    private final HashMap<UUID,UserCache> caches=new HashMap<>();
    private static final Gson gson= new GsonBuilder().setDateFormat(format()).create();
    private UserCaches(File cache){
        this.cache=cache;
        if(!this.cache.exists()) return;
        try(BufferedReader reader=new BufferedReader(new FileReader(this.cache))) {
            List<UserCache> caches=gson.fromJson(reader,TypeToken.getArray(UserCache.class).getType());
            for(UserCache userCache:caches){
                if(this.caches.containsKey(userCache.uuid())) throw new RuntimeException("the same UUID found "+userCache.uuid);
                this.add(userCache);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void write() throws IOException{
        try(BufferedWriter writer=new BufferedWriter(new FileWriter(this.cache))){
            writer.write(gson.toJson(this.caches.values()));
        }
    }
    public void add(String name,UUID uuid,  Date expiresOn){
        this.add(new UserCache(name,uuid,expiresOn));
    }
    public void add(UserCache cache){
        this.caches.put(cache.uuid(),cache);
    }
    public boolean contains(UUID uuid){
        return this.caches.containsKey(uuid);
    }

    public UserCache getCache(UUID uuid) {
        return this.caches.get(uuid);
    }

    public static UserCaches fromFolder(File folder){
        return fromFile(new File(folder,"usercache.json"));
    }
    public static UserCaches fromFile(File file){
        return new UserCaches(file);
    }
    private static String format() {
        return  "yyyy-MM-dd HH:mm:ss Z";
    }
    public record UserCache(String name,UUID uuid, Date expiresOn){}
}
