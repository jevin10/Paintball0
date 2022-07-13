package jevin10.paintball.Runnables;

import jevin10.paintball.BossBars.ArenaBossBar;
import jevin10.paintball.BossBars.LobbyBossBar;
import jevin10.paintball.Paintball;
import jevin10.paintball.Scoreboards.PaintballScoreboard;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

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
        // cancel if player is not online
        if (!p.isOnline()) {
            this.cancel();
        }
        if (p.getWorld() != Paintball.getPbWorld()) {
            this.cancel();
        }
        PaintballScoreboard.newTest(p);
        p.setFoodLevel(20);
        if (Paintball.getGameScoreboard().getGameInstance().equals("lobby")) {
            LobbyBossBar lobbyBossBar = LobbyBossBar.getLobbyBossBarList().get(LobbyBossBar.getLobbyBossBarList().size() - 1);

            if (!lobbyBossBar.getBossBar().getPlayers().contains(p)) {
                lobbyBossBar.addPlayer(p);
            }

            if (p.getHealth() > 0) {
                p.setHealth(Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue());
            }
        }
        if (Paintball.getGameScoreboard().getGameInstance().equals("arena")) {
            LobbyBossBar lobbyBossBar = LobbyBossBar.getLobbyBossBarList().get(LobbyBossBar.getLobbyBossBarList().size() - 1);
            lobbyBossBar.getBossBar().removeAll();

            ArenaBossBar arenaBossBar = ArenaBossBar.getArenaBossBarList().get(ArenaBossBar.getArenaBossBarList().size() - 1);
            if (!arenaBossBar.getBossBar().getPlayers().contains(p)) {
                arenaBossBar.addPlayer(p);
            }
        }
        if (Paintball.getGameScoreboard().getGameInstance().equals("end")) {
            ArenaBossBar arenaBossBar = ArenaBossBar.getArenaBossBarList().get(ArenaBossBar.getArenaBossBarList().size() - 1);
            arenaBossBar.getBossBar().removeAll();
        }
    }
}
