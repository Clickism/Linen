/*
 * Copyright 2026 Clickism
 * Released under the GNU General Public License 3.0.
 * See LICENSE.md for details.
 */

package de.clickism.linen.core.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

/**
 * Utility class for colorizing messages.
 */
public class Colorizer {
    private Colorizer() {
        // Utility class
    }
    /**
     * Converts a MiniMessage formatted string to a legacy formatted string.
     *
     * @param miniMessage the MiniMessage formatted string
     * @return the legacy formatted string
     */
    public static String rich(String miniMessage) {
        Component parsed = MiniMessage.miniMessage().deserialize(miniMessage);
        return LegacyComponentSerializer.legacyAmpersand().serialize(parsed);
    }

    /**
     * Converts a legacy formatted string to a legacy formatted string (essentially a no-op).
     *
     * @param legacyMessage the legacy formatted string
     * @return the legacy formatted string
     */
    public static String legacy(String legacyMessage) {
        Component parsed = LegacyComponentSerializer.legacyAmpersand().deserialize(legacyMessage);
        return LegacyComponentSerializer.legacyAmpersand().serialize(parsed);
    }

    /**
     * Converts a legacy formatted string with a custom color character to a legacy formatted string.
     *
     * @param legacyMessage the legacy formatted string
     * @param colorChar     the color character used in the legacy format
     * @return the legacy formatted string
     */
    public static String legacy(String legacyMessage, char colorChar) {
        Component parsed = LegacyComponentSerializer.legacy(colorChar).deserialize(legacyMessage);
        return LegacyComponentSerializer.legacy(colorChar).serialize(parsed);
    }
}
