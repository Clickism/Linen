package de.clickism.linentestplugin;

import de.clickism.linen.core.Linen;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class LinenTestPlugin extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getLogger().info("LinenTestPlugin has been enabled!");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Linen.player(event.getPlayer()).sendRichMessage("<green>You <bold>broke</bold> a block!</green>");
    }
}