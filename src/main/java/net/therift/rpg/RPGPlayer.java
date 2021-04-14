package net.therift.rpg;

import net.therift.rpg.combat.melee.MeleeStyle;
import net.therift.rpg.combat.melee.styles.BloodborneVengeance;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Class containing RPG relevant information on the player
 */
public class RPGPlayer {

    private UUID uuid;

    private MeleeStyle style;

    /**
     * Construct a new RPGPlayer given the Player to attach to.
     * @param p the player
     */
    public RPGPlayer(Player p) {
        uuid = p.getUniqueId();
        style = new BloodborneVengeance(p);
    }

    /**
     * Clean up all references
     */
    public void close() {
        style.close();
        style = null;
        uuid = null;
    }

    /**
     * Return the player attached to the RPGPlayer
     * @return the player
     */
    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    /**
     * Get the player's melee style
     * @return a melee style
     */
    public MeleeStyle getStyle() {
        return style;
    }

}
