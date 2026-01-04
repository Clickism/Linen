/*
 * Copyright 2026 Clickism
 * Released under the GNU General Public License 3.0.
 * See LICENSE.md for details.
 */

package de.clickism.linen.core.platform;

/**
 * Exception thrown when a platform-specific object is not found.
 */
public class PlatformObjectNotFoundException extends IllegalArgumentException {
    /**
     * Constructs a new PlatformObjectNotFoundException with the specified object class, key, and message.
     *
     * @param objectClass the class of the platform object
     * @param key         the key used to look up the object
     * @param message     additional message providing context
     */
    public PlatformObjectNotFoundException(Class<?> objectClass, String key, String message) {
        super("Platform object \"" + objectClass.getName() + "\" not found for key \"" + key + "\": " + message);
    }

    /**
     * Constructs a new PlatformObjectNotFoundException with the specified object class and key.
     *
     * @param objectClass the class of the platform object
     * @param key         the key used to look up the object
     */
    public PlatformObjectNotFoundException(Class<?> objectClass, String key) {
        super("Platform object \"" + objectClass.getName() + "\" not found for key \"" + key + "\".");
    }
}
