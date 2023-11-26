package io.github.koufu193;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import io.github.koufu193.core.game.data.Identifier;
import io.github.koufu193.core.game.data.item.ItemStack;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.util.StringCommandReader;
import net.kyori.adventure.text.TextComponent;
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
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        System.out.println((16<<8)|(16<<4)|16);
    }

}
