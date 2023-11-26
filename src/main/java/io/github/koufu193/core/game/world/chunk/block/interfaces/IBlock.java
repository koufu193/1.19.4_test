package io.github.koufu193.core.game.world.chunk.block.interfaces;

import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.core.game.data.Material;
import org.jetbrains.annotations.NotNull;
import org.jglrxavpok.hephaistos.mca.BlockState;
import org.jglrxavpok.hephaistos.nbt.NBTCompoundLike;

public interface IBlock {
    /**
     * ブロックの位置を取得
     * @return ブロックの位置
     */
    @NotNull
    Location location();

    /**
     * ブロックのタイプを取得
     * @return ブロックのタイプ
     */
    @NotNull
    Material type();

    /**
     * ブロックのNBTを取得
     * @return ブロックのNBT
     */
    @NotNull
    NBTCompoundLike nbt();
}
