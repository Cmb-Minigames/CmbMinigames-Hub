package xyz.devcmb.cmbMinigamesServer.lobby.misc;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreboardManager {
    public static void updateScoreboard(Player player){
        Scoreboard board = new TabListPrefix().getScoreboard(player);
        player.setScoreboard(board);
    }
}
