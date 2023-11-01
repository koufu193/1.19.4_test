package io.github.koufu193;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import io.github.koufu193.util.StringCommandReader;
import net.minecraft.commands.CommandListenerWrapper;
import org.jglrxavpok.hephaistos.mca.*;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        StringCommandReader reader=new StringCommandReader("hello \"hello\"");
        System.out.println(reader.read());
        System.out.println(reader.read());
        System.out.println(reader.read());
    }

}
