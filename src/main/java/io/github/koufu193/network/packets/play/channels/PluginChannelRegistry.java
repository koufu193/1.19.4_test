package io.github.koufu193.network.packets.play.channels;

import io.github.koufu193.core.game.data.Identifier;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * プラグインチャネルのレジストリ
 */
public final class PluginChannelRegistry {
    private static final Map<Identifier, Supplier<PluginChannel>> channels=new HashMap<>(){{
        put(new Identifier("minecraft:brand"),BrandChannel::new);
        put(new Identifier("minecraft:register"),RegisterChannel::new);
    }};

    /**
     * プラグインチャネルのIDからプラグインチャネルを取得
     * @param identifier プラグインチャネルのID
     * @return 生成されたプラグインチャネル 登録されていないIDの場合データ部のサイズが0のプラグインチャネルを返す
     */
    @NotNull
    public static PluginChannel get(Identifier identifier){
        return channels.getOrDefault(identifier,()->new PluginChannel() {
            @NotNull
            @Override
            public Identifier channel() {
                return identifier;
            }

            @Override
            public byte[] bytes() {
                return new byte[0];
            }

            @Override
            public void load(ByteBuffer buffer) {

            }
        }).get();
    }
}
