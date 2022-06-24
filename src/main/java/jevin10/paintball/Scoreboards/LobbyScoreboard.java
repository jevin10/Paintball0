package jevin10.paintball.Scoreboards;

import jevin10.paintball.ComponentHandler;
import jevin10.paintball.Paintball;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class LobbyScoreboard {
    public static void newTest(Player p) {

        // Check if player is in pbWorld
        if(p.getWorld() != Paintball.getPbWorld()) {
            return;
        }

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getNewScoreboard();

        // DisplaySlot.SIDEBAR
        Objective lobbyObjective = getLobbyObjective(scoreboard);

        // no DisplaySlot

        p.setScoreboard(scoreboard);

    }

    public static Objective getLobbyObjective(Scoreboard scoreboard) {
        Objective lobbyObjective = scoreboard.registerNewObjective("lobby", "dummy", ChatColor.BOLD + "Lobby Info");
        lobbyObjective.setDisplaySlot(DisplaySlot.SIDEBAR);

        // WARNING: Entries cannot be duplicates.

        // Score 8: " " (1 space)
        lobbyObjective.getScore(" ").setScore(8);

        // Score 7: "Mode: TDM"
        scoreboard.registerNewTeam("gameModeDisplay");
        Team gameModeDisplay = scoreboard.getTeam("gameModeDisplay");
        gameModeDisplay.setSuffix(ComponentHandler.getTdmComponent().toLegacyText());
        gameModeDisplay.addEntry("Mode: ");
        lobbyObjective.getScore("Mode: ").setScore(7);

        // Score 6: "[teamScoreDisplay]"
        scoreboard.registerNewTeam("teamScoreDisplay");
        Team teamScoreDisplay = scoreboard.getTeam("teamScoreDisplay");
        teamScoreDisplay.setSuffix(ComponentHandler.getBlueTeamComponent().toLegacyText());
        teamScoreDisplay.addEntry("Team: ");
        lobbyObjective.getScore("Team: ").setScore(6);

        // Score 5: "Players: [getPlayerCount]"
        lobbyObjective.getScore("Players: " + Paintball.getGameScoreboard().getPlayers().size()).setScore(5);

        // Score 4: "  " (2 space)
        lobbyObjective.getScore("  ").setScore(4);

        // Score 3: Game Starts:
        // Score 2: [TimeLeft]
        lobbyObjective.getScore("Game Starts:").setScore(3);
        lobbyObjective.getScore("null").setScore(2);

        // Score 1: "  " (3 space)
        lobbyObjective.getScore("   ").setScore(1);

        return lobbyObjective;
    }

    public static Objective getGameObjective(Scoreboard scoreboard) {
        Objective gameObjective = scoreboard.registerNewObjective("lobby", "dummy", "Game Objective");

        return gameObjective;
    }



}
