package io.github.koufu193.core.game.data.component;

import org.jetbrains.annotations.NotNull;

public class TextComponent extends BaseComponent<TextComponent, TextComponent.Builder>{
    public static final TextComponent EMPTY=new TextComponent();
    private String text="";
    public String text(){
        return text;
    }
    public TextComponent(){
        this("");
    }
    public TextComponent(@NotNull String text){
        this.text=text;
    }

    @Override
    public Builder builder() {
        return new Builder(this.text);
    }
    public static class Builder extends BaseComponent.Builder<TextComponent,Builder>{
        private String text;
        private Builder(@NotNull String text){
            this.text(text);
        }
        public Builder text(@NotNull String text){
            this.text=text;
            return this;
        }
        @Override
        public TextComponent build() {
            return new TextComponent(text);
        }
    }
}
