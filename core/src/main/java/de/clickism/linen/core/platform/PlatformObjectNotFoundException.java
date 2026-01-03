/*
 * Copyright 2026 Clickism
 * Released under the GNU General Public License 3.0.
 * See LICENSE.md for details.
 */

package de.clickism.linen.core.platform;

public class PlatformObjectNotFoundException extends IllegalArgumentException {
    public PlatformObjectNotFoundException(Class<?> objectClass, String key, String message) {
        super("Platform object \"" + objectClass.getName() + "\" not found for key \"" + key + "\": " + message);
    }

    public PlatformObjectNotFoundException(Class<?> objectClass, String key) {
        super("Platform object \"" + objectClass.getName() + "\" not found for key \"" + key + "\".");
    }
}
