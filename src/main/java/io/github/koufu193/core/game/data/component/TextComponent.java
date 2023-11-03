package io.github.koufu193.core.game.data.component;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import io.github.koufu193.core.game.data.component.serializers.TextComponentSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


@JsonAdapter(TextComponentSerializer.class)
public class TextComponent extends BaseComponent<TextComponent, TextComponent.Builder>{
    public static final TextComponent EMPTY=new TextComponent();
    private String text="";
    public TextComponent(){
        this("");
    }
    public TextComponent(String text){
        this(text,null);
    }
    public TextComponent(@NotNull String text,@Nullable ChatColor color, @Nullable Boolean bold, @Nullable Boolean italic, @Nullable Boolean underline, @Nullable Boolean strikethrough, @Nullable Boolean obfuscated, @Nullable BaseComponent<?, ?> extra) {
        super(color, bold, italic, underline, strikethrough, obfuscated, extra);
        this.text=text;
    }
    public TextComponent(@NotNull String text,@Nullable ChatColor color){
        this(text,color,null,null,null,null,null,null);
    }

    public String text(){
        return text;
    }

    @Override
    public Builder builder() {
        return new Builder(this.text,color,bold,italic,underline,strikethrough,obfuscated);
    }
    public static class Builder extends BaseComponent.Builder<TextComponent,Builder>{
        private String text;

        public Builder(@NotNull String text,@Nullable ChatColor color, @Nullable Boolean bold, @Nullable Boolean italic, @Nullable Boolean underline, @Nullable Boolean strikethrough, @Nullable Boolean obfuscated) {
            super(color, bold, italic, underline, strikethrough, obfuscated);
            this.text(text);
        }

        public Builder text(@NotNull String text){
            this.text=text;
            return this;
        }
        @Override
        public TextComponent build() {
            return new TextComponent(text,color,bold,italic,underline,strikethrough,obfuscated,null);
        }
    }
}
