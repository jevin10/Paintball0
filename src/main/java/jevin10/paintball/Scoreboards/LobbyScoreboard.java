package jevin10.paintball.Scoreboards;

import jevin10.paintball.ComponentHandler;
import jevin10.paintball.Paintball;
import jevin10.paintball.Runnables.CountdownTimer;
import jevin10.paintball.Utils.ColorTranslator;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class LobbyScoreboard {
    /**
     *
     * @param p A player (in the lobby) to whom a scoreboard with the correct objective shall be shown
     */
    public static void newTest(Player p) {

        // Check if player is in pbWorld
        if(p.getWorld() != Paintball.getPbWorld()) {
            return;
        }

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getNewScoreboard();

        // DisplaySlot.SIDEBAR
        Objective lobbyObjective = getLobbyObjective(scoreboard, p);

        // no DisplaySlot

        p.setScoreboard(scoreboard);

    }

    public static Objective getLobbyObjective(Scoreboard scoreboard, Player p) {
        Objective lobbyObjective = scoreboard.registerNewObjective("lobby", "dummy", ChatColor.BOLD + "LOBBY       ");
        lobbyObjective.setDisplaySlot(DisplaySlot.SIDEBAR);

        // WARNING: Entries cannot be duplicates.

        // Score 8: " " (1 space)
        lobbyObjective.getScore(" ").setScore(7);

        // Score 9: "Mode: TDM"
        scoreboard.registerNewTeam("gameModeDisplay");
        Team gameModeDisplay = scoreboard.getTeam("gameModeDisplay");
        gameModeDisplay.setSuffix(ComponentHandler.getTdmComponent().toLegacyText());
        gameModeDisplay.addEntry("⚑  ");
        lobbyObjective.getScore("⚑  ").setScore(6);

        // Score 6: "[teamScoreDisplay]"
        scoreboard.registerNewTeam("teamScoreDisplay");
        Team teamScoreDisplay = scoreboard.getTeam("teamScoreDisplay");
        teamScoreDisplay.setSuffix(ComponentHandler.getTeamComponent(p).toLegacyText());
        teamScoreDisplay.addEntry("⛨  ");
        lobbyObjective.getScore("⛨  ").setScore(5);

        // Score 5: "Players: [getPlayerCount]"
        lobbyObjective.getScore("☻  " + Paintball.getGameScoreboard().getPlayers().size()).setScore(4);

        // Score 3: Game Starts:
        lobbyObjective.getScore("⌛  " + CountdownTimer.getTimerColor() + CountdownTimer.getCountdownTimer()).setScore(3);

        return lobbyObjective;
    }

    public static Objective getGameObjective(Scoreboard scoreboard) {
        Objective gameObjective = scoreboard.registerNewObjective("lobby", "dummy", "Game Objective");

        return gameObjective;
    }



}
