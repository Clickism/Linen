/*
 * Copyright 2026 Clickism
 * Released under the GNU General Public License 3.0.
 * See LICENSE.md for details.
 */

package de.clickism.linen.core.fabric;

import de.clickism.linen.core.fabric.version.IdentifierWrapper;
import de.clickism.linen.core.platform.PlatformObjectNotFoundException;
import de.clickism.linen.core.sound.LinenSoundCategory;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;

/**
 * Utility class for converting platform-specific objects.
 */
public class PlatformObjects {
    private PlatformObjects() {
        // Prevent instantiation
    }

    /**
     * Converts a sound string to a SoundEvent.
     *
     * @param sound the sound string
     * @return the corresponding SoundEvent
     */
    public static SoundEvent soundEvent(String sound) {
        var id = IdentifierWrapper.tryParse(sound);
        if (id == null) {
            id = IdentifierWrapper.withDefaultNamespace(sound);
        }
        return SoundEvent.createVariableRangeEvent(id.unwrap());
    }

    /**
     * Converts a LinenSoundCategory to a SoundSource.
     *
     * @param category the LinenSoundCategory
     * @return the corresponding SoundSource
     */
    public static SoundSource soundSource(LinenSoundCategory category) {
        try {
            return SoundSource.valueOf(category.name());
        } catch (IllegalArgumentException e) {
            throw new PlatformObjectNotFoundException(SoundSource.class, category.name());
        }
    }
}
