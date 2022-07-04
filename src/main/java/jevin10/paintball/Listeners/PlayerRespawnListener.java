package jevin10.paintball.Listeners;

import jevin10.paintball.Paintball;
import jevin10.paintball.Utils.Processes.SetupInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawnListener implements Listener {
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player p = event.getPlayer();
        if (Paintball.getPbWorld() == null) {
            return;
        }
        if (p.getWorld() == Paintball.getPbWorld()) {
            if (Paintball.getGameScoreboard().getGameInstance().equals("lobby")) {
                p.teleport(Paintball.getPlugin().getConfig().getLocation("lobbyLocation"));
            }
            else if (Paintball.getGameScoreboard().getGameInstance().equals("arena")) {
                p.teleport(Paintball.getPlugin().getConfig().getLocation("arenaLocation"));
            }

        }
    }
}
