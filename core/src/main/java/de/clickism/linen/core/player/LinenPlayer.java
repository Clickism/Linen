/*
 * Copyright 2026 Clickism
 * Released under the GNU General Public License 3.0.
 * See LICENSE.md for details.
 */

package de.clickism.linen.core.player;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.util.UUID;

public interface LinenPlayer {

    String getName();

    UUID getUniqueId();

    void sendMessage(String legacyMessage);

    default void sendMessage(Component message) {
        sendMessage(LegacyComponentSerializer.legacySection().serialize(message));
    }

    default void sendRichMessage(String miniMessage) {
        sendMessage(MiniMessage.miniMessage().deserialize(miniMessage));
    }


}
