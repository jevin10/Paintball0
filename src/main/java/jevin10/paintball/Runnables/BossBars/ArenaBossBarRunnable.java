package jevin10.paintball.Runnables.BossBars;

import jevin10.paintball.Paintball;
import jevin10.paintball.Scoreboards.BossBars.ArenaBossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ArenaBossBarRunnable extends BukkitRunnable {
    static int announcerFrame = 0;
    @Override
    public void run() {
        if (Paintball.getPbWorld() == null) {
            return;
        }
        if (Paintball.getGameScoreboard().getGameInstance().equals("arena")) {
            for (Player player : Paintball.getPbWorld().getPlayers()) {
                ArenaBossBar.showMyBossBar(player);
            }
        }
        incrementAnnouncerFrame();
    }
    public static void incrementAnnouncerFrame() {
        if (announcerFrame >= 12) {
            announcerFrame = 0;
            return;
        }
        announcerFrame++;
    }
    public static int getAnnouncerFrame() {
        return announcerFrame;
    }
}
