/*
 * Copyright 2026 Clickism
 * Released under the GNU General Public License 3.0.
 * See LICENSE.md for details.
 */

package de.clickism.linen.core.message.colorizer;


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
        return MiniMiniMessage.parse(miniMessage);
    }

    /**
     * Converts a legacy formatted string with '&amp;' color codes to a legacy formatted string with '§' color codes.
     * <p>
     * You can escape the '&amp;' character by prefixing it with a backslash ('\&amp;').
     *
     * @param legacyMessage the legacy formatted string with '&amp;' color codes
     * @return the legacy formatted string with '§' color codes
     */
    public static String legacyAmpersand(String legacyMessage) {
        return legacyMessage.replaceAll("(?<!\\\\)&(?=[a-zA-Z0-9])", "§");
    }
}
