/*
 * Copyright 2026 Clickism
 * Released under the GNU General Public License 3.0.
 * See LICENSE.md for details.
 */

package de.clickism.linen.core.paper.player;

import de.clickism.linen.core.message.ChatLocation;
import de.clickism.linen.core.player.LinenCommandSender;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;

public class PaperCommandSender implements LinenCommandSender {
    private final CommandSender sender;

    public PaperCommandSender(CommandSender sender) {
        this.sender = sender;
    }

    @Override
    public boolean hasPermission(String permission) {
        return sender.hasPermission(permission);
    }

    @Override
    public boolean isOp() {
        return sender.isOp();
    }

    @Override
    public void sendMessage(String legacyMessage, ChatLocation location) {
        sender.sendMessage(legacyMessage);
    }

    @Override
    public void sendRichMessage(String miniMiniMessage, ChatLocation location) {
        var component = MiniMessage.miniMessage().deserialize(miniMiniMessage);
        if (location == ChatLocation.CHAT) {
            sender.sendMessage(component);
        } else {
            sender.sendActionBar(component);
        }
    }

    @Override
    public Object platformObject() {
        return sender;
    }
}
