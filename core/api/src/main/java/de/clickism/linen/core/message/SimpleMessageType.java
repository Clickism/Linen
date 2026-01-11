/*
 * Copyright 2026 Clickism
 * Released under the GNU General Public License 3.0.
 * See LICENSE.md for details.
 */

package de.clickism.linen.core.message;

/**
 * A builder class for creating custom message types with specific formats and sounds.
 */
public class SimpleMessageType extends SoundMessageType<SimpleMessageType> {
    private String chatFormat;
    private String overlayFormat;

    /**
     * Creates a SimpleMessageType with default formats.
     */
    protected SimpleMessageType() {}

    /**
     * Sets the chat format for this message type.
     *
     * @param format The format string, where %s will be replaced by the message.
     * @return The current SimpleMessageType instance.
     */
    public SimpleMessageType chat(String format) {
        this.chatFormat = format;
        return this;
    }

    /**
     * Sets the overlay format for this message type.
     *
     * @param format The format string, where %s will be replaced by the message.
     * @return The current SimpleMessageType instance.
     */
    public SimpleMessageType overlay(String format) {
        this.overlayFormat = format;
        return this;
    }

    @Override
    public String format(String message, ChatLocation location) {
        return location == ChatLocation.CHAT
                ? String.format(chatFormat, message)
                : String.format(overlayFormat, message);
    }
}
