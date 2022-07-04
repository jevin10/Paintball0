package jevin10.paintball.Utils.Processes;

import jevin10.paintball.Paintball;
import jevin10.paintball.Scoreboards.GameScoreboard;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Set;

public class GameEvents {
    public static void playerKill(Player killed, Player killer) {
        String killedTeam = Objects.requireNonNull(Paintball.getGameScoreboard().getScoreboard().getPlayerTeam(killed)).getName();
        String killerTeam = Objects.requireNonNull(Paintball.getGameScoreboard().getScoreboard().getPlayerTeam(killer)).getName();
        Paintball.getGameScoreboard().addKillToTeam(killerTeam);
    }
    public static void gameWin(String team) {
        Paintball.getGameScoreboard().setGameInstance("end");
        String oppositeTeam = team.equals("blue") ? "red" : "blue";
        @NotNull Set<OfflinePlayer> winningPlayers = Paintball.getGameScoreboard().getScoreboard().getTeam(team).getPlayers();
        @NotNull Set<OfflinePlayer> losingPlayers = Paintball.getGameScoreboard().getScoreboard().getTeam(oppositeTeam).getPlayers();

        for (OfflinePlayer p : winningPlayers) {
            if (p.isOnline()) {
                p.getPlayer().sendTitle("GAME WON! ", "Null", 10, 128, 10);
            }
        }

        for (OfflinePlayer p : losingPlayers) {
            if (p.isOnline()) {
                p.getPlayer().sendTitle("You lost!", "Null", 10, 128, 10);
            }
        }
    }
}
