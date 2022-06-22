package jevin10.paintball.Runnables;

import jevin10.paintball.Scoreboards.TestScoreboard;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ScoreboardRunnable extends BukkitRunnable {

    Player p;

    public ScoreboardRunnable(Player p) {
        this.p = p;
    }

    @Override
    public void run() {
        TestScoreboard.newTest(p);
    }
}
