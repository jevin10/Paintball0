package jevin10.paintball.Utils.Processes;

import jevin10.paintball.Paintball;
import jevin10.paintball.Scoreboards.GameScoreboard;
import org.bukkit.entity.Player;

import java.util.Objects;

public class GameEvents {
    public static void playerKill(Player killed, Player killer) {
        String killedTeam = Objects.requireNonNull(Paintball.getGameScoreboard().getScoreboard().getPlayerTeam(killed)).getName();
        String killerTeam = Objects.requireNonNull(Paintball.getGameScoreboard().getScoreboard().getPlayerTeam(killer)).getName();
        Paintball.getGameScoreboard().addKillToTeam(killerTeam);
    }
}
