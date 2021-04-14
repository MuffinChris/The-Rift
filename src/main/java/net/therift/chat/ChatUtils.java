package net.therift.chat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * This class contains utility functions for working with chat
 */
public class ChatUtils {

    public static final String SPECIAL_COLOR_CHARACTER = "§";
    public static final char ALTERNATE_COLOR_CHARACTER = '&';

    /**
     * Send a colored message to a player.
     * @param p the player
     * @param msg the message to be color coded
     */
    public static void msg(Player p, String msg) {
        p.sendMessage(color(msg));
    }

    /**
     * Convert all & characters to the Bukkit color character
     * @param msg the message to translate
     * @return the colored message
     */
    public static String color(String msg) {
        return ChatColor.translateAlternateColorCodes(ALTERNATE_COLOR_CHARACTER, msg);
    }

    /**
     * Print to console with color!
     * @param msg the message
     */
    public static void so(String msg) {
        Bukkit.getConsoleSender().sendMessage(color(msg));
    }

}
