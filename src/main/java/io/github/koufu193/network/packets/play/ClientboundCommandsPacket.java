package io.github.koufu193.network.packets.play;

import io.github.koufu193.core.game.commands.nodes.ICommandNode;
import io.github.koufu193.core.game.commands.nodes.LiteralCommandNode;
import io.github.koufu193.core.game.commands.nodes.RootCommandNode;
import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.*;

public class ClientboundCommandsPacket extends AbstractPacket {
    public ClientboundCommandsPacket(@NotNull RootCommandNode root){
        fields(root);
    }
    public ClientboundCommandsPacket(){
        this(RootCommandNode.EMPTY);
    }
    @Override
    public PacketFormat format() {
        return PacketFormat.of(new DataTypes.DataType<RootCommandNode>() {
            @Override
            public byte[] encode(RootCommandNode value) {
                ByteArrayOutputStream output=new ByteArrayOutputStream(size(value));
                HashSet<ICommandNode> nodes=getAll(value);
                List<ICommandNode> list=new ArrayList<>(nodes);
                HashMap<ICommandNode,Integer> ids=new HashMap<>(list.size());
                for(int i=0;i<list.size();i++){
                    ids.put(list.get(i),i);
                }
                //write
                output.writeBytes(DataTypes.VarInt.encode(nodes.size()));
                for(ICommandNode node:nodes){
                    output.write(makeFlag(node));
                    output.writeBytes(DataTypes.VarInt.encode(node.children().size()));
                    for(ICommandNode child:node.children()) output.writeBytes(DataTypes.VarInt.encode(ids.get(child)));
                    if(node.redirect()!=null) output.writeBytes(DataTypes.VarInt.encode(ids.get(node.redirect())));
                    if(!(node instanceof RootCommandNode)) output.writeBytes(DataTypes.String.encode(node.name()));
                    //TODO
                }
                output.writeBytes(DataTypes.VarInt.encode(ids.get(value)));
                return output.toByteArray();
            }

            @Override
            @Deprecated
            public int size(RootCommandNode value) {
                return 128;
            }

            private byte makeFlag(ICommandNode node){
                byte flag= (byte) (node.getClass()==RootCommandNode.class?0:node.getClass()==LiteralCommandNode.class?1:2);
                flag|=(node.executable()?0x04:0x00);
                flag|=(node.redirect()!=null?0x08:0x00);
                //TODO
                return flag;
            }
            private HashSet<ICommandNode> getAll(RootCommandNode root){
                return getAll(root,new HashSet<>());
            }
            private HashSet<ICommandNode> getAll(ICommandNode node,HashSet<ICommandNode> result){
                if(result.contains(node)) return result;
                result.add(node);
                node.children().forEach(a->{
                    if(!result.contains(a)) getAll(a,result);
                });
                if(node.redirect()!=null&&!result.contains(node.redirect())) getAll(node.redirect(), result);
                return result;
            }

            @Override
            public RootCommandNode decode(ByteBuffer buffer) {
                return null;
            }
        });
    }

    @Override
    public int packetId() {
        return 0x10;
    }
}
