package jevin10.paintball.Scoreboards;

import jevin10.paintball.ComponentHandler;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class LobbyScoreboard {

    public static void newTest(Player p) {

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getNewScoreboard();

        Objective objective = scoreboard.registerNewObjective("lobby", "dummy", ChatColor.BOLD + "Lobby Info");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        // WARNING: Entries cannot be duplicates.

        // Score 8: " " (1 space)
        objective.getScore(" ").setScore(8);

        // Score 7: "Mode: TDM"
        scoreboard.registerNewTeam("gameModeDisplay");
        Team gameModeDisplay = scoreboard.getTeam("gameModeDisplay");
        gameModeDisplay.setSuffix(ComponentHandler.getTdmComponent().toLegacyText());
        gameModeDisplay.addEntry("Mode: ");
        objective.getScore("Mode: ").setScore(7);

        // Score 6: "[teamScoreDisplay]"
        scoreboard.registerNewTeam("teamScoreDisplay");
        Team teamScoreDisplay = scoreboard.getTeam("teamScoreDisplay");
        teamScoreDisplay.setSuffix(ComponentHandler.getBlueTeamComponent().toLegacyText());
        teamScoreDisplay.addEntry("Team: ");
        objective.getScore("Team: ").setScore(6);

        // Score 5: "Players: [getPlayerCount]"
        objective.getScore("Players: null").setScore(5);

        // Score 4: "  " (2 space)
        objective.getScore("  ").setScore(4);

        // Score 3: Game Starts:
        // Score 2: [TimeLeft]
        objective.getScore("Game Starts:").setScore(3);
        objective.getScore("null").setScore(2);

        // Score 1: "  " (3 space)
        objective.getScore("   ").setScore(1);

        p.setScoreboard(scoreboard);

    }

}
