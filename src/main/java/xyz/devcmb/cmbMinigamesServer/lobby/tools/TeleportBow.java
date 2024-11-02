package xyz.devcmb.cmbMinigamesServer.lobby.tools;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemRarity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TeleportBow {
    public static void giveBow(Player player){
        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta meta = bow.getItemMeta();
        if(meta == null) return;
        meta.setItemName("Teleport Bow");
        meta.setRarity(ItemRarity.RARE);
        meta.setUnbreakable(true);
        bow.setItemMeta(meta);
        bow.addEnchantment(Enchantment.INFINITY, 1);
        player.getInventory().setItem(9, new ItemStack(Material.ARROW, 1));
        player.getInventory().setItem(8, bow);
    }
}