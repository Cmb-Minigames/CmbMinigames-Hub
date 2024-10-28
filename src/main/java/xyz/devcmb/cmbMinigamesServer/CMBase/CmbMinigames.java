package xyz.devcmb.cmbMinigamesServer.CMBase;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;
import xyz.devcmb.cmbMinigamesServer.CMBase.commands.RegisterCommands;
import xyz.devcmb.cmbMinigamesServer.CMBase.controllers.MinigameController;
import xyz.devcmb.cmbMinigamesServer.CMBase.controllers.PartyController;
import xyz.devcmb.cmbMinigamesServer.CMBase.listeners.RegisterListeners;
import xyz.devcmb.cmbMinigamesServer.CMBase.misc.Database;
import xyz.devcmb.cmbMinigamesServer.CmbMinigamesServer;

public class CmbMinigames {
    public static void onEnable(){
        Database.connect();

        MinigameController.registerAllMinigames();
        RegisterCommands.register();
        RegisterListeners.register();

        CmbMinigamesServer.LOGGER.info("Cmb Minigames base module has awoken");
    }

    public static void onDisable(){
        for (Player player : Bukkit.getOnlinePlayers()){
            PartyController.onLeave(new PlayerQuitEvent(player, "Server closed"));
        }

        CmbMinigamesServer.LOGGER.info("Cmb Minigames base module has been murdered");
    }
}
