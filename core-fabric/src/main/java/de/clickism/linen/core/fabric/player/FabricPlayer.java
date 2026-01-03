/*
 * Copyright 2026 Clickism
 * Released under the GNU General Public License 3.0.
 * See LICENSE.md for details.
 */

package de.clickism.linen.core.fabric.player;

import de.clickism.linen.core.player.LinenPlayer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.UUID;

public record FabricPlayer(ServerPlayerEntity player) implements LinenPlayer {

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
        player.sendMessage(Text.literal(legacyMessage), false);
    }

}
