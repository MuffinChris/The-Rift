package net.therift.rpg.combat.melee;

import net.therift.Main;
import net.therift.rpg.RPGPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import javax.inject.Inject;

public class StyleCooldownManager {

    private Main plugin;

    @Inject
    public StyleCooldownManager(Main plugin) {
        this.plugin = plugin;
    }

    /**
     * Reset a player's combo sequence if too much time has elapsed
     */
    public void resetStylePeriodic() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                    attemptTimeout(p);
                }
            }
        }.runTaskTimer(plugin, 5L, 1L);
    }

    /**
     * Attempt to timeout a player's combo if valid
     * @param p the player
     */
    public void attemptTimeout(Player p) {
        RPGPlayer rp = plugin.getRPGUtils().getRPGPlayer(p);
        MeleeStyle style = rp.getStyle();
        if (style.shouldTimeout()) {
            style.timeoutHitCount();
        }
    }

}
