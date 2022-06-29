package jevin10.paintball.Runnables.BossBars;

import jevin10.paintball.Paintball;
import jevin10.paintball.Scoreboards.BossBars.LobbyBossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class LobbyBossBarRunnable extends BukkitRunnable {
    static int announcerFrame = 0;
    @Override
    public void run() {
        if (Paintball.getPbWorld() == null) {
            return;
        }
        if (Paintball.getGameScoreboard().getGameInstance().equals("lobby")) {
            for (Player player : Paintball.getPbWorld().getPlayers()) {
                LobbyBossBar.showMyBossBar(player);
            }
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
