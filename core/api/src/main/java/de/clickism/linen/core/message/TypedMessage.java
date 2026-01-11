/*
 * Copyright 2026 Clickism
 * Released under the GNU General Public License 3.0.
 * See LICENSE.md for details.
 */

package de.clickism.linen.core.message;

import de.clickism.linen.core.player.LinenCommandSender;

/**
 * Represents a message that is styled with a specific {@link MessageType}.
 */
public interface TypedMessage {
    /**
     * Gets the type of the message.
     *
     * @return the message type
     */
    MessageType type();

    /**
     * Gets the content of the message.
     *
     * @param params optional parameters to format the message
     * @return the message content
     */
    String message(Object... params);

    /**
     * Sends the message to the specified command sender.
     *
     * @param params optional parameters to format the message
     * @param sender the command sender to send the message to
     */
    default void send(LinenCommandSender sender, Object... params) {
        type().send(sender, message(params));
    }

    /**
     * Sends the message to the specified command sender without sound.
     *
     * @param params optional parameters to format the message
     * @param sender the command sender to send the message to
     */
    default void sendSilently(LinenCommandSender sender, Object... params) {
        type().sendSilently(sender, message(params));
    }

    /**
     * Sends the message as an overlay to the specified command sender.
     *
     * @param params optional parameters to format the message
     * @param sender the command sender to send the overlay message to
     */
    default void sendOverlay(LinenCommandSender sender, Object... params) {
        type().sendOverlay(sender, message(params));
    }

    /**
     * Sends the message as an overlay to the specified command sender without sound.
     *
     * @param params optional parameters to format the message
     * @param sender the command sender to send the overlay message to
     */
    default void sendOverlaySilently(LinenCommandSender sender, Object... params) {
        type().sendOverlaySilently(sender, message(params));
    }

    /**
     * Creates a TypedMessage with the specified type and message content.
     *
     * @param type    the message type
     * @param message the message content
     * @return a TypedMessage instance
     */
    static TypedMessage of(MessageType type, String message) {
        return new TypedMessage() {
            @Override
            public MessageType type() {
                return type;
            }

            @Override
            public String message(Object... params) {
                return message;
            }
        };
    }
}
