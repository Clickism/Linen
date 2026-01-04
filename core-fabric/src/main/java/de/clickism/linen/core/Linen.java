/*
 * Copyright 2026 Clickism
 * Released under the GNU General Public License 3.0.
 * See LICENSE.md for details.
 */

package de.clickism.linen.core;

import de.clickism.linen.core.fabric.player.FabricCommandSender;
import de.clickism.linen.core.fabric.player.FabricPlayer;
import de.clickism.linen.core.player.LinenCommandSender;
import de.clickism.linen.core.player.LinenPlayer;
import de.clickism.linen.core.scheduler.LinenScheduler;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

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
    public static LinenPlayer player(Player player) {
        return new FabricPlayer((ServerPlayer) player);
    }

    /**
     * Wraps a ServerCommandSource into a LinenCommandSender.
     *
     * @param source The command source to wrap.
     * @return The wrapped LinenCommandSender.
     */
    public static LinenCommandSender commandSender(CommandSourceStack source) {
        return new FabricCommandSender(source);
    }

    /**
     * Wraps a Fabric PlayerEntity into a LinenCommandSender.
     *
     * @param player The player to wrap.
     * @return The wrapped LinenCommandSender.
     */
    public static LinenCommandSender commandSender(Player player) {
        return new FabricCommandSender(((ServerPlayer) player).createCommandSourceStack());
    }
}
