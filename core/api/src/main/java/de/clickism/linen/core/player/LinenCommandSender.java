/*
 * Copyright 2026 Clickism
 * Released under the GNU General Public License 3.0.
 * See LICENSE.md for details.
 */

package de.clickism.linen.core.player;

import de.clickism.linen.core.message.ChatLocation;
import de.clickism.linen.core.platform.PlatformObjectHolder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

/**
 * Linen command sender interface.
 */
public interface LinenCommandSender extends PlatformObjectHolder {
    /**
     * Checks if the command sender has the specified permission.
     *
     * @param permission the permission to check
     * @return true if the sender has the permission, false otherwise
     */
    boolean hasPermission(String permission);

    /**
     * Checks if the command sender is an operator.
     *
     * @return true if the sender is an operator, false otherwise
     */
    boolean isOp();

    /**
     * Sends a legacy formatted message to the player at the specified chat location.
     *
     * @param legacyMessage the legacy formatted message
     * @param location      the location to send the message to
     */
    void sendMessage(String legacyMessage, ChatLocation location);

    /**
     * Sends a legacy formatted message to the player in the chat.
     *
     * @param legacyMessage the legacy formatted message
     */
    default void sendMessage(String legacyMessage) {
        sendMessage(legacyMessage, ChatLocation.CHAT);
    }

    /**
     * Sends a component message to the player at the specified chat location.
     *
     * @param message  the component message
     * @param location the location to send the message to
     */
    default void sendComponentMessage(Component message, ChatLocation location) {
        sendMessage(LegacyComponentSerializer.legacySection().serialize(message), location);
    }

    /**
     * Sends a component message to the player in the chat.
     *
     * @param miniMessage the MiniMessage formatted string
     */
    default void sendRichMessage(String miniMessage) {
        sendComponentMessage(MiniMessage.miniMessage().deserialize(miniMessage), ChatLocation.CHAT);
    }

    /**
     * Sends a MiniMessage formatted message to the player at the specified chat location.
     *
     * @param miniMessage the MiniMessage formatted string
     * @param location    the location to send the message to
     */
    default void sendRichMessage(String miniMessage, ChatLocation location) {
        sendComponentMessage(MiniMessage.miniMessage().deserialize(miniMessage), location);
    }
}
