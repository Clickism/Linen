/*
 * Copyright 2026 Clickism
 * Released under the GNU General Public License 3.0.
 * See LICENSE.md for details.
 */

package de.clickism.linen.core.paper;

import de.clickism.linen.core.paper.player.PaperPlayer;
import de.clickism.linen.core.player.LinenPlayer;
import org.bukkit.entity.Player;

public class Linen {
    public static LinenPlayer player(Player player) {
        return new PaperPlayer(player);
    }
}
