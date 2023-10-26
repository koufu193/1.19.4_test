package io.github.koufu193.network.packets.play;

import io.github.koufu193.core.game.data.GameProfile;
import io.github.koufu193.core.game.data.component.TextComponent;
import io.github.koufu193.core.game.entities.Player;
import io.github.koufu193.core.game.entities.interfaces.IPlayer;
import io.github.koufu193.core.properties.Properties;
import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.*;

public class ClientboundUpdatePlayerInfoPacket extends AbstractPacket {
    ClientboundUpdatePlayerInfoPacket(){}
    public ClientboundUpdatePlayerInfoPacket(PlayerActions... actionsArray){
        if(actionsArray.length==0) {
            fields(0,actionsArray);
            return;
        }
        for(PlayerActions actions:actionsArray){
            actions.actions().sort(Comparator.comparingInt(PlayerAction::mask));
        }
        List<PlayerAction> actionList=actionsArray[0].actions();
        Class<?> previousAction=null;
        for(PlayerAction action:actionList){
            if(previousAction==action.getClass()) throw new IllegalArgumentException("already registered action");
            previousAction=action.getClass();
        }
        if(!Arrays.stream(actionsArray).allMatch(actions->{
            if(actionList.size()!=actions.actions().size()) return false;
            for(int i=0,size=actionList.size();i<size;i++){
                if(actionList.get(i).getClass()!=actions.actions().get(i).getClass()) return false;
            }
            return true;
        })) throw new IllegalArgumentException("invalid actionsArray");
        fields(
                (byte)actionList.stream().mapToInt(PlayerAction::mask).reduce(0,(a,b)->a|b),actionsArray
        );
    }
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.Byte, new DataTypes.DataType<PlayerActions>() {
            @Override
            public byte[] encode(PlayerActions value) {
                ByteArrayOutputStream output=new ByteArrayOutputStream(size(value));
                output.writeBytes(DataTypes.UUID.encode(value.id()));
                for(PlayerAction action:value.actions()) output.writeBytes(action.encode());
                return output.toByteArray();
            }

            @Override
            public int size(PlayerActions value) {
                int size=DataTypes.UUID.size(value.id());
                for(PlayerAction action:value.actions()) size+=action.size();
                return size;
            }

            @Override
            public PlayerActions decode(ByteBuffer buffer) {
                return null;
            }
        }.array());
    }

    @Override
    public int packetId() {
        return 0x3a;
    }
    public record PlayerActions(@NotNull UUID id, @NotNull List<PlayerAction> actions){
        public PlayerActions(@NotNull UUID id,@NotNull PlayerAction... actions){
            this(id,new ArrayList<>(List.of(actions)));
        }
    }
    public interface PlayerAction{
        byte[] encode();

        byte mask();

        int size();
    }

    public static final class AddPlayerAction implements PlayerAction {
        private final String name;
        private final Properties properties;

        public AddPlayerAction(@NotNull String name, @NotNull Properties properties) {
            this.name = name;
            this.properties = properties;
        }

        public AddPlayerAction(@NotNull IPlayer player) {
            this(player.name(), player.profile().properties());
        }
        public AddPlayerAction(@NotNull GameProfile profile){
            this(profile.name(),profile.properties());
        }

        @Override
        public byte[] encode() {
            ByteArrayOutputStream output = new ByteArrayOutputStream(size());
            output.writeBytes(DataTypes.String.encode(this.name));
            output.writeBytes(DataTypes.Properties.encode(this.properties));
            return output.toByteArray();
        }

        @Override
        public byte mask() {
            return 0x01;
        }

        @Override
        public int size() {
            return DataTypes.String.size(this.name) + DataTypes.Properties.size(this.properties);
        }
    }

    public static final class InitializeChatAction implements PlayerAction {
        private final boolean signed;

        public InitializeChatAction(){this.signed = false;}

        @Override
        public byte[] encode() {
            if (!signed) {
                ByteArrayOutputStream output = new ByteArrayOutputStream(size());
                output.writeBytes(DataTypes.Bool.encode(this.signed));
                return output.toByteArray();
            }
            return new byte[0];
        }

        @Override
        public byte mask() {
            return 0x02;
        }

        @Override
        public int size() {
            return DataTypes.Bool.size(this.signed);
        }
    }

    public static final class UpdateGameModeAction implements PlayerAction {
        private final Player.GameMode gameMode;

        public UpdateGameModeAction(@NotNull Player.GameMode gameMode) {
            this.gameMode = gameMode;
        }

        @Override
        public byte[] encode() {
            ByteArrayOutputStream output = new ByteArrayOutputStream(size());
            output.writeBytes(DataTypes.VarInt.encode(this.gameMode.id()));
            return output.toByteArray();
        }

        @Override
        public byte mask() {
            return 0x04;
        }

        @Override
        public int size() {
            return DataTypes.VarInt.size(this.gameMode.id());
        }
    }

    public static final class ListPlayerAction implements PlayerAction {
        private final boolean listed;

        public ListPlayerAction(boolean listed) {
            this.listed = listed;
        }

        @Override
        public byte[] encode() {
            ByteArrayOutputStream output = new ByteArrayOutputStream(size());
            output.writeBytes(DataTypes.Bool.encode(this.listed));
            return output.toByteArray();
        }

        @Override
        public byte mask() {
            return 0x08;
        }

        @Override
        public int size() {
            return DataTypes.Bool.size(this.listed);
        }
    }

    public static final class UpdatePingAction implements PlayerAction {
        private final int ping;

        public UpdatePingAction(int ping) {
            this.ping = ping;
        }

        @Override
        public byte[] encode() {
            ByteArrayOutputStream output = new ByteArrayOutputStream(size());
            output.writeBytes(DataTypes.VarInt.encode(this.ping));
            return output.toByteArray();
        }

        @Override
        public byte mask() {
            return 0x10;
        }

        @Override
        public int size() {
            return DataTypes.VarInt.size(this.ping);
        }
    }

    public static final class UpdateDisplayNameAction implements PlayerAction {
        private final TextComponent displayName;

        public UpdateDisplayNameAction(@Nullable TextComponent displayName) {
            this.displayName = displayName;
        }

        @Override
        public byte[] encode() {
            ByteArrayOutputStream output = new ByteArrayOutputStream(size());
            output.writeBytes(DataTypes.Bool.encode(this.displayName != null));
            if (this.displayName != null) output.writeBytes(DataTypes.Chat.encode(this.displayName));
            return output.toByteArray();
        }

        @Override
        public byte mask() {
            return 0x20;
        }

        @Override
        public int size() {
            return DataTypes.Bool.size(this.displayName != null)
                    + (this.displayName != null ? DataTypes.Chat.size(this.displayName) : 0);
        }
    }
}
