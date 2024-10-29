package xyz.devcmb.cmbMinigamesServer.lobby.tools;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Compass {
    public static void giveCompass(Player player){
        ItemStack compass = new ItemStack(Material.COMPASS);
        ItemMeta meta = compass.getItemMeta();
        if(meta == null) return;
        meta.setItemName("Server Compass");
        compass.setItemMeta(meta);
        player.getInventory().setItem(0, compass);
    }
}
