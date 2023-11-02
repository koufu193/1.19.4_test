package io.github.koufu193.core.game.world.chunk.block.interfaces;

import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.core.game.data.Material;
import org.jglrxavpok.hephaistos.mca.BlockState;

public interface IBlock {
    Location location();
    Material<?> type();
    BlockState convert();
}
