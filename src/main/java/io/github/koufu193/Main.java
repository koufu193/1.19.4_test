package io.github.koufu193;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import io.github.koufu193.core.game.data.Identifier;
import io.github.koufu193.core.game.data.item.ItemStack;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.util.StringCommandReader;
import net.minecraft.commands.CommandListenerWrapper;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jglrxavpok.hephaistos.mca.*;
import org.jglrxavpok.hephaistos.nbt.CompressedProcesser;
import org.jglrxavpok.hephaistos.nbt.NBTCompound;
import org.jglrxavpok.hephaistos.nbt.NBTException;
import org.jglrxavpok.hephaistos.nbt.NBTReader;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    private static final int ITEMS_LENGTH=36;
    public static void main(String[] args) {
        for(int i=0;i<2;i++){
            for(int j=0;j<9;j++){
                System.out.println((3-i)*9+j);
            }
        }
        int[] d=new int[ITEMS_LENGTH];
        for(int i=0;i<d.length;i++) d[i]=i;
        int[] o=reverseForPacket(d);
        for(int i=0;i<o.length;i++){
            System.out.print(o[i]+",");
            if((i+1)%9==0) System.out.println();
        }
    }
    private static int[] reverseForPacket(@NotNull int[] items){
        if(items.length!=ITEMS_LENGTH) throw new IllegalArgumentException(String.format("items length %d must be %d",items.length,ITEMS_LENGTH));
        int[] result=new int[items.length];
        for(int i=0;i<2;i++){
            for(int j=0;j<9;j++){
                result[i*9+j]=items[(3-i)*9+j];
                result[(3-i)*9+j]=items[i*9+j];
            }
        }
        return result;
    }

}
