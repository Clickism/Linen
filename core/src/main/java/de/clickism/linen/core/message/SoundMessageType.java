/*
 * Copyright 2026 Clickism
 * Released under the GNU General Public License 3.0.
 * See LICENSE.md for details.
 */

package de.clickism.linen.core.message;

import de.clickism.linen.core.player.LinenPlayer;
import de.clickism.linen.core.sound.LinenSoundCategory;

import java.util.function.Consumer;

/**
 * A base class for message types that can play sounds.
 *
 * @param <T> The type of the message type extending this class.
 */
@SuppressWarnings("unchecked")
public abstract class SoundMessageType<T> implements MessageType {
    private Consumer<LinenPlayer> soundCallback = player -> {};

    /**
     * Sets the sound callback for this message type.
     *
     * @param soundPlayer The sound callback to be executed when the message is sent.
     * @return the current message type instance.
     */
    public T sound(Consumer<LinenPlayer> soundPlayer) {
        this.soundCallback = soundPlayer;
        return (T) this;
    }

    /**
     * Sets the sound to be played with specified volume and pitch.
     *
     * @param sound  the sound identifier.
     * @param volume the volume
     * @param pitch  the pitch
     * @return the current message type instance.
     */
    public T sound(String sound, float volume, float pitch) {
        this.soundCallback = player -> player.playSound(sound, volume, pitch);
        return (T) this;
    }

    /**
     * Sets the sound to be played with default volume and pitch.
     *
     * @param sound the sound identifier.
     * @return the current message type instance.
     */
    public T sound(String sound) {
        this.soundCallback = player -> player.playSound(sound, 1.0f, 1.0f);
        return (T) this;
    }

    /**
     * Sets the sound to be played with specified category, volume and pitch.
     *
     * @param sound    the sound identifier.
     * @param category the sound category.
     * @param volume   the volume
     * @param pitch    the pitch
     * @return the current message type instance.
     */
    public T sound(String sound, LinenSoundCategory category, float volume, float pitch) {
        this.soundCallback = player -> player.playSound(sound, category, volume, pitch);
        return (T) this;
    }

    @Override
    public void playSound(LinenPlayer player) {
        soundCallback.accept(player);
    }
}
