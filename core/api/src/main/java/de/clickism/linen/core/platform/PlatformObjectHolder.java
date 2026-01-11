/*
 * Copyright 2026 Clickism
 * Released under the GNU General Public License 3.0.
 * See LICENSE.md for details.
 */

package de.clickism.linen.core.platform;

/**
 * A holder for platform-specific objects.
 */
public interface PlatformObjectHolder {
    /**
     * Returns the platform-specific object held by this holder.
     *
     * @return The platform-specific object.
     */
    Object platformObject();
}
