package io.github.koufu193.network.packets.handshake;

import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * ハンドシェイクパケット
 */
public class ServerboundHandshakePacket extends AbstractPacket {
    @NotNull
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.VarInt,DataTypes.String,DataTypes.Short,DataTypes.VarInt);
    }
    @Override
    public int packetId() {
        return 0x00;
    }

    /**
     * クライアントのプロトコルバージョンを取得
     * @return クライアントのプロトコルバージョン
     */
    public int protocolVersion(){
         return (int)this.fields()[0];
    }

    /**
     * クライアントが接続時に使用したサーバーのアドレスを取得
     * @return クライアントが接続時に使用したサーバーのアドレス
     */
    @NotNull
    public String serverAddress(){
        return (String)this.fields()[1];
    }

    /**
     * クライアントが接続時に使用したサーバーのポートを取得
     * @return クライアントが接続時に使用したサーバーのポート
     */
    public short serverPort(){
        return (short)this.fields()[2];
    }

    /**
     * クライアントが希望する状態を取得
     * @return クライアントが希望する状態 送られた値が異常値なら null
     */
    @Nullable
    public NextState nextState(){
        return NextState.byStateNumber(this.nextStateNumber());
    }

    /**
     * クライアントが希望する状態を数値で取得
     * @return 数値でのクライアントが希望する状態
     */
    public int nextStateNumber(){
        return (int)this.fields()[3];
    }
    public enum NextState{
        STATUS(0),LOGIN(1);
        private final int stateNumber;
        NextState(int stateNumber){
            this.stateNumber =stateNumber;
        }
        public int stateNumber(){
            return this.stateNumber;
        }
        public static NextState byStateNumber(int stateNumber){
            for(NextState state:values()){
                if(state.stateNumber()==stateNumber) return state;
            }
            return null;
        }
    }
}
