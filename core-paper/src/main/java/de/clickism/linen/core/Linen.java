/*
 * Copyright 2026 Clickism
 * Released under the GNU General Public License 3.0.
 * See LICENSE.md for details.
 */

package de.clickism.linen.core;

import de.clickism.linen.core.paper.player.PaperCommandSender;
import de.clickism.linen.core.paper.player.PaperPlayer;
import de.clickism.linen.core.player.LinenCommandSender;
import de.clickism.linen.core.player.LinenPlayer;
import de.clickism.linen.core.scheduler.LinenScheduler;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 * Main and factory class for Linen on Paper.
 */
public class Linen {
    /**
     * Initializes Linen for the given plugin.
     *
     * @param plugin The plugin to initialize Linen for.
     */
    public static void initialize(Plugin plugin) {
        // Initialize Scheduler
        plugin.getServer().getScheduler().runTaskTimer(plugin, () -> {
            LinenScheduler.scheduler().tick();
        }, 0L, 1L);
    }

    /**
     * Wraps a Bukkit Player into a LinenPlayer.
     *
     * @param player The player to wrap.
     * @return The wrapped LinenPlayer.
     */
    public static LinenPlayer player(Player player) {
        return new PaperPlayer(player);
    }

    /**
     * Wraps a Bukkit CommandSender or Player into a LinenCommandSender.
     *
     * @param sender The command sender to wrap.
     * @return The wrapped LinenCommandSender.
     */
    public static LinenCommandSender commandSender(CommandSender sender) {
        if (sender instanceof Player player) {
            return player(player);
        }
        return new PaperCommandSender(sender);
    }
}
