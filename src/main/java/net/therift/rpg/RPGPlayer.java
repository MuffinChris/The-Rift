package net.therift.rpg;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Class containing RPG relevant information on the player
 */
public class RPGPlayer {

    private UUID uuid;

    /**
     * Construct a new RPGPlayer given the Player to attach to.
     * @param p the player
     */
    public RPGPlayer(Player p) {
        uuid = p.getUniqueId();
    }

    /**
     * Return the player attached to the RPGPlayer
     * @return the player
     */
    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    /**
     * Clean up all references
     */
    public void close() {
        uuid = null;
    }

}
