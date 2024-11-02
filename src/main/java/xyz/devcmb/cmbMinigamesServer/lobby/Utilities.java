package xyz.devcmb.cmbMinigamesServer.lobby;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import xyz.devcmb.cmbMinigamesServer.CmbMinigamesServer;

public class Utilities {
    public static void sendToServer(Player player, String serverName) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(serverName);
        player.sendPluginMessage(CmbMinigamesServer.getPlugin(), "BungeeCord", out.toByteArray());
    }
}
