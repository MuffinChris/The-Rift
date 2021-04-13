package net.therift.rpg;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Class containing RPG relevant utility
 */
public class RPGUtils {

    private Map<Player, RPGPlayer> rpgPlayerMap;

    /**
     * Construct RPGUtils with necessary information
     */
    public RPGUtils() {
        rpgPlayerMap = new HashMap<>();
    }

    /**
     * Return the rpgPlayerMap
     * @return the map
     */
    public Map<Player, RPGPlayer> getRpgPlayerMap() {
        return rpgPlayerMap;
    }

    /**
     * Add a new player to the RPG Player Map
     * @param p the player
     */
    public void addPlayer(Player p) {
        rpgPlayerMap.put(p, new RPGPlayer(p));
    }

    /**
     * Remove a player from the RPG Player Map
     * @param p the player
     */
    public void removePlayer(Player p) {
        rpgPlayerMap.remove(p).close();
    }

    /**
     * Get the RPGPlayer of a player
     * @param p the player
     */
    public RPGPlayer getRPGPlayer(Player p) {
        return rpgPlayerMap.get(p);
    }

}
