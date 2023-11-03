package io.github.koufu193.core.game.data.component;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class ChatColor{
    private static final Map<Character,ChatColor> BY_CHAR=new HashMap<>();
    private static final Map<String,ChatColor> BY_NAME=new HashMap<>();
    private static final String PREFIX="§";
    public static final ChatColor BLACK = new ChatColor( '0', "black" );
    public static final ChatColor DARK_BLUE = new ChatColor( '1', "dark_blue" );
    public static final ChatColor DARK_GREEN = new ChatColor( '2', "dark_green" );
    public static final ChatColor DARK_AQUA = new ChatColor( '3', "dark_aqua" );
    public static final ChatColor DARK_RED = new ChatColor( '4', "dark_red" );
    public static final ChatColor DARK_PURPLE = new ChatColor( '5', "dark_purple" );
    public static final ChatColor GOLD = new ChatColor( '6', "gold" );
    public static final ChatColor GRAY = new ChatColor( '7', "gray" );
    public static final ChatColor DARK_GRAY = new ChatColor( '8', "dark_gray");
    public static final ChatColor BLUE = new ChatColor( '9', "blue" );
    public static final ChatColor GREEN = new ChatColor( 'a', "green" );
    public static final ChatColor AQUA = new ChatColor( 'b', "aqua" );
    public static final ChatColor RED = new ChatColor( 'c', "red");
    public static final ChatColor LIGHT_PURPLE = new ChatColor( 'd', "light_purple");
    public static final ChatColor YELLOW = new ChatColor( 'e', "yellow" );
    public static final ChatColor WHITE = new ChatColor( 'f', "white" );
    public static final ChatColor MAGIC = new ChatColor( 'k', "obfuscated" );
    public static final ChatColor BOLD = new ChatColor( 'l', "bold" );
    public static final ChatColor STRIKETHROUGH = new ChatColor( 'm', "strikethrough" );
    public static final ChatColor UNDERLINE = new ChatColor( 'n', "underline" );
    public static final ChatColor ITALIC = new ChatColor( 'o', "italic" );
    public static final ChatColor RESET = new ChatColor( 'r', "reset" );
    private final String code;
    private final String name;
    public ChatColor(@NotNull String code){
        this(code,null);
    }
    public ChatColor(int rgb){
        this(rgb,null);
    }
    public ChatColor(int rgb,String name){
        if(rgb<0||0xffffff<rgb) throw new IllegalArgumentException("Too big or negative");
        this.code="#"+Integer.toHexString(rgb);
        this.name=name;
    }
    public String name() {
        return name;
    }
    public ChatColor(@NotNull String code, String name){
        this.code=code;
        this.name=name;
    }
    private ChatColor(char c,String name){
        this(PREFIX+c,name);
        BY_CHAR.put(c,this);
        BY_NAME.put(name,this);
    }
    public String toColorString(){
        return Objects.requireNonNullElse(name,code);
    }
    @Override
    public String toString() {
        return code;
    }
    public static ChatColor byChar(char c){
        return BY_CHAR.get(c);
    }
    public static ChatColor byString(@NotNull String name){
        ChatColor color=BY_NAME.get(name);
        if(color!=null) return color;
        if(!name.matches("^#[\\da-fA-F]{6}$")) return null;
        return new ChatColor(Integer.parseInt(name.substring(1),16));
    }
}
