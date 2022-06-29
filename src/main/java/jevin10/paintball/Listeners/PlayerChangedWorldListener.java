package jevin10.paintball.Listeners;

import jevin10.paintball.Paintball;
import jevin10.paintball.Runnables.ScoreboardRunnable;
import jevin10.paintball.Scoreboards.BossBars;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
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
            BossBars.showMyBossBar(p);
            if(!Paintball.getGameScoreboard().getPlayers().contains(p)) {
                Paintball.getGameScoreboard().addPlayerToTeam("no", p);
            }
            BukkitTask scoreboardRunnable = new ScoreboardRunnable(p).runTaskTimer(Paintball.getPlugin(), 0L, 10L);
        }
    }
}
