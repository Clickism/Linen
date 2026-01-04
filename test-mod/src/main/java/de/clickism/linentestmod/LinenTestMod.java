/*
 * Copyright 2026 Clickism
 * Released under the GNU General Public License 3.0.
 * See LICENSE.md for details.
 */

package de.clickism.linentestmod;

import de.clickism.linen.core.Linen;
import de.clickism.linen.core.message.MessageType;
import de.clickism.linen.core.player.LinenPlayer;
import de.clickism.linen.core.scheduler.LinenScheduler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;

public class LinenTestMod implements ModInitializer {
    @Override
    public void onInitialize() {
        // Initialization logic here
        Linen.initialize();
        PlayerBlockBreakEvents.AFTER.register(((world, playerEntity, blockPos, blockState, blockEntity) -> {
            LinenPlayer player = Linen.player(playerEntity);
            MessageType.SUCCESS.send(player, "You broke a block at " + blockPos);
            MessageType.WARN.sendOverlaySilently(player, "Be careful!");
            LinenScheduler.scheduler()
                    .task((task) -> {
                        long count = task.atMost() - task.executions();
                        MessageType.WARN.send(player, "Countdown: " + count);
                    })
                    .interval(20)
                    .atMost(10)
                    .schedule();
        }));
    }
}