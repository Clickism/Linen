/*
 * Copyright 2026 Clickism
 * Released under the GNU General Public License 3.0.
 * See LICENSE.md for details.
 */

package de.clickism.linen.core.player;

import de.clickism.linen.core.exception.PlatformObjectNotFoundException;
import de.clickism.linen.core.message.ChatLocation;
import de.clickism.linen.core.platform.PlatformObjectHolder;
import de.clickism.linen.core.sound.LinenSoundCategory;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.util.UUID;

/**
 * Linen player interface representing a player in the game.
 */
public interface LinenPlayer extends PlatformObjectHolder {

    /**
     * Gets the name of the player.
     *
     * @return the name
     */
    String getName();

    /**
     * Gets the unique ID of the player.
     *
     * @return the unique ID
     */
    UUID getUniqueId();

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

    /**
     * Plays a sound to the player.
     *
     * @param sound    the sound identifier
     * @param category the sound category
     * @param volume   the volume
     * @param pitch    the pitch
     * @throws PlatformObjectNotFoundException if the sound or category could not be found
     */
    void playSound(String sound, LinenSoundCategory category, float volume, float pitch) throws PlatformObjectNotFoundException;

    /**
     * Plays a sound to the player with the MASTER category.
     *
     * @param sound  the sound  identifier
     * @param volume the volume
     * @param pitch  the pitch
     * @throws PlatformObjectNotFoundException if the sound could not be foundq-
     */
    default void playSound(String sound, float volume, float pitch) throws PlatformObjectNotFoundException {
        playSound(sound, LinenSoundCategory.MASTER, volume, pitch);
    }

}
