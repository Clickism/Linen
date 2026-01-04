/*
 * Copyright 2026 Clickism
 * Released under the GNU General Public License 3.0.
 * See LICENSE.md for details.
 */

package de.clickism.linen.core.fabric.player;

import de.clickism.linen.core.fabric.PlatformObjects;
import de.clickism.linen.core.message.ChatLocation;
import de.clickism.linen.core.platform.PlatformObjectNotFoundException;
import de.clickism.linen.core.player.LinenPlayer;
import de.clickism.linen.core.sound.LinenSoundCategory;
import java.util.UUID;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;

public class FabricPlayer extends FabricCommandSender implements LinenPlayer {
    private final ServerPlayer player;

    public FabricPlayer(ServerPlayer player) {
        super(player.createCommandSourceStack());
        this.player = player;
    }

    @Override
    public String getName() {
        return player.getName().tryCollapseToString();
    }

    @Override
    public UUID getUniqueId() {
        return player.getUUID();
    }

    @Override
    public void sendMessage(String legacyMessage, ChatLocation location) {
        player.displayClientMessage(Component.literal(legacyMessage), location == ChatLocation.OVERLAY);
    }

    @Override
    public void playSound(String sound, LinenSoundCategory category, float volume, float pitch) throws PlatformObjectNotFoundException {
        var soundEvent = PlatformObjects.soundEvent(sound);
        var soundCategory = PlatformObjects.soundCategory(category);
        player.level().playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                soundEvent,
                soundCategory,
                volume,
                pitch
        );
    }

    @Override
    public Object platformObject() {
        return player;
    }
}
