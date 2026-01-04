/*
 * Copyright 2026 Clickism
 * Released under the GNU General Public License 3.0.
 * See LICENSE.md for details.
 */

package de.clickism.linen.core.fabric;

import de.clickism.linen.core.platform.PlatformObjectNotFoundException;
import de.clickism.linen.core.sound.LinenSoundCategory;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;

public class PlatformObjects {
    public static SoundEvent soundEvent(String sound) {
        var id = Identifier.tryParse(sound);
        if (id == null) {
            id = Identifier.withDefaultNamespace(sound);
        }
        return SoundEvent.createVariableRangeEvent(id);
    }

    public static SoundSource soundCategory(LinenSoundCategory category) {
        try {
            return SoundSource.valueOf(category.name());
        } catch (IllegalArgumentException e) {
            throw new PlatformObjectNotFoundException(SoundSource.class, category.name());
        }
    }
}
