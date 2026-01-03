/*
 * Copyright 2026 Clickism
 * Released under the GNU General Public License 3.0.
 * See LICENSE.md for details.
 */

package de.clickism.linen.core.fabric.player;

import de.clickism.linen.core.message.ChatLocation;
import de.clickism.linen.core.player.LinenCommandSender;
import net.minecraft.command.DefaultPermissions;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class FabricCommandSender implements LinenCommandSender {
    private final ServerCommandSource sender;

    public FabricCommandSender(ServerCommandSource sender) {
        this.sender = sender;
    }

    @Override
    public boolean hasPermission(String permission) {
        throw new UnsupportedOperationException("Permission checks by string are not supported in Fabric.");
    }

    @Override
    public boolean isOp() {
        var perms = sender.getPermissions();
        return perms.hasPermission(DefaultPermissions.ADMINS) || perms.hasPermission(DefaultPermissions.OWNERS);
    }

    @Override
    public void sendMessage(String legacyMessage, ChatLocation location) {
        sender.sendFeedback(() -> Text.literal(legacyMessage), false);
    }

    @Override
    public Object platformObject() {
        return sender;
    }
}
