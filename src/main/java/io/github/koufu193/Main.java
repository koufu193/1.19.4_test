package io.github.koufu193;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import io.github.koufu193.core.game.data.Identifier;
import io.github.koufu193.util.StringCommandReader;
import net.minecraft.commands.CommandListenerWrapper;
import org.bukkit.Material;
import org.jglrxavpok.hephaistos.mca.*;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        System.out.println(new Identifier("bedrock"));
    }

}
