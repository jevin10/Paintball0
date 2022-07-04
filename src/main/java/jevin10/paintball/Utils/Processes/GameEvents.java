package jevin10.paintball.Utils.Processes;

import jevin10.paintball.Paintball;
import jevin10.paintball.Scoreboards.GameScoreboard;
import jevin10.paintball.Utils.PlayerData;
import net.md_5.bungee.api.ChatColor;
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
        PlayerData.addDeath(killed);
        PlayerData.addKill(killer);
    }
    public static void gameWin(String team) {
        Paintball.getGameScoreboard().setGameInstance("end");
        String oppositeTeam = team.equals("blue") ? "red" : "blue";
        @NotNull Set<OfflinePlayer> winningPlayers = Paintball.getGameScoreboard().getScoreboard().getTeam(team).getPlayers();
        @NotNull Set<OfflinePlayer> losingPlayers = Paintball.getGameScoreboard().getScoreboard().getTeam(oppositeTeam).getPlayers();

        for (OfflinePlayer p : winningPlayers) {
            if (p.isOnline()) {
                p.getPlayer().sendTitle("GAME WON! ☺", ChatColor.BLUE + String.valueOf(Paintball.getGameScoreboard().getBlueTeamKills()) + ChatColor.WHITE + " - " + ChatColor.RED + Paintball.getGameScoreboard().getRedTeamKills(), 10, 128, 10);
                PlayerData.addWin(p.getPlayer());
                Player onlinePlayer = p.getPlayer();
                onlinePlayer.sendMessage(PlayerData.getMVP().getName() + " was the MVP with " + PlayerData.getKills(PlayerData.getMVP()) + " kills!");
            }
        }

        for (OfflinePlayer p : losingPlayers) {
            if (p.isOnline()) {
                p.getPlayer().sendTitle("GAME LOST! ☹", ChatColor.BLUE + String.valueOf(Paintball.getGameScoreboard().getBlueTeamKills()) + ChatColor.WHITE + " - " + ChatColor.RED + Paintball.getGameScoreboard().getRedTeamKills(), 10, 128, 10);
                PlayerData.addLoss(p.getPlayer());
                Player onlinePlayer = p.getPlayer();
                onlinePlayer.sendMessage(PlayerData.getMVP().getName() + " was the MVP with " + PlayerData.getKills(PlayerData.getMVP()) + " kills!");
            }
        }
    }
}
