package net.therift.util;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import net.therift.Main;

/**
 * Code created by @MrDienns
 */
public class BinderModule extends AbstractModule {

    private final Main plugin;

    /**
     * Construct a new binder module
     * @param plugin with main class
     */
    public BinderModule(Main plugin) {
        this.plugin = plugin;
    }

    /**
     * Use guice to create a new injector
     * @return the injector
     */
    public Injector createInjector() {
        return Guice.createInjector(this);
    }

    /**
     * Tells guice to use the plugin instance everytime we need it
     */
    @Override
    protected void configure() {
        this.bind(Main.class).toInstance(this.plugin);
    }
}
