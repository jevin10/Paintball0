package jevin10.paintball.Scoreboards;

import jevin10.paintball.ComponentStuff;
import jevin10.paintball.Utils.ColorTranslator;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class TestScoreboard {

    public static void newTest(Player p) {

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getNewScoreboard();

        Objective objective = scoreboard.registerNewObjective("test", "dummy", "Spaghetti");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        scoreboard.registerNewTeam("test");
        Team team = scoreboard.getTeam("test");
        team.setSuffix(ComponentStuff.getBlueTeamComponent().toLegacyText());
        team.addEntry(ChatColor.translateAlternateColorCodes('&', "&a"));

        objective.getScore(ChatColor.translateAlternateColorCodes('&', "&a")).setScore(1);

        p.setScoreboard(scoreboard);

    }

}
