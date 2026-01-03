package de.clickism.linentestmod;

import de.clickism.linen.core.Linen;
import de.clickism.linen.core.message.MessageType;
import de.clickism.linen.core.player.LinenPlayer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class LinenTestMod implements ModInitializer {
    @Override
    public void onInitialize() {
        // Initialization logic here
        Linen.initialize();
        PlayerBlockBreakEvents.AFTER.register(((world, playerEntity, blockPos, blockState, blockEntity) -> {
            LinenPlayer player = Linen.player(playerEntity);
            MessageType.SUCCESS.send(player, "You broke a block at " + blockPos);
            MessageType.WARN.sendOverlaySilently(player, "Be careful!");
        }));
    }
}