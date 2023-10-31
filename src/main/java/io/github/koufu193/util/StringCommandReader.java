package io.github.koufu193.util;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public final class StringCommandReader {
    private final char[] command;
    private int offset=0;
    private int mark=0;
    public StringCommandReader(@NotNull String command){
        this.command=command.toCharArray();
    }
    public boolean canRead(int len){
        return offset+len<=command.length;
    }
    public boolean canRead(){
        return this.canRead(1);
    }
    public long readLong(){
        return Long.parseLong(read());
    }
    public int readInt(){
        return Integer.parseInt(read());
    }
    public boolean readBool(){
        return Boolean.parseBoolean(read());
    }
    public double readDouble(){
        return Double.parseDouble(read());
    }
    public float readFloat(){
        return Float.parseFloat(read());
    }
    public void reset(){
        this.offset=this.mark;
    }
    public String rawCommand(){
        return new String(command);
    }
    public void mark(){
        this.mark=offset;
    }
    public int offset(){
        return this.offset;
    }
    public void offset(int offset){
        if(offset<0||command.length<offset) throw new IllegalArgumentException(String.format("format must be between %d and %d",0,command.length+1));
        this.offset=offset;
    }
    public String read(int startOffset,int endOffset){
        return new String(Arrays.copyOfRange(command,startOffset,endOffset));
    }
    public String readQuotableString(){
        StringBuilder string= new StringBuilder(read());
        if(!string.toString().startsWith("\"")) return string.toString();
        String str=read();
        for(;!str.endsWith("\"");str=read()){
            string.append(" ").append(str);
        }
        return string.append(" ").append(str).toString().replaceAll("(^\")|(\"$)","");
    }
    public String read(){
        if(!canRead()) throw new ArrayIndexOutOfBoundsException("End of String");
        char[] string=new char[countCharsToRead()];
        System.arraycopy(command, offset, string, 0, string.length);
        offset+=string.length+1;
        return new String(string);
    }
    public int size(){
        return this.command.length;
    }
    private int countCharsToRead(){
        int len=0;
        for(;offset+len<command.length;len++){
            if(Character.isWhitespace(command[offset+len])) return len;
        }
        return len;
    }
}
