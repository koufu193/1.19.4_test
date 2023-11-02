package io.github.koufu193.generator;

import org.bukkit.Material;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Generator {
    public static void main(String[] args) {
        save(new MaterialCodeGenerator().generate());
    }
    private static void save(@NotNull JavaClassSource source){
        File f=new File(source.getName()+".java");
        if(!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try(BufferedWriter writer=new BufferedWriter(new FileWriter(f))){
            writer.write(source.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
