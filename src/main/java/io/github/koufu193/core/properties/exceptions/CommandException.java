package io.github.koufu193.core.properties.exceptions;

import io.github.koufu193.core.game.data.component.TextComponent;
import org.jetbrains.annotations.NotNull;

public class CommandException extends RuntimeException{
    private final TextComponent message;
    public CommandException(@NotNull TextComponent message){
        super(message.text());
        this.message =message;
    }
    public CommandException(@NotNull String message){
        this(new TextComponent(message));
    }
    public CommandException(){
        this(TextComponent.EMPTY);
    }
    public TextComponent messageComponent(){
        return this.message;
    }
}
