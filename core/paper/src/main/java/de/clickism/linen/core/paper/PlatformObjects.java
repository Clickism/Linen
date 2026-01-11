/*
 * Copyright 2026 Clickism
 * Released under the GNU General Public License 3.0.
 * See LICENSE.md for details.
 */

package de.clickism.linen.core.paper;

import de.clickism.linen.core.platform.PlatformObjectNotFoundException;
import de.clickism.linen.core.sound.LinenSoundCategory;
import org.bukkit.SoundCategory;

public class PlatformObjects {
    public static SoundCategory soundCategory(LinenSoundCategory category) {
        try {
            return SoundCategory.valueOf(category.name());
        } catch (IllegalArgumentException e) {
            throw new PlatformObjectNotFoundException(SoundCategory.class, category.name());
        }
    }
}
