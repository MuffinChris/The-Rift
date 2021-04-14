package net.therift.admin;

import net.therift.util.AliasConstants;
import net.therift.util.ArgParser;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class GamemodeCommand implements CommandExecutor {

    private Map<String, GameMode> aliasMap;

    /**
     * Generate the alias map
     */
    public GamemodeCommand() {
        aliasMap = new HashMap<>();
        aliasMap.put(AliasConstants.CREATIVE_ALIAS, GameMode.CREATIVE);
        aliasMap.put(AliasConstants.SURVIVAL_ALIAS, GameMode.SURVIVAL);
        aliasMap.put(AliasConstants.SPECTATOR_ALIAS, GameMode.SPECTATOR);
    }

    /**
     * Aliases for gamemodes (excludes adventure because who uses that lmao)
     * @param sender the command sender
     * @param command the command instance
     * @param label the command name /<command>
     * @param args the arguments after the command label
     * @return if executed
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            return executeGamemodeCommand(p, aliasMap.get(label.toLowerCase()), args);
        } else if (sender instanceof ConsoleCommandSender){
            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            return executeGamemodeCommand(console, aliasMap.get(label.toLowerCase()), args);
        }
        return false;
    }

    /**
     * Execute gamemode command alias for a player
     * @param p the player
     * @param gamemode the gamemode
     * @param args player arguments
     * @return if successful
     */
    public boolean executeGamemodeCommand(Player p, GameMode gamemode, String[] args) {
        if (gamemode == GameMode.CREATIVE) {
            return p.performCommand("gamemode creative" + ArgParser.getArgs(args));
        }
        if (gamemode == GameMode.SURVIVAL) {
            return p.performCommand("gamemode survival" + ArgParser.getArgs(args));
        }
        if (gamemode == GameMode.SPECTATOR) {
            return p.performCommand("gamemode spectator" + ArgParser.getArgs(args));
        }
        return false;
    }

    /**
     * Execute gamemode command alias for a console sender
     * @param console console
     * @param gamemode the gamemode
     * @param args player arguments
     * @return if successful
     */
    public boolean executeGamemodeCommand(ConsoleCommandSender console, GameMode gamemode, String[] args) {
        if (gamemode == GameMode.CREATIVE) {
            return Bukkit.dispatchCommand(console, "gamemode creative" + ArgParser.getArgs(args));
        }
        if (gamemode == GameMode.SURVIVAL) {
            return Bukkit.dispatchCommand(console, "gamemode survival" + ArgParser.getArgs(args));
        }
        if (gamemode == GameMode.SPECTATOR) {
            return Bukkit.dispatchCommand(console, "gamemode spectator" + ArgParser.getArgs(args));
        }
        return false;
    }

}
