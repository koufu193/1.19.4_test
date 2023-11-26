package io.github.koufu193.util;

import org.checkerframework.checker.units.qual.A;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * マイクラのコマンドをテキストして解釈する際に用いるパーサー
 */
public final class StringCommandReader {
    private final char[] command;
    private int offset=0;
    private int mark=0;
    public StringCommandReader(@NotNull String command){
        this.command=command.toCharArray();
    }

    /**
     * 引数分文字を読むことが可能か調べる
     * @param len 読みたい長さ
     * @return 読むことが可能ならtrue,不可能ならfalse
     */
    public boolean canRead(int len){
        return offset+len<=command.length;
    }

    /**
     * まだ読むことが可能かどうか
     * @return 読むことが可能ならtrue,不可能ならfalse
     */
    public boolean canRead(){
        return this.canRead(1);
    }

    /**
     * 10進数ロングを読む
     * @return 読まれた値
     */
    public long readLong(){
        return Long.parseLong(read());
    }

    /**
     * 10進数数値を読む
     * @return 読まれた値
     */
    public int readInt(){
        return Integer.parseInt(read());
    }

    /**
     * ブール値を読む
     * @return 読まれた値
     */
    public boolean readBool(){
        return Boolean.parseBoolean(read());
    }

    /**
     * 倍精度浮動小数点数を読む
     * @return 読まれた値
     */
    public double readDouble(){
        return Double.parseDouble(read());
    }

    /**
     * 浮動小数点数を読む
     * @return 読まれた値
     */
    public float readFloat(){
        return Float.parseFloat(read());
    }

    /**
     * オフセットをマークされていた位置へ戻す
     * マークされていなかったら初期位置へ戻す
     */
    public void reset(){
        this.offset=this.mark;
    }

    /**
     * 生のコマンドを取得する
     * @return 生のコマンド
     */
    public String rawCommand(){
        return new String(command);
    }

    /**
     * 現在位置をマークする
     */
    public void mark(){
        this.mark=offset;
    }

    /**
     * 現在のオフセットを取得する
     * @return 現在のオフセット
     */
    public int offset(){
        return this.offset;
    }

    /**
     * オフセットを設定する
     * @param offset 設定したいオフセット
     */
    public void offset(int offset){
        if(offset<0||command.length<offset) throw new IllegalArgumentException(String.format("format must be between %d and %d",0,command.length+1));
        this.offset=offset;
    }

    /**
     * 読む
     * @param startOffset 開始位置
     * @param endOffset 終了位置
     * @return 読まれた文字列
     */
    public String read(int startOffset,int endOffset){
        return new String(Arrays.copyOfRange(command,startOffset,endOffset));
    }

    /**
     * 引用符に囲まれていたら引用符の中身,囲まれていなかったら{@link #read()}と同じ動作
     * @return 読まれた文字列(初めと終わりの引用符はない)
     */
    public String readQuotableString(){
        int size=countQuotableCharsToRead();
        String str=read(offset,offset+size);
        offset+=str.length()+1;
        if(!str.startsWith("\"")) return str;
        return str.replaceAll("(^\")|(\"$)","").replaceAll("\\\\([\"\\\\])","\\1");
    }

    /**
     * 次の空白まで読む
     * @return 読まれた文字列
     */
    public String read(){
        if(!canRead()) throw new ArrayIndexOutOfBoundsException("End of String");
        String string=read(offset,offset+countCharsToRead());
        offset+=string.length()+1;
        return string;
    }

    /**
     * まだ読まれていない部分をすべて読む
     * @return 読まれた部分
     */
    public String readRemaining(){
        String string=read(offset,command.length);
        offset=command.length;
        return string;
    }

    /**
     * このコマンドの文字列としての長さを取得する
     * @return 長さ
     */
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
    private int countQuotableCharsToRead(){
        if(command[offset]!='"') return countCharsToRead();
        int len=1;
        for(;offset+len<command.length;len++){
            if(command[offset+len]=='\\'){
                if(command[offset+len+1]=='"'||command[offset+len+1]=='\\'){
                    offset++;
                    continue;
                }
                else throw new IllegalStateException("Invalid char:"+command[offset+len+1]);
            }
            if(command[offset+len]!='"') continue;
            if(command.length<=offset+len+1||Character.isWhitespace(command[offset+len+1])) return len+1;
        }
        return len+1;
    }
}
