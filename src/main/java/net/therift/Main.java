package net.therift;

import com.google.inject.Inject;
import com.google.inject.Injector;
import net.therift.admin.GamemodeCommand;
import net.therift.rpg.RPGUtils;
import net.therift.rpg.RPGUtilsListener;
import net.therift.rpg.combat.CombatListener;
import net.therift.rpg.combat.HPBarManager;
import net.therift.rpg.combat.melee.StyleCooldownManager;
import net.therift.server.MotdListener;
import net.therift.util.AliasConstants;
import net.therift.util.BinderModule;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    /** Constants */
    public static final String NO_PERMISSION = "&cNo permission.";
    public static final String PLUGIN_PATH = "plugins/Rift/";

    /** Important Classes */
    private RPGUtils rpgUtils;
    public RPGUtils getRPGUtils() { return rpgUtils; }
    @Inject private HPBarManager hpBarManager;
    @Inject private StyleCooldownManager styleCooldownManager;

    /** Commands to inject */
    @Inject private GamemodeCommand gamemodeCommand;

    /**
     * Register all commands
     */
    public void registerCommands() {
        // Gamemode Command
        this.getCommand(AliasConstants.CREATIVE_ALIAS).setExecutor(this.gamemodeCommand);
        this.getCommand(AliasConstants.SURVIVAL_ALIAS).setExecutor(this.gamemodeCommand);
        this.getCommand(AliasConstants.SPECTATOR_ALIAS).setExecutor(this.gamemodeCommand);

    }

    /** Listeners to inject */
    @Inject private CombatListener combatListener;
    @Inject private RPGUtilsListener rpgUtilsListener;

    /**
     * Register all listeners
     */
    public void registerListeners() {
        this.getServer().getPluginManager().registerEvents(this.combatListener, this);
        this.getServer().getPluginManager().registerEvents(this.rpgUtilsListener, this);
        this.getServer().getPluginManager().registerEvents(new MotdListener(), this);
    }

    /**
     * Method runs when plugin is enabled
     */
    @Override
    public void onEnable() {
        getLogger().info("Starting The Rift Core Plugin.");

        getLogger().info("Starting Dependency Injector.");
        BinderModule module = new BinderModule(this);
        Injector injector = module.createInjector();
        injector.injectMembers(this);

        getLogger().info("Setting up Important Classes.");
        rpgUtils = new RPGUtils();
        hpBarManager.sendHpPeriodic();
        styleCooldownManager.resetStylePeriodic();

        getLogger().info("Registering Commands.");
        registerCommands();

        getLogger().info("Registering Listeners.");
        registerListeners();

        getLogger().info("The Rift Core Plugin successfully started.");
    }

    /**
     * Method runs when plugin is disabled
     */
    @Override
    public void onDisable() {

    }

}
