package jevin10.paintball.Runnables;

import jevin10.paintball.Paintball;
import jevin10.paintball.Scoreboards.LobbyScoreboard;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ScoreboardRunnable extends BukkitRunnable {

    Player p;

    /**
     * @param p A player to be shown the scoreboard to
     */
    public ScoreboardRunnable(Player p) {
        this.p = p;
    }

    @Override
    public void run() {
        LobbyScoreboard.newTest(p);
        // cancel if player is not online
        if (!p.isOnline()) {
            this.cancel();
        }
        if (p.getWorld() != Paintball.getPbWorld()) {
            this.cancel();

        }
    }
}
