package xyz.devcmb.cmbMinigamesServer;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.devcmb.cmbMinigamesServer.lobby.listeners.ListenerManager;

import java.util.logging.Logger;

public final class CmbMinigamesServer extends JavaPlugin {
    public static Logger LOGGER;
    public static String VERSION;
    public static boolean PluginDisabled = false;
    public static final boolean DeveloperModeEnabled = true;

    public static CmbMinigamesServer getPlugin() {
        return plugin;
    }

    private static CmbMinigamesServer plugin;

    @Override
    public void onEnable() {
        LOGGER = getLogger();
        VERSION = getDescription().getVersion();
        plugin = this;

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        ListenerManager.register();
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
    }
}
