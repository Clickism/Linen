/*
 * Copyright 2026 Clickism
 * Released under the GNU General Public License 3.0.
 * See LICENSE.md for details.
 */

package de.clickism.linen.core.message;

import de.clickism.linen.core.player.LinenPlayer;

import java.util.function.Consumer;

/**
 * A builder class for creating custom message types with specific formats and sounds.
 */
public class MessageTypeImpl implements MessageType {
    private String chatFormat;
    private String overlayFormat;
    private Consumer<LinenPlayer> soundPlayer = player -> {};

    /**
     * Creates a new MessageTypeBuilder instance.
     */
    protected MessageTypeImpl() {}

    /**
     * Sets the chat format for this message type.
     *
     * @param format The format string, where %s will be replaced by the message.
     * @return The current MessageTypeBuilder instance.
     */
    public MessageTypeImpl chat(String format) {
        this.chatFormat = format;
        return this;
    }

    /**
     * Sets the overlay format for this message type.
     *
     * @param format The format string, where %s will be replaced by the message.
     * @return The current MessageTypeBuilder instance.
     */
    public MessageTypeImpl overlay(String format) {
        this.overlayFormat = format;
        return this;
    }

    /**
     * Sets the sound to be played when this message type is sent.
     *
     * @param soundPlayer A consumer that accepts a LinenPlayer and plays the desired sound.
     * @return The current MessageTypeBuilder instance.
     */
    public MessageTypeImpl sound(Consumer<LinenPlayer> soundPlayer) {
        this.soundPlayer = soundPlayer;
        return this;
    }

    @Override
    public String format(String message, ChatLocation location) {
        return location == ChatLocation.CHAT
                ? String.format(chatFormat, message)
                : String.format(overlayFormat, message);
    }

    @Override
    public void playSound(LinenPlayer player) {
        soundPlayer.accept(player);
    }
}
