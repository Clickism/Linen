/*
 * Copyright 2026 Clickism
 * Released under the GNU General Public License 3.0.
 * See LICENSE.md for details.
 */

package de.clickism.linen.core.player;

import de.clickism.linen.core.platform.PlatformObjectNotFoundException;
import de.clickism.linen.core.sound.LinenSoundCategory;

import java.util.UUID;

/**
 * Linen player interface.
 */
public interface LinenPlayer extends LinenCommandSender {

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
