/*
 * Copyright 2026 Clickism
 * Released under the GNU General Public License 3.0.
 * See LICENSE.md for details.
 */

package de.clickism.linen.core;

import de.clickism.linen.core.fabric.player.FabricPlayer;
import de.clickism.linen.core.player.LinenPlayer;
import de.clickism.linen.core.scheduler.LinenScheduler;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;

/**
 * Main and factory class for Linen on Fabric.
 */
public class Linen {
    /**
     * Initializes Linen for Fabric.
     */
    public static void initialize() {
        ServerTickEvents.START_SERVER_TICK.register(server -> {
            LinenScheduler.scheduler().tick();
        });
    }

    /**
     * Wraps a Fabric PlayerEntity into a LinenPlayer.
     *
     * @param player The player to wrap.
     * @return The wrapped LinenPlayer.
     */
    public static LinenPlayer player(PlayerEntity player) {
        return new FabricPlayer((ServerPlayerEntity) player);
    }
}
