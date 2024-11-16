package xyz.devcmb.cmbMinigamesServer.lobby.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.devcmb.cmbMinigamesServer.CmbMinigamesServer;

public class TeleportBow implements Listener {
    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
        if (event.getEntity() instanceof Arrow arrow) {
            if (arrow.getShooter() instanceof Player player) {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (!arrow.isDead() && !arrow.isInBlock()) {
                            Location arrowLocation = arrow.getLocation();
                            EnderPearl enderPearl = arrow.getWorld().spawn(arrowLocation, EnderPearl.class);
                            enderPearl.setVelocity(arrow.getVelocity());
                            enderPearl.setShooter(player);
                            arrow.remove();
                        } else {
                            cancel();
                        }
                    }
                }.runTaskTimer(CmbMinigamesServer.getPlugin(), 1L, 1L);
            }
        }
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if (event.getEntity() instanceof EnderPearl enderPearl) {
            if (enderPearl.getShooter() instanceof Player player) {
                Location hitLocation = enderPearl.getLocation();
                Location teleportLocation = hitLocation.clone().add(enderPearl.getVelocity().normalize().multiply(2)).add(0, 1, 0);
                teleportLocation.setDirection(enderPearl.getVelocity());
                player.teleport(teleportLocation);
                enderPearl.remove();
            }
        }
    }
}