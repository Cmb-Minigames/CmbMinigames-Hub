package xyz.devcmb.cmbMinigamesServer.CMBase.listeners.minigames;

import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.devcmb.cmbMinigamesServer.CMBase.controllers.MinigameController;
import xyz.devcmb.cmbMinigamesServer.CMBase.controllers.minigames.ManhuntController;
import xyz.devcmb.cmbMinigamesServer.CmbMinigamesServer;

public class ManhuntListeners implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        Player player = event.getEntity();
        if(!MinigameController.isMinigameBeingPlayed("manhunt")) return;
        ManhuntController mhc = (ManhuntController) MinigameController.getById("manhunt");

        new BukkitRunnable() {
            @Override
            public void run() {
                player.spigot().respawn();
            }
        }.runTaskLater(CmbMinigamesServer.getPlugin(), 1L);

        if(mhc.AliveRunners.contains(player)) {
            player.setGameMode(GameMode.SPECTATOR);
            mhc.AliveRunners.remove(player);
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event){
        Player player = event.getPlayer();
        if(!MinigameController.isMinigameBeingPlayed("manhunt")) return;
        ManhuntController mhc = (ManhuntController) MinigameController.getById("manhunt");
        if(mhc.Hunters.contains(player)){
            ItemStack compass = new ItemStack(Material.COMPASS);
            ItemMeta meta = compass.getItemMeta();
            if (meta != null) {
                meta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                compass.setItemMeta(meta);
            }
            player.getInventory().addItem(compass);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if(!MinigameController.isMinigameBeingPlayed("manhunt")) return;
        ManhuntController mhc = (ManhuntController) MinigameController.getById("manhunt");

        if(mhc.Hunters.contains(player) || mhc.Runners.contains(player)){
            player.getInventory().clear();
        }

        CmbMinigamesServer.LOGGER.info("Player " + player.getDisplayName() + " has left the game, removing from manhunt game.");
        mhc.Hunters.remove(player);
        mhc.Runners.remove(player);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if(MinigameController.isMinigameActive("manhunt") && !MinigameController.isMinigameBeingPlayed("manhunt")){
            player.sendMessage(ChatColor.RED + "Manhunt is currently active, but has not started yet, type /hunter or /runner to join the game!");
            return;
        }

        if(!MinigameController.isMinigameBeingPlayed("manhunt")) return;
        ManhuntController mhc = (ManhuntController) MinigameController.getById("manhunt");

        mhc.Hunters.add(player);
        player.setGameMode(GameMode.SURVIVAL);
        player.getInventory().clear();

        ItemStack compass = new ItemStack(Material.COMPASS);
        ItemMeta meta = compass.getItemMeta();
        if (meta != null) {
            meta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            compass.setItemMeta(meta);
        }

        player.getInventory().addItem(compass);
        player.sendMessage(ChatColor.GREEN + "You joined in the midst of a manhunt game, and you have been spawned in as a hunter");
        player.teleport(player.getWorld().getSpawnLocation());
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        Location location = new Location(event.getPlayer().getWorld(), event.getPlayer().getLocation().getX(), event.getPlayer().getLocation().getY() - 1, event.getPlayer().getLocation().getZ());
        World world = location.getWorld();

        if (world != null && world.getEnvironment() == World.Environment.THE_END) {
            Player player = event.getPlayer();
            ManhuntController mhc = (ManhuntController) MinigameController.getById("manhunt");
            if (location.getBlock().getType() == Material.END_PORTAL) {
                if (!MinigameController.isMinigameBeingPlayed("manhunt")) return;

                if (mhc.AliveRunners.contains(player)) {
                    CmbMinigamesServer.LOGGER.info("Player " + player.getDisplayName() + " has entered the end portal, ending game.");
                    mhc.RunnerWin = true;
                }
            }
        }
    }
}