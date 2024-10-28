package xyz.devcmb.cmbMinigamesServer.CMBase.commands.completions;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import xyz.devcmb.cmbMinigamesServer.CMBase.controllers.MinigameController;
import xyz.devcmb.cmbMinigamesServer.CMBase.controllers.minigames.Minigame;

import java.util.ArrayList;
import java.util.List;

public class MinigameAutocomplete implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length == 1) {
            List<String> minigames = new ArrayList<>();
            for (Minigame m : MinigameController.MINIGAMES) {
                minigames.add(m.getId());
            }
            return minigames;
        }
        if (args.length == 2) {
            return List.of("activate", "deactivate", "start", "end", "reset");
        }
        return null;
    }
}