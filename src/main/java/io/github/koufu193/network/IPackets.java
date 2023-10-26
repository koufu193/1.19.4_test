package io.github.koufu193.network;

import io.github.koufu193.network.packets.AbstractPacket;

public interface IPackets {
    AbstractPacket getClientboundPacket(int id);
    AbstractPacket getServerboundPacket(int id);
}
