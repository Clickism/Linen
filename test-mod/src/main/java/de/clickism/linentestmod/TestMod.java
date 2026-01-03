package de.clickism.linentestmod;

import de.clickism.linen.core.fabric.Linen;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;

public class TestMod implements ModInitializer {
    @Override
    public void onInitialize() {
        // Initialization logic here
        PlayerBlockBreakEvents.AFTER.register(((world, playerEntity, blockPos, blockState, blockEntity) -> {
            Linen.player(playerEntity).sendMessage("You broke a block at " + blockPos);
            Linen.player(playerEntity).sendRichMessage("<green>You broke a <bold>block</bold> at </><yellow>" + blockPos);
        }));
    }
}