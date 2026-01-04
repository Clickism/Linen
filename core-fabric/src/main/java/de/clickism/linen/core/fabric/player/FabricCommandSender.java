/*
 * Copyright 2026 Clickism
 * Released under the GNU General Public License 3.0.
 * See LICENSE.md for details.
 */

package de.clickism.linen.core.fabric.player;

import de.clickism.linen.core.fabric.version.VersionUtil;
import de.clickism.linen.core.message.ChatLocation;
import de.clickism.linen.core.player.LinenCommandSender;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

/**
 * Fabric implementation of LinenCommandSender.
 */
public class FabricCommandSender implements LinenCommandSender {
    private final CommandSourceStack sender;

    /**
     * Constructor for FabricCommandSender.
     *
     * @param sender the CommandSourceStack to wrap
     */
    public FabricCommandSender(CommandSourceStack sender) {
        this.sender = sender;
    }

    @Override
    public boolean hasPermission(String permission) {
        throw new UnsupportedOperationException("Permission checks by string are not supported in Fabric.");
    }

    @Override
    public boolean isOp() {
        return VersionUtil.isOp(sender);
    }

    @Override
    public void sendMessage(String legacyMessage, ChatLocation location) {
        sender.sendSuccess(() -> Component.literal(legacyMessage), false);
    }

    @Override
    public Object platformObject() {
        return sender;
    }
}
