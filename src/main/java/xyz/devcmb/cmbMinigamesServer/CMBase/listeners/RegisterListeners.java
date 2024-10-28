package xyz.devcmb.cmbMinigamesServer.CMBase.listeners;

import org.bukkit.plugin.PluginManager;
import xyz.devcmb.cmbMinigamesServer.CMBase.listeners.minigames.BlockBingoListeners;
import xyz.devcmb.cmbMinigamesServer.CMBase.listeners.minigames.BlockShuffleListeners;
import xyz.devcmb.cmbMinigamesServer.CMBase.listeners.minigames.ManhuntListeners;
import xyz.devcmb.cmbMinigamesServer.CmbMinigamesServer;

public class RegisterListeners {
    public static void register(){
        CmbMinigamesServer plugin = CmbMinigamesServer.getPlugin();
        PluginManager pluginManager = plugin.getServer().getPluginManager();

        pluginManager.registerEvents(new GlobalListeners(), CmbMinigamesServer.getPlugin());
        pluginManager.registerEvents(new DeathEffects(), CmbMinigamesServer.getPlugin());
        pluginManager.registerEvents(new PartyListeners(), CmbMinigamesServer.getPlugin());

        // Minigame listeners
        pluginManager.registerEvents(new ManhuntListeners(), CmbMinigamesServer.getPlugin());
        pluginManager.registerEvents(new BlockShuffleListeners(), CmbMinigamesServer.getPlugin());
        pluginManager.registerEvents(new BlockBingoListeners(), CmbMinigamesServer.getPlugin());
    }
}
