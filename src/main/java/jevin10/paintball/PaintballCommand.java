package jevin10.paintball;

import jevin10.paintball.Runnables.ScoreboardRunnable;
import jevin10.paintball.Runnables.TeamComponentRunnable;
import jevin10.paintball.Scoreboards.TestScoreboard;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.DisplaySlot;
import org.jetbrains.annotations.NotNull;

import static jevin10.paintball.Paintball.plugin;

public class PaintballCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player p) {
            // toLegacyText is IMPORTANT for scoreboards.
            BukkitTask teamComponentRunnable = new ScoreboardRunnable(p).runTaskTimer(plugin, 20L, 20L);
        }
        return true;
    }
}
