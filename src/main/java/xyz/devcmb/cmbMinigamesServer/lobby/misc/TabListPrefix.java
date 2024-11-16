package xyz.devcmb.cmbMinigamesServer.lobby.misc;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;
import xyz.devcmb.cmbMinigamesServer.lobby.Format;

import java.util.Objects;

public class TabListPrefix{
    public Scoreboard getScoreboard(Player player) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = Objects.requireNonNull(manager).getNewScoreboard();

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            String prefix = Format.getPrefix(onlinePlayer);
            Team team = board.getTeam(prefix);
            if (team == null) {
                team = board.registerNewTeam(prefix);
                team.setPrefix(prefix);
            }
            team.addEntry(onlinePlayer.getName());
        }

        return board;
    }
}
