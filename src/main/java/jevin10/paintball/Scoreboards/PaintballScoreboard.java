package jevin10.paintball.Scoreboards;

import jevin10.paintball.ComponentHandler;
import jevin10.paintball.Paintball;
import jevin10.paintball.Runnables.ArenaTimer;
import jevin10.paintball.Runnables.EndTimer;
import jevin10.paintball.Runnables.LobbyTimer;
import jevin10.paintball.Utils.PlayerData;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class PaintballScoreboard {
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
        switch (Paintball.getGameScoreboard().getGameInstance()) {
            case "lobby":
                Objective lobbyObjective = getLobbyObjective(scoreboard, p);
                break;
            case "arena":
                Objective arenaObjective = getArenaObjective(scoreboard, p);
                break;
            case "end":
                Objective endObjective = getEndObjective(scoreboard, p);
                break;
        }


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
        LobbyTimer lobbyTimer = LobbyTimer.getLobbyTimers().get(LobbyTimer.getLobbyTimers().size() - 1);
        lobbyObjective.getScore("⌛  " + lobbyTimer.getTimerColor() + lobbyTimer.getCountdownTimer()).setScore(3);

        lobbyObjective.getScore("  ").setScore(2);

        return lobbyObjective;
    }

    public static Objective getArenaObjective(Scoreboard scoreboard, Player p) {
        Objective arenaObjective = scoreboard.registerNewObjective("tdm", "dummy", ChatColor.BOLD + "TDM         ");
        arenaObjective.setDisplaySlot(DisplaySlot.SIDEBAR);

        arenaObjective.getScore(" ").setScore(7);

        scoreboard.registerNewTeam("arenaScoreDisplay");
        Team gameModeDisplay = scoreboard.getTeam("arenaScoreDisplay");
        gameModeDisplay.setSuffix(ChatColor.BLUE + String.valueOf(Paintball.getGameScoreboard().getBlueTeamKills()) + ChatColor.WHITE + " - " + ChatColor.RED + Paintball.getGameScoreboard().getRedTeamKills());
        gameModeDisplay.addEntry("⚑  ");
        arenaObjective.getScore("⚑  ").setScore(6);

        // Score 5: "[teamScoreDisplay]"
        scoreboard.registerNewTeam("teamScoreDisplay");
        Team teamScoreDisplay = scoreboard.getTeam("teamScoreDisplay");
        teamScoreDisplay.setSuffix(ComponentHandler.getTeamComponent(p).toLegacyText());
        teamScoreDisplay.addEntry("⛨  ");
        arenaObjective.getScore("⛨  ").setScore(5);

        // Score 4: Game Starts:
        ArenaTimer arenaTimer = ArenaTimer.getArenaTimers().get(ArenaTimer.getArenaTimers().size() - 1);
        arenaObjective.getScore("⌛  " + arenaTimer.getTimerColor() + arenaTimer.getCountdownTimer()).setScore(4);

        arenaObjective.getScore("  ").setScore(3);

        arenaObjective.getScore("⚔  " + PlayerData.getKills(p)).setScore(2);
        arenaObjective.getScore("©  " + PlayerData.getCoins(p)).setScore(1);

        arenaObjective.getScore("   ").setScore(0);

        return arenaObjective;
    }

    public static Objective getEndObjective(Scoreboard scoreboard, Player p) {
        Objective endObjective = scoreboard.registerNewObjective("end", "dummy", ChatColor.BOLD + "END         ");
        endObjective.setDisplaySlot(DisplaySlot.SIDEBAR);

        endObjective.getScore(" ").setScore(7);

        // Score 4: Returning to lobby:
        EndTimer endTimer = EndTimer.getEndTimers().get(EndTimer.getEndTimers().size() - 1);
        endObjective.getScore("⌛  " + endTimer.getTimerColor() + endTimer.getCountdownTimer()).setScore(6);

        return endObjective;
    }



}
