/*
 * Copyright 2026 Clickism
 * Released under the GNU General Public License 3.0.
 * See LICENSE.md for details.
 */

package de.clickism.linen.core.paper.player;

import de.clickism.linen.core.message.ChatLocation;
import de.clickism.linen.core.paper.PlatformObjects;
import de.clickism.linen.core.platform.PlatformObjectNotFoundException;
import de.clickism.linen.core.player.LinenPlayer;
import de.clickism.linen.core.sound.LinenSoundCategory;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;

import java.util.UUID;

public record PaperPlayer(Player player) implements LinenPlayer {
    @Override
    public String getName() {
        return player.getName();
    }

    @Override
    public UUID getUniqueId() {
        return player.getUniqueId();
    }

    @Override
    public void sendMessage(String legacyMessage, ChatLocation location) {
        if (location == ChatLocation.CHAT) {
            player.sendMessage(LegacyComponentSerializer.legacySection().deserialize(legacyMessage));
        } else {
            player.sendActionBar(LegacyComponentSerializer.legacySection().deserialize(legacyMessage));
        }
    }

    @Override
    public void playSound(String sound, LinenSoundCategory category, float volume, float pitch) throws PlatformObjectNotFoundException {
        player.playSound(player, sound, PlatformObjects.soundCategory(category), volume, pitch);
    }

    @Override
    public Object platformObject() {
        return player;
    }
}
