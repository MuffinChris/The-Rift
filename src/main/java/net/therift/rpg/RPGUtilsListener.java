package net.therift.rpg;

import com.google.inject.Inject;
import net.therift.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class RPGUtilsListener implements Listener {

    private Main plugin;

    @Inject
    public RPGUtilsListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        plugin.getRPGUtils().addPlayer(e.getPlayer());
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        plugin.getRPGUtils().removePlayer(e.getPlayer());
    }

}
