/*
 * Copyright 2026 Clickism
 * Released under the GNU General Public License 3.0.
 * See LICENSE.md for details.
 */

package de.clickism.linen.core.fabric;

import de.clickism.linen.core.exception.PlatformObjectNotFoundException;
import de.clickism.linen.core.sound.LinenSoundCategory;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class PlatformObjects {
    public static SoundEvent soundEvent(String sound) {
        try {
            return SoundEvent.of(Identifier.tryParse(sound));
        } catch (Exception e) {
            throw new PlatformObjectNotFoundException(SoundEvent.class, sound);
        }
    }

    public static SoundCategory soundCategory(LinenSoundCategory category) {
        try {
            return SoundCategory.valueOf(category.name());
        } catch (IllegalArgumentException e) {
            throw new PlatformObjectNotFoundException(SoundCategory.class, category.name());
        }
    }
}
