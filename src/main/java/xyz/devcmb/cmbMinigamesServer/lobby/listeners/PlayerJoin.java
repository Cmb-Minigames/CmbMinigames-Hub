package xyz.devcmb.cmbMinigamesServer.lobby.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import xyz.devcmb.cmbMinigamesServer.lobby.tools.Compass;
import xyz.devcmb.cmbMinigamesServer.lobby.tools.TeleportBow;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.getInventory().clear();

        Location spawnLocation = Bukkit.getWorld("world").getSpawnLocation();
        spawnLocation.setYaw(90);
        spawnLocation.setPitch(0);
        player.teleport(spawnLocation);

        Compass.giveCompass(player);
        TeleportBow.giveBow(player);
    }
}
