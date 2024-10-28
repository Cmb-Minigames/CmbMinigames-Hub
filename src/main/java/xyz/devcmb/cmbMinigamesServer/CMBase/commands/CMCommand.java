package xyz.devcmb.cmbMinigamesServer.CMBase.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import xyz.devcmb.cmbMinigamesServer.CmbMinigamesServer;

public class CMCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        commandSender.sendMessage(ChatColor.GREEN + "This server is currently running Cmb Minigames v" + CmbMinigamesServer.VERSION);
        return true;
    }
}
