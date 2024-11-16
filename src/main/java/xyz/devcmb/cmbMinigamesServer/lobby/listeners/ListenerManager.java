package xyz.devcmb.cmbMinigamesServer.lobby.listeners;

import org.bukkit.Bukkit;
import xyz.devcmb.cmbMinigamesServer.CmbMinigamesServer;

public class ListenerManager {
    public static void register(){
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(), CmbMinigamesServer.getPlugin());
        Bukkit.getPluginManager().registerEvents(new TeleportBow(), CmbMinigamesServer.getPlugin());
        Bukkit.getPluginManager().registerEvents(new BreakBlocking(), CmbMinigamesServer.getPlugin());
        Bukkit.getPluginManager().registerEvents(new Compass(), CmbMinigamesServer.getPlugin());
        Bukkit.getPluginManager().registerEvents(new ChatListeners(), CmbMinigamesServer.getPlugin());
    }
}
