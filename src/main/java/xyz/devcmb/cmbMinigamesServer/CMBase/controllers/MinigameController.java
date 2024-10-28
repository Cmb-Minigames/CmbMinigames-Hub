package xyz.devcmb.cmbMinigamesServer.CMBase.controllers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import xyz.devcmb.cmbMinigamesServer.CMBase.controllers.minigames.*;
import xyz.devcmb.cmbMinigamesServer.CmbMinigamesServer;

import java.util.ArrayList;
import java.util.List;

public class MinigameController {
    public static final List<Minigame> MINIGAMES = new ArrayList<>();

    public static Minigame ActiveMinigame = null;
    public static Minigame PlayingMinigame = null;

    public static Minigame getById(String id){
        return MINIGAMES.stream().filter(m -> m.getId().equals(id)).findFirst().orElse(null);
    }

    public static boolean isMinigameActive(String MinigameID) {
        return (ActiveMinigame != null) && (ActiveMinigame.getId().equals(MinigameID));
    }

    public static boolean isMinigameBeingPlayed(String MinigameID) {
        return (PlayingMinigame != null) && (PlayingMinigame.getId().equals(MinigameID));
    }

    private static void registerMinigame(Minigame minigame){
        MINIGAMES.add(minigame);
        Permission minigamePermission = new Permission("cmbminigames.minigame." + minigame.getId(), PermissionDefault.OP);
        CmbMinigamesServer.getPlugin().getServer().getPluginManager().addPermission(minigamePermission);
    }

    public static void registerAllMinigames(){
        registerMinigame(new ManhuntController());
        registerMinigame(new BlockShuffleController());
        registerMinigame(new BlockBingoController());
        registerMinigame(new BrawlController());
    }

    public static void activateMinigame(Player executor, String id, Boolean automaticTrigger){
        Minigame minigame = getById(id);
        if(minigame == null) return;

        if(ActiveMinigame == minigame){
            if(!automaticTrigger) executor.sendMessage(ChatColor.RED + "This minigame is already active!");
            return;
        }

        if(PlayingMinigame != null){
            executor.sendMessage(ChatColor.RED + "A minigame is already being played!");
            return;
        }

        if(ActiveMinigame != null){
            ActiveMinigame.resetGame(automaticTrigger);
        }

        ActiveMinigame = minigame;
        minigame.activateGame(executor, automaticTrigger);
    }

    public static void deactivateMinigame(Player executor, String id, Boolean automaticTrigger){
        Minigame minigame = getById(id);
        if(minigame == null) return;

        if(ActiveMinigame != minigame){
            if(!automaticTrigger) executor.sendMessage(ChatColor.RED + "This minigame is not active!");
            return;
        }

        if(PlayingMinigame != null){
            if(!automaticTrigger) executor.sendMessage(ChatColor.RED + "A minigame is already being played!");
            return;
        }

        ActiveMinigame = null;

        minigame.deactivateGame(executor, automaticTrigger);
    }

    public static void startMinigame(Player executor, String id, Boolean automaticTrigger){
        Minigame minigame = getById(id);
        if(minigame == null) return;

        if(ActiveMinigame != minigame){
            if(!automaticTrigger) executor.sendMessage(ChatColor.RED + "You must activate the minigame before starting it");
            return;
        }

        if(PlayingMinigame == minigame){
            if(!automaticTrigger) executor.sendMessage(ChatColor.RED + "This minigame is already being played!");
            return;
        }

        PlayingMinigame = minigame;
        minigame.startGame(executor, automaticTrigger);
    }

    public static void endMinigame(Player executor, String id, Boolean automaticTrigger){
        Minigame minigame = getById(id);
        if(minigame == null) return;

        if(PlayingMinigame != minigame){
            if(!automaticTrigger) executor.sendMessage(ChatColor.RED + "This minigame is not being played!");
            return;
        }

        PlayingMinigame = null;
        ActiveMinigame = null;

        minigame.endGame(automaticTrigger);
    }

    public static void resetMinigame(Player executor, String id, Boolean automaticTrigger){
        Minigame minigame = getById(id);
        if(minigame == null) return;

        if(ActiveMinigame != minigame){
            if(!automaticTrigger) executor.sendMessage(ChatColor.RED + "This minigame is not active!");
            return;
        }

        minigame.resetGame(automaticTrigger);
    }
}
