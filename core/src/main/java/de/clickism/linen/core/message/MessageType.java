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
    MessageType SUCCESS = MessageType
            .chat("<green>[✔] %s</green>")
            .overlay("<dark_gray>< <green>%s</green> ></dark_gray>")
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
    MessageType WARN = MessageType
            .chat("<yellow>[] %s</yellow>")
            .overlay("<dark_gray>< <yellow>%s</yellow> ></dark_gray>")
            .sound(player -> {
                player.playSound("block.amethyst_block.resonate", 1f, 1f);
            });

    /**
     * Predefined message type for error messages.
     */
    MessageType FAIL = MessageType
            .chat("<red>[✘] %s</red>")
            .overlay("<dark_gray>< <red>%s</red> ></dark_gray>")
            .sound(player -> {
                player.playSound("block.amethyst_block.resonate", 1f, .5f);

            });


    static MessageTypeImpl chat(String format) {
        return new MessageTypeImpl().chat(format);
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
}
