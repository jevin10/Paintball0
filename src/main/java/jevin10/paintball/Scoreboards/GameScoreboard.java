package jevin10.paintball.Scoreboards;

import jevin10.paintball.Paintball;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.HashSet;
import java.util.Set;

public class GameScoreboard {
    ScoreboardManager manager = Bukkit.getScoreboardManager();
    Scoreboard scoreboard = manager.getNewScoreboard();
    Team blueTeam = null;
    Team redTeam = null;
    Team noTeam = null;
    int blueTeamKills = 0;
    int redTeamKills = 0;

    public GameScoreboard() {
        Objective killsObjective = scoreboard.registerNewObjective("kills", "dummy", "kills");

        scoreboard.registerNewTeam("blue");
        scoreboard.registerNewTeam("red");
        scoreboard.registerNewTeam("no");

        blueTeam = scoreboard.getTeam("blue");
        redTeam = scoreboard.getTeam("red");
        noTeam = scoreboard.getTeam("no");
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    /**
     *
     * @param teamName Name of team you want to add player to: takes "no", "red", and "blue"
     * @param p Name of player to add
     */
    public void addPlayerToTeam(String teamName, Player p) {
        switch (teamName) {
            case "no" -> noTeam.addPlayer(p);
            case "red" -> redTeam.addPlayer(p);
            case "blue" -> blueTeam.addPlayer(p);
        }
    }

    /**
     *
     * @return Set of online players registered on the main gameScoreboard
     */
    public Set<Player> getPlayers() {
        Set<Player> playerList = new HashSet<>();
        for (OfflinePlayer p : blueTeam.getPlayers()) {
            if (!p.isOnline()) {
                continue;
            }
            Player player = (Player) p;
            if (player.getWorld() != Paintball.getPbWorld()) {
                continue;
            }
            playerList.add(player);
        }
        for (OfflinePlayer p : redTeam.getPlayers()) {
            if (!p.isOnline()) {
                continue;
            }
            Player player = (Player) p;
            if (player.getWorld() != Paintball.getPbWorld()) {
                continue;
            }
            playerList.add(player);
        }
        for (OfflinePlayer p : noTeam.getPlayers()) {
            if (!p.isOnline()) {
                continue;
            }
            Player player = (Player) p;
            if (player.getWorld() != Paintball.getPbWorld()) {
                continue;
            }
            playerList.add(player);
        }
        return playerList;
    }

    /**
     *
     * @param teamName name of team you want
     * @return set of online players on that team
     */
    public Set<Player> getTeamPlayers(String teamName) {
        Set<Player> playerList = new HashSet<>();
        Team team = null;

        switch (teamName) {
            case "red" -> team = redTeam;
            case "blue" -> team = blueTeam;
            case "no" -> team = noTeam;
        }

        for (OfflinePlayer p : team.getPlayers()) {
            if (!p.isOnline()) {
                continue;
            }
            Player player = (Player) p;
            if (player.getWorld() != Paintball.getPbWorld()) {
                continue;
            }
            playerList.add(player);
        }

        return playerList;
    }
}
