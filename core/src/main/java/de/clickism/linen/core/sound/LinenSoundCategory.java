/*
 * Copyright 2026 Clickism
 * Released under the GNU General Public License 3.0.
 * See LICENSE.md for details.
 */

package de.clickism.linen.core.sound;

/**
 * Enumeration of sound categories used in Linen.
 */
public enum LinenSoundCategory {
    /**
     * Master sound category.
     */
    MASTER,
    /**
     * Music sound category.
     */
    MUSIC,
    /**
     * Records sound category.
     */
    RECORDS,
    /**
     * Weather sound category (ambient environmental sounds like rain).
     */
    WEATHER,
    /**
     * Block-related sounds (placing, breaking, interacting with blocks).
     */
    BLOCKS,
    /**
     * Hostile creature sounds (mobs that attack the player).
     */
    HOSTILE,
    /**
     * Neutral creature sounds (mobs that are not hostile by default).
     */
    NEUTRAL,
    /**
     * Player-related sounds (footsteps, damage, actions).
     */
    PLAYERS,
    /**
     * Ambient sounds (background ambience and environment).
     */
    AMBIENT,
    /**
     * Voice sounds (speech and vocalizations).
     */
    VOICE,
}
