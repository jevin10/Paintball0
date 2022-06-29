package jevin10.paintball.Listeners;

import jevin10.paintball.Paintball;
import jevin10.paintball.Runnables.ScoreboardRunnable;
import jevin10.paintball.Scoreboards.BossBars.LobbyBossBar;
import jevin10.paintball.Utils.Processes.SetupInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.scheduler.BukkitTask;

/**
 * A listener that handles a player the moment they change worlds.
 */
public class PlayerChangedWorldListener implements Listener {
    @EventHandler
    public void onPlayerChangeWorld(PlayerChangedWorldEvent event) {
        Player p = event.getPlayer();
        if (Paintball.getPbWorld() == null) {
            return;
        }
        if (event.getFrom() == Paintball.getPbWorld()) {
            return;
        }
        if (p.getWorld() == Paintball.getPbWorld()) {
            LobbyBossBar.showMyBossBar(p);
            if(!Paintball.getGameScoreboard().getPlayers().contains(p)) {
                Paintball.getGameScoreboard().addPlayerToTeam("no", p);
            }
            if (Paintball.getGameScoreboard().getGameInstance().equals("lobby")) {
                SetupInventory.lobby(p);
            }
            BukkitTask scoreboardRunnable = new ScoreboardRunnable(p).runTaskTimer(Paintball.getPlugin(), 0L, 10L);
        }
    }
}
