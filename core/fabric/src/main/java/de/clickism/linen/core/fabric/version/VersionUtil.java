/*
 * Copyright 2026 Clickism
 * Released under the GNU General Public License 3.0.
 * See LICENSE.md for details.
 */

package de.clickism.linen.core.fabric.version;

import net.minecraft.commands.CommandSourceStack;
//? if >=1.21.11
import net.minecraft.server.permissions.Permissions;

/**
 * Utility class for player version-specific operations.
 */
public class VersionUtil {
    private VersionUtil() {
        // Prevent instantiation
    }

    /**
     * Checks if the command sender has operator (op) privileges.
     *
     * @param sender the command source stack
     * @return true if the sender is an operator, false otherwise
     */
    public static boolean isOp(CommandSourceStack sender) {
        //? if >=1.21.11 {
        var perms = sender.permissions();
        return perms.hasPermission(Permissions.COMMANDS_ADMIN) || perms.hasPermission(Permissions.COMMANDS_OWNER);
        //?} else {
        /*return sender.hasPermission(4);
         *///?}
    }
}
