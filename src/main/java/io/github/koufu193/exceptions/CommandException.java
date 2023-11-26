package io.github.koufu193.exceptions;

import io.github.koufu193.core.game.data.component.TextComponent;
import org.jetbrains.annotations.NotNull;

/**
 * コマンド処理時に例外が発生したことを示す例外
 */
public class CommandException extends RuntimeException{
    private final TextComponent message;

    /**
     * @param message エラーメッセージ
     */
    public CommandException(@NotNull TextComponent message){
        super(message.text());
        this.message =message;
    }
    /**
     * @param message エラーメッセージ
     */
    public CommandException(@NotNull String message){
        this(new TextComponent(message));
    }
    public CommandException(){
        this(TextComponent.EMPTY);
    }

    /**
     * マイクラで表示する際に使われるメッセージ
     * @return エラーメッセージ
     */
    public TextComponent messageComponent(){
        return this.message;
    }
}
