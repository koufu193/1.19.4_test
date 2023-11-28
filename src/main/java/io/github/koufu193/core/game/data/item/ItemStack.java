package io.github.koufu193.core.game.data.item;

import io.github.koufu193.core.game.data.Material;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ItemStack implements Cloneable{
    public static final ItemStack AIR=new ItemStack(Material.AIR,1,ItemMeta.defaultItemMeta(Material.AIR)){
        private static final ItemMeta meta=ItemMeta.defaultItemMeta(Material.AIR);
        @Override
        public ItemStack amount(int amount) {
            return this;
        }

        @Override
        public ItemStack itemMeta(@NotNull ItemMeta meta) {return this;}

        @Override
        public ItemStack type(@NotNull Material material) {return this;}

        @Override
        public int amount() {
            return 1;
        }

        @Override
        public Material type() {
            return Material.AIR;
        }

        @Override
        public ItemMeta itemMeta() {
            return meta;
        }

        @Override
        public ItemStack clone() {
            return this;
        }
    };
    private Material material;
    private int amount;
    private ItemMeta meta;
    public ItemStack(@NotNull Material material, int amount, @NotNull ItemMeta meta){
        type(material);
        amount(amount);
        itemMeta(meta);
    }
    public int amount(){
        return this.amount;
    }
    public ItemStack amount(int amount){
        if(amount<=0||material.maxStack()<amount) throw new IllegalArgumentException(String.format("amount must be between %d and %d",1,material.maxStack()));
        this.amount=amount;
        return this;
    }
    public Material type(){
        return this.material;
    }
    public ItemStack type(@NotNull Material material){
        if(!material.isItem()) throw new IllegalArgumentException(String.format("Material %s is not an item",material.id()));
        if(material.maxStack()<this.amount) this.amount=material.maxStack();
        this.material=material;
        return this;
    }
    public ItemStack itemMeta(@NotNull ItemMeta meta){
        if(!meta.isTypeOf(this.material)) throw new IllegalArgumentException(String.format("ItemMeta %s cannot use %s",meta.getClass().getSimpleName(),this.material.id()));
        this.meta=meta;
        return this;
    }
    public ItemMeta itemMeta(){
        return this.meta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemStack stack)) return false;

        if (amount != stack.amount) return false;
        if (material!=stack.material) return false;
        return Objects.equals(meta, stack.meta);
    }

    @Override
    public int hashCode() {
        int result = material != null ? material.hashCode() : 0;
        result = 31 * result + amount;
        result = 31 * result + (meta != null ? meta.hashCode() : 0);
        return result;
    }

    @Override
    public ItemStack clone() {
        return new ItemStack(material,amount,meta);
    }
}
