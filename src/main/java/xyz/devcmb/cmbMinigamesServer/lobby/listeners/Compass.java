package xyz.devcmb.cmbMinigamesServer.lobby.listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.devcmb.cmbMinigamesServer.lobby.Utilities;

import java.util.List;
import java.util.Objects;

public class Compass implements Listener {
    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item != null && item.getType() == Material.COMPASS) {
            Inventory compassMenu = Bukkit.createInventory(player, 27, "Servers");
            ItemStack randomMinigames = new ItemStack(Material.IRON_SWORD);
            ItemMeta randomMinigamesMeta = randomMinigames.getItemMeta();
            if (randomMinigamesMeta == null) return;
            randomMinigamesMeta.setItemName("Random Minigames");
            String text = PlaceholderAPI.setPlaceholders(player, "%bungee_random% players online");
            randomMinigamesMeta.setLore(List.of(ChatColor.WHITE + "A server where random minigames run every once in a while", ChatColor.GOLD + text));
            randomMinigames.setItemMeta(randomMinigamesMeta);

            compassMenu.setItem(13, randomMinigames);
            player.openInventory(compassMenu);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        String title = event.getView().getTitle();
        Player player = (Player) event.getWhoClicked();

        if (title.equalsIgnoreCase("Servers")) {
            event.setCancelled(true);
            ItemStack item = event.getCurrentItem();
            if (item == null) return;

            if (Objects.requireNonNull(item.getItemMeta()).getItemName().equals("Random Minigames")) {
                Utilities.sendToServer(player, "random");
                player.closeInventory();
            }
        }
    }
}