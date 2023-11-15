package io.github.koufu193.core.game.data.inventory;

import io.github.koufu193.core.game.data.component.TextComponent;
import io.github.koufu193.core.game.entities.interfaces.IPlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class InventoryView {
    private final Inventory inventory;
    private final Set<IPlayer> viewers=new HashSet<>();
    private TextComponent title;
    public InventoryView(@NotNull Inventory inventory, @NotNull TextComponent title) {
        this.inventory = inventory;
        this.title=title;
    }
    public InventoryView(@NotNull Inventory inventory){
        this(inventory,TextComponent.EMPTY);
    }
    public TextComponent title(){
        return this.title;
    }
    public Inventory inventory(){
        return this.inventory;
    }
    public Set<IPlayer> viewers(){
        return Collections.unmodifiableSet(this.viewers);
    }
    public void remove(@NotNull IPlayer player){
        this.viewers.remove(player);
    }
}
