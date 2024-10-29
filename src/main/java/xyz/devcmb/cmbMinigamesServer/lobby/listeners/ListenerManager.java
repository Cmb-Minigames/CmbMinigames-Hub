package xyz.devcmb.cmbMinigamesServer.lobby.listeners;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import xyz.devcmb.cmbMinigamesServer.CmbMinigamesServer;

public class ListenerManager {
    public static void register(){
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(), CmbMinigamesServer.getPlugin());
        Bukkit.getPluginManager().registerEvents(new TeleportBow(), CmbMinigamesServer.getPlugin());
    }
}
