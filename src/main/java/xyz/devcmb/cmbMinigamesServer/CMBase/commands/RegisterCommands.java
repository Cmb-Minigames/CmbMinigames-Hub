package xyz.devcmb.cmbMinigamesServer.CMBase.commands;

import xyz.devcmb.cmbMinigamesServer.CMBase.commands.completions.MinigameAutocomplete;
import xyz.devcmb.cmbMinigamesServer.CmbMinigamesServer;

import java.util.Objects;

public class RegisterCommands {
    public static void register(){
        CmbMinigamesServer plugin = CmbMinigamesServer.getPlugin();
        Objects.requireNonNull(plugin.getCommand("cm")).setExecutor(new CMCommand());
        Objects.requireNonNull(plugin.getCommand("party")).setExecutor(new PartyCommand());
        Objects.requireNonNull(plugin.getCommand("minigame")).setExecutor(new MinigameCommand());
        Objects.requireNonNull(plugin.getCommand("minigame")).setTabCompleter(new MinigameAutocomplete());
        Objects.requireNonNull(plugin.getCommand("hunter")).setExecutor(new HunterCommand());
        Objects.requireNonNull(plugin.getCommand("runner")).setExecutor(new RunnerCommand());
    }
}
