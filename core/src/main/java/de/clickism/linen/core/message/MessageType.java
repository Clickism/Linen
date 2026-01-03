/*
 * Copyright 2026 Clickism
 * Released under the GNU General Public License 3.0.
 * See LICENSE.md for details.
 */

package de.clickism.linen.core.message;

import de.clickism.linen.core.player.LinenPlayer;
import de.clickism.linen.core.scheduler.LinenScheduler;

/**
 * Bundles formatting and sound information for different types of messages.
 */
public interface MessageType {

    /**
     * Predefined message type for success messages.
     */
    MessageType SUCCESS = MessageType.icon("✔")
            .iconColor("green")
            .sound(player -> {
                player.playSound("block.note_block.chime", 1f, 1f);
                LinenScheduler.scheduler()
                        .task(task -> {
                            player.playSound("block.note_block.chime", 1f, 2f);
                        })
                        .delay(2)
                        .schedule();
            });

    /**
     * Predefined message type for warning messages.
     */
    MessageType WARN = MessageType.icon("⚠")
            .iconColor("yellow")
            .sound("block.amethyst_block.resonate", 1f, 1f);

    /**
     * Predefined message type for error messages.
     */
    MessageType ERROR = MessageType.icon("❌")
            .iconColor("red")
            .sound("block.amethyst_block.resonate", 1f, .5f);

    /**
     * Creates a simple message type with the specified chat format.
     * <p>
     * See {@link SimpleMessageType} for details.
     *
     * @param format the chat format
     * @return the simple message type
     */
    static SimpleMessageType chat(String format) {
        return new SimpleMessageType().chat(format);
    }

    /**
     * Creates an icon message type with the specified icon.
     * <p>
     * See {@link IconMessageType} for details.
     *
     * @param icon the icon for the message type
     * @return the icon message type
     */
    static IconMessageType icon(String icon) {
        return new IconMessageType(icon);
    }

    /**
     * Formats a message according to the specified chat location.
     *
     * @param message  the message to format
     * @param location the chat location
     * @return the formatted message
     */
    String format(String message, ChatLocation location);

    /**
     * Plays the sound for this message type to a player.
     *
     * @param player the player to play the sound to
     */
    void playSound(LinenPlayer player);

    /**
     * Sends a message to a player with the specified location and sound option.
     *
     * @param player    the player to send the message to
     * @param message   the message to send
     * @param location  the chat location
     * @param playSound whether to play the associated sound
     * @param rich      whether to send the message as rich text (MiniMessage)
     */
    default void send(LinenPlayer player, String message, ChatLocation location, boolean playSound, boolean rich) {
        String formattedMessage = format(message, location);
        if (rich) {
            player.sendRichMessage(formattedMessage, location);
        } else {
            player.sendMessage(formattedMessage, location);
        }
        if (playSound) {
            playSound(player);
        }
    }

    /**
     * Sends a message to a player in the chat location and plays the associated sound.
     *
     * @param player  the player to send the message to
     * @param message the message to send
     */
    default void send(LinenPlayer player, String message) {
        send(player, message, ChatLocation.CHAT, true, true);
    }

    /**
     * Sends a message to a player in the chat location without playing the associated sound.
     *
     * @param player  the player to send the message to
     * @param message the message to send
     */
    default void sendSilently(LinenPlayer player, String message) {
        send(player, message, ChatLocation.CHAT, false, true);
    }

    /**
     * Sends a message to a player in the overlay location and plays the associated sound.
     *
     * @param player  the player to send the message to
     * @param message the message to send
     */
    default void sendOverlay(LinenPlayer player, String message) {
        send(player, message, ChatLocation.OVERLAY, true, true);
    }

    /**
     * Sends a message to a player in the overlay location without playing the associated sound.
     *
     * @param player  the player to send the message to
     * @param message the message to send
     */
    default void sendOverlaySilently(LinenPlayer player, String message) {
        send(player, message, ChatLocation.OVERLAY, false, true);
    }
}
