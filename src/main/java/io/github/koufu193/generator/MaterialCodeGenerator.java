package io.github.koufu193.generator;

import com.google.gson.*;
import io.github.koufu193.core.game.data.Identifier;
import io.github.koufu193.core.game.data.block.BlockMeta;
import io.github.koufu193.core.game.world.chunk.block.Block;
import io.github.koufu193.core.game.world.chunk.block.converters.DefaultBlockMetaConverter;
import io.github.koufu193.core.game.world.chunk.block.converters.IBlockMetaConverter;
import io.github.koufu193.core.game.world.chunk.block.interfaces.IBlock;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jetbrains.annotations.NotNull;
import org.jglrxavpok.hephaistos.mca.BlockState;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Identifier id
int maxStack(0<)
int blockId
boolean isBlock
boolean isItem
 */
public class MaterialCodeGenerator extends CodeGenerator {
    private static final String PACKAGE_NAME = "io.github.koufu193.core.game.data";
    private static final String CLASS_NAME = "Material";

    @Override
    JavaClassSource generate() {
        final JavaClassSource material = Roaster.create(JavaClassSource.class);
        material.setPackage(PACKAGE_NAME).setName(CLASS_NAME);
        addImports(material);
        addStaticFields(material);
        addMaterials(material);
        addFields(material);
        addConstructors(material);
        addMethods(material);
        addStaticMethods(material);
        return material;
    }

    void addFields(JavaClassSource source) {
        source.addField().setName("id").setType(Identifier.class).setPrivate().setFinal(true);
        source.addField().setName("maxStack").setType("int").setPrivate().setFinal(true);
        source.addField().setName("blockId").setType("int").setPrivate().setFinal(true);
        source.addField().setName("isItem").setType("boolean").setPrivate().setFinal(true);
        source.addField().setName("isBlock").setType("boolean").setPrivate().setFinal(true);
        source.addField().setName("itemId").setType("int").setPrivate().setFinal(true);
        source.addField().setName("converter").setType("IBlockMetaConverter<meta>").setPrivate().setFinal(true);
    }

    void addStaticFields(JavaClassSource source) {
        source.addField().setName("DEFAULT_CONVERTER").setType("IBlockMetaConverter<BlockMeta>").setLiteralInitializer("new DefaultBlockMetaConverter();").setPrivate().setStatic(true).setFinal(true);
        source.addField().setName("ids").setType("Map<String,Material<?>>").setLiteralInitializer("new HashMap<>();").setPrivate().setStatic(true).setFinal(true);
    }

    void addConstructors(JavaClassSource source) {
        source.addMethod().setParameters("@NotNull Identifier id,int maxStack,int blockId,int itemId,boolean isBlock,boolean isItem,IBlockMetaConverter<meta> converter").setBody(
                """
                if(maxStack<=0) throw new IllegalArgumentException(String.format("Max Stack(%d) cannot be negative or zero",maxStack));
                this.id=id;
                this.maxStack=maxStack;
                this.blockId=blockId;
                this.isBlock=isBlock;
                this.isItem=isItem;
                this.itemId=itemId;
                this.converter=converter;
                ids.put(id.toString(),this);
                """
        ).setConstructor(true).setPublic();
    }

    void addMethods(JavaClassSource source) {
        source.addMethod().setName("id").setReturnType(Identifier.class).setBody("return this.id;").setPublic();
        source.addMethod().setName("blockId").setReturnType("int").setBody("return this.blockId;").setPublic();
        source.addMethod().setName("maxStack").setReturnType("int").setBody("return this.maxStack;").setPublic();
        source.addMethod().setName("isItem").setReturnType("boolean").setBody("return this.isItem;").setPublic();
        source.addMethod().setName("isBlock").setReturnType("boolean").setBody("return this.isBlock;").setPublic();
        source.addMethod().setName("itemId").setReturnType("int").setBody("return this.itemId;").setPublic();
        source.addMethod().setName("convert").setReturnType("meta").setParameters("@NotNull BlockState state").setBody(
                """
                if(!isBlock()) throw new IllegalStateException("Not a block");
                return this.converter.convert(state);
                """
        ).setPublic();
    }

    void addStaticMethods(JavaClassSource source) {
        source.addMethod().setName("fromId").setReturnType("Material<?>").setParameters("@NotNull Identifier id").setBody("return fromId(id.toString());").setPublic().setStatic(true);
        source.addMethod().setName("fromId").setReturnType("Material<?>").setParameters("@NotNull String id").setBody("""
                if(!id.contains(":")) id="minecraft:"+id;
                return ids.get(id);
                """).setPublic().setStatic(true);
    }

    void addImports(JavaClassSource source) {
        source.addImport(Map.class);
        source.addImport(Block.class);
        source.addImport(IBlock.class);
        source.addImport(HashMap.class);
        source.addImport(NotNull.class);
        source.addImport(BlockMeta.class);
        source.addImport(BlockState.class);
        source.addImport(Identifier.class);
        source.addImport(IBlockMetaConverter.class);
        source.addImport(DefaultBlockMetaConverter.class);
    }
    void addMaterials(JavaClassSource source){
        makeData(readItems(),readBlocks()).forEach(value->addMaterial(source,value));
    }
    void addMaterial(JavaClassSource source,MaterialData data){
        String id=data.block!=null?data.block.get("name").getAsString():data.item.get("name").getAsString();
        boolean isItem=data.item!=null;
        boolean isBlock=data.block!=null;
        int stackSize=isItem?data.item.get("stackSize").getAsInt():data.block.get("stackSize").getAsInt();
        int blockId=isBlock?data.block.get("defaultState").getAsInt():-1;
        int itemId=isItem?data.item.get("id").getAsInt():-1;
        source.addField().setName(id.toUpperCase()).setType("Material<BlockMeta>").setPublic().setStatic(true).setFinal(true).setLiteralInitializer(
                String.format("new Material<>(new Identifier(\"%s\"),%d,%d,%d,%s,%s,%s);",id,stackSize,blockId,itemId,isBlock, isItem,!isBlock?"null":"DEFAULT_CONVERTER")
        );
    }
    List<JsonObject> readItems(){
        return read("items.json").asList().stream().map(JsonElement::getAsJsonObject).toList();
    }
    List<JsonObject> readBlocks(){
        return read("blocks.json").asList().stream().map(JsonElement::getAsJsonObject).toList();
    }
    List<MaterialData> makeData(List<JsonObject> items,List<JsonObject> blocks){
        Map<String,MaterialData> result=new HashMap<>(items.size()+blocks.size());
        items.forEach(value->{
            String name=value.get("name").getAsString();
            MaterialData data=new MaterialData();
            data.item=value;
            result.put(name,data);
        });
        blocks.forEach(value->{
            String name=value.get("name").getAsString();
            MaterialData data=result.get(name);

            if(data==null){
                data=new MaterialData();
            }
            data.block=value;
            result.put(name,data);
        });
        return new ArrayList<>(result.values());
    }
    JsonArray read(String name){
        try(BufferedReader reader=new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(name)))){
            return JsonParser.parseReader(reader).getAsJsonArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static class MaterialData{
        JsonObject item;
        JsonObject block;
    }
}
