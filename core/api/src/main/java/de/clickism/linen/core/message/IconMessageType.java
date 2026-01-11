/*
 * Copyright 2026 Clickism
 * Released under the GNU General Public License 3.0.
 * See LICENSE.md for details.
 */

package de.clickism.linen.core.message;

import org.jetbrains.annotations.Nullable;

/**
 * A message type that displays an icon alongside the message.
 * <p>
 * The icon can be customized with color and placement options.
 * <p>
 * Example:
 * <pre><code>
 * [✔] Operation completed successfully!
 * [✖] Operation failed!
 * </code></pre>
 */
public class IconMessageType extends SoundMessageType<IconMessageType> {
    private static final String OVERLAY_BRACKET_COLOR = "dark_gray";
    private static final String LEFT_BRACKET = wrapWithColor("<", OVERLAY_BRACKET_COLOR);
    private static final String RIGHT_BRACKET = wrapWithColor(">", OVERLAY_BRACKET_COLOR);

    private String icon;
    private String iconFormat = "[%s]";
    private @Nullable String iconColor;
    private @Nullable String messageColor;
    private boolean iconInOverlay = true;

    /**
     * Creates an IconMessageType with the specified icon.
     *
     * @param icon the icon to display alongside the message
     */
    protected IconMessageType(String icon) {
        this.icon = icon;
    }

    /**
     * Sets the color of the icon.
     * <p>
     * Will also be used as the message color if no message color is set.
     *
     * @param color the color to set
     * @return the current IconMessageType instance
     */
    public IconMessageType iconColor(String color) {
        this.iconColor = color;
        return this;
    }

    /**
     * Sets the color of the message text.
     *
     * @param color the color to set
     * @return the current IconMessageType instance
     */
    public IconMessageType messageColor(String color) {
        this.messageColor = color;
        return this;
    }

    /**
     * Sets whether the icon should be displayed in the overlay.
     *
     * @param iconInOverlay true to display the icon in the overlay, false otherwise
     * @return the current IconMessageType instance
     */
    public IconMessageType iconInOverlay(boolean iconInOverlay) {
        this.iconInOverlay = iconInOverlay;
        return this;
    }

    /**
     * Sets the format of the icon.
     *
     * @param format the format string, where %s will be replaced by the icon
     * @return the current IconMessageType instance
     */
    public IconMessageType iconFormat(String format) {
        this.iconFormat = format;
        return this;
    }

    @Override
    public String format(String message, ChatLocation location) {
        String coloredMessage = wrapWithColor(message, messageColor == null ? iconColor : messageColor);
        if (location == ChatLocation.CHAT) {
            String coloredIcon = wrapWithColor(iconFormat.formatted(icon), iconColor);
            return coloredIcon + " " + coloredMessage;
        } else {
            String coloredIcon = wrapWithColor(icon, iconColor);
            if (iconInOverlay) {
                return LEFT_BRACKET + " " + coloredIcon + " " + coloredMessage + " " + RIGHT_BRACKET;
            } else {
                return coloredMessage;
            }
        }
    }

    /**
     * Wraps the given text with the specified color tags in MiniMessage format.
     *
     * @param text  the text to wrap
     * @param color the color to use, or null for no color
     * @return the wrapped text
     */
    private static String wrapWithColor(String text, @Nullable String color) {
        if (color == null) return text;
        return "<" + color + ">" + text + "</" + color + ">";
    }

}