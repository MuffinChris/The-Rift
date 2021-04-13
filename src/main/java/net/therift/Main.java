package net.therift;

import net.therift.rpg.RPGUtils;
import net.therift.rpg.combat.CombatListener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    /** Constants */
    public static final String NO_PERMISSION = "&cNo permission.";

    /** Important Classes */
    private RPGUtils rpgUtils;
    public RPGUtils getRPGUtils() {
        return rpgUtils;
    }

    /**
     * Method runs when plugin is enabled
     */
    @Override
    public void onEnable() {
        getLogger().info("Starting The Rift Core Plugin.");

        getLogger().info("Registering Commands.");
        registerCommands();

        getLogger().info("Registering Listeners.");
        registerListeners();

        getLogger().info("Creating Classes.");
        rpgUtils = new RPGUtils();

        getLogger().info("The Rift Core Plugin successfully started.");
    }

    /**
     * Method runs when plugin is disabled
     */
    @Override
    public void onDisable() {

    }

    /**
     * Register all commands
     */
    public void registerCommands() {

    }

    /**
     * Register all listeners
     */
    public void registerListeners() {
        getServer().getPluginManager().registerEvents(new CombatListener(), this);
    }

    /**
     * Return an instance of the Main Class
     * (Useful for BukkitRunnables)
     * @return Main
     */
    public static Main getInstance() {
        return JavaPlugin.getPlugin(Main.class);
    }

}
