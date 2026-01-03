/*
 * Copyright 2026 Clickism
 * Released under the GNU General Public License 3.0.
 * See LICENSE.md for details.
 */

package de.clickism.linen.core.fabric;

import de.clickism.linen.core.fabric.player.FabricPlayer;
import de.clickism.linen.core.player.LinenPlayer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;

public class Linen {
    public static LinenPlayer player(PlayerEntity player) {
        return new FabricPlayer((ServerPlayerEntity) player);
    }

    public static boolean isServerPlayer(PlayerEntity player) {
        return player instanceof ServerPlayerEntity;
    }
}
