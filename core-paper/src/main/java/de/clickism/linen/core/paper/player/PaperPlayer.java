/*
 * Copyright 2026 Clickism
 * Released under the GNU General Public License 3.0.
 * See LICENSE.md for details.
 */

package de.clickism.linen.core.paper.player;

import de.clickism.linen.core.player.LinenPlayer;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;

import java.util.UUID;

public record PaperPlayer(Player player) implements LinenPlayer {
    @Override
    public String getName() {
        return "";
    }

    @Override
    public UUID getUniqueId() {
        return null;
    }

    @Override
    public void sendMessage(String legacyMessage) {
        player.sendMessage(LegacyComponentSerializer.legacySection().deserialize(legacyMessage));
    }
}
