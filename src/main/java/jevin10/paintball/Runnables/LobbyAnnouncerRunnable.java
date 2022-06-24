package jevin10.paintball.Runnables;

import jevin10.paintball.Paintball;
import jevin10.paintball.Scoreboards.BossBars;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class LobbyAnnouncerRunnable extends BukkitRunnable {
    static int announcerFrame = 0;
    @Override
    public void run() {
        if (Paintball.getPbWorld() == null) {
            return;
        }
        for (Player player : Paintball.getPbWorld().getPlayers()) {
            BossBars.showMyBossBar(player);
        }
        incrementAnnouncerFrame();
    }

    public static int getAnnouncerFrame() {
        return announcerFrame;
    }

    public static void incrementAnnouncerFrame() {
        if (announcerFrame >= 200) {
            announcerFrame = 0;
            return;
        }
        announcerFrame++;
    }
}
