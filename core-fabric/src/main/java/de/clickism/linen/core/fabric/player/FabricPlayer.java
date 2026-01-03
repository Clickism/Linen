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
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.UUID;

public class FabricPlayer extends FabricCommandSender implements LinenPlayer {
    private final ServerPlayerEntity player;

    public FabricPlayer(ServerPlayerEntity player) {
        super(player.getCommandSource());
        this.player = player;
    }

    @Override
    public String getName() {
        return player.getName().getLiteralString();
    }

    @Override
    public UUID getUniqueId() {
        return player.getUuid();
    }

    @Override
    public void sendMessage(String legacyMessage, ChatLocation location) {
        player.sendMessage(Text.literal(legacyMessage), location == ChatLocation.OVERLAY);
    }

    @Override
    public void playSound(String sound, LinenSoundCategory category, float volume, float pitch) throws PlatformObjectNotFoundException {
        var soundEvent = PlatformObjects.soundEvent(sound);
        var soundCategory = PlatformObjects.soundCategory(category);
        player.getEntityWorld().playSound(
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
