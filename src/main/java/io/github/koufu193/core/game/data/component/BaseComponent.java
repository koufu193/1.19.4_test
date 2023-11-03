package io.github.koufu193.core.game.data.component;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class BaseComponent<T extends BaseComponent<T, Builder>, Builder extends BaseComponent.Builder<T, Builder>> {
    @Nullable
    protected ChatColor color;
    @Nullable
    protected Boolean bold;
    @Nullable
    protected Boolean italic;
    @Nullable
    protected Boolean underline;
    @Nullable
    protected Boolean strikethrough;
    @Nullable
    protected Boolean obfuscated;
    @Nullable
    protected BaseComponent<?,?> extra;
    public BaseComponent(@Nullable ChatColor color, @Nullable Boolean bold, @Nullable Boolean italic, @Nullable Boolean underline, @Nullable Boolean strikethrough, @Nullable Boolean obfuscated, @Nullable BaseComponent<?,?> extra){
        this.color=color;
        this.bold=bold;
        this.italic=italic;
        this.underline=underline;
        this.strikethrough=strikethrough;
        this.obfuscated=obfuscated;
        this.extra=extra;
    }
    @NotNull
    public ChatColor color(){
        if(color==null) return ChatColor.BLACK;
        return color;
    }
    public boolean bold(){
        if(bold==null) return false;
        return bold;
    }
    public boolean italic(){
        if(italic==null) return false;
        return italic;
    }
    public boolean underline(){
        if(underline==null) return false;
        return underline;
    }
    public boolean strikethrough(){
        if(strikethrough==null) return false;
        return strikethrough;
    }
    public boolean obfuscated(){
        if(obfuscated==null) return false;
        return obfuscated;
    }
    public boolean boldDefault(){
        return this.bold==null;
    }
    public boolean obfuscatedDefault(){
        return this.obfuscated==null;
    }
    public boolean strikethroughDefault(){
        return this.strikethrough==null;
    }
    public boolean underlineDefault(){
        return this.underline==null;
    }
    public boolean italicDefault(){
        return this.italic==null;
    }
    public boolean colorDefault(){
        return this.color==null;
    }
    public BaseComponent<?,?> extra(){
        return this.extra;
    }

    public abstract Builder builder();

    public static abstract class Builder<T extends BaseComponent<T, Builder>, Builder extends BaseComponent.Builder<T, Builder>> {
        @Nullable
        protected ChatColor color;
        @Nullable
        protected Boolean bold;
        @Nullable
        protected Boolean italic;
        @Nullable
        protected Boolean underline;
        @Nullable
        protected Boolean strikethrough;
        @Nullable
        protected Boolean obfuscated;
        public Builder(@Nullable ChatColor color, @Nullable Boolean bold, @Nullable Boolean italic, @Nullable Boolean underline, @Nullable Boolean strikethrough, @Nullable Boolean obfuscated){
            this.color=color;
            this.bold=bold;
            this.italic=italic;
            this.underline=underline;
            this.strikethrough=strikethrough;
            this.obfuscated=obfuscated;
        }

        public abstract T build();

        public Builder color(@Nullable ChatColor color) {
            this.color = color;
            return (Builder) this;
        }

        public Builder bold(@Nullable Boolean bold) {
            this.bold = bold;
            return (Builder) this;
        }

        public Builder italic(@Nullable Boolean italic) {
            this.italic = italic;
            return (Builder) this;
        }

        public Builder obfuscated(@Nullable Boolean obfuscated) {
            this.obfuscated = obfuscated;
            return (Builder) this;
        }

        public Builder underline(@Nullable Boolean underline) {
            this.underline = underline;
            return (Builder) this;
        }

        public Builder strikethrough(@Nullable Boolean strikethrough) {
            this.strikethrough = strikethrough;
            return (Builder) this;
        }

        public Builder resetColor() {
            return color(null);
        }

        public Builder resetBold() {
            return bold(null);
        }

        public Builder resetObfuscated() {
            return obfuscated(null);
        }

        public Builder resetItalic() {
            return italic(null);
        }

        public Builder resetUnderline() {
            return underline(null);
        }
        public Builder resetStrikethrough(){
            return strikethrough(null);
        }
    }
}
