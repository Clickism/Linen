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

public class PaperPlayer extends PaperCommandSender implements LinenPlayer {
    private final Player player;

    public PaperPlayer(Player player) {
        super(player);
        this.player = player;
    }

    @Override
    public String getName() {
        return player.getName();
    }

    @Override
    public UUID getUniqueId() {
        return player.getUniqueId();
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
