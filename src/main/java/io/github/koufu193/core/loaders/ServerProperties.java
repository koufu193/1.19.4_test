package io.github.koufu193.core.loaders;

import io.github.koufu193.core.game.entities.Player;

import java.io.*;
import java.util.Objects;
import java.util.Properties;

public class ServerProperties {
    private boolean debug=false;
    private boolean hardcore=false;
    private int networkCompressionThreshold=256;
    private Player.GameMode defaultGameMode= Player.GameMode.Survival;
    private int maxPlayers=20;
    private File levelFolder;
    public boolean debug() {
        return debug;
    }

    public boolean hardcore() {
        return hardcore;
    }

    public int networkCompressionThreshold() {
        return networkCompressionThreshold;
    }

    public Player.GameMode defaultGameMode() {
        return defaultGameMode;
    }

    public int maxPlayers() {
        return maxPlayers;
    }
    public File levelFolder(){
        return levelFolder;
    }


    public static ServerProperties fromFile(File file) {
        ServerProperties properties=new ServerProperties();
        if(!file.exists()){
            try(BufferedOutputStream writer=new BufferedOutputStream(new FileOutputStream(file));InputStream defaultServerProperties=properties.getClass().getClassLoader().getResourceAsStream("server.properties")){
                writer.write(Objects.requireNonNull(defaultServerProperties).readAllBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(file))) {
                Properties properties1=new Properties();
                properties1.load(input);

                properties.debug= Boolean.parseBoolean((String) properties1.getOrDefault("debug","false"));
                properties.hardcore= Boolean.parseBoolean((String) properties1.getOrDefault("hardcore","false"));
                properties.networkCompressionThreshold= Integer.parseInt((String) properties1.getOrDefault("network-compression-threshold","256"));
                properties.defaultGameMode= Player.GameMode.fromString((String) properties1.getOrDefault("gamemode","survival"));
                properties.maxPlayers= Integer.parseInt((String)  properties1.getOrDefault("max-players","20"));
                properties.levelFolder = new File(file.getParent(),(String) properties1.getOrDefault("level-name","world"));
                if(properties.maxPlayers<0) throw new RuntimeException("max-players must be 0 at least");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return properties;
    }
    public static  ServerProperties fromFolder(File folder){
        return fromFile(new File(folder,"server.properties"));
    }
}
