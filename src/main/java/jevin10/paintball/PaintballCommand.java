package jevin10.paintball;

import jevin10.paintball.Exceptions.MenuManagerException;
import jevin10.paintball.Exceptions.MenuManagerNotSetupException;
import jevin10.paintball.Menus.ChooseTeamMenu;
import jevin10.paintball.Menus.ChooseTeamMenuRunnable;
import jevin10.paintball.Runnables.CountdownTimer;
import jevin10.paintball.Runnables.LobbyAnnouncerRunnable;
import jevin10.paintball.Runnables.ScoreboardRunnable;
import jevin10.paintball.Scoreboards.BossBars;
import jevin10.paintball.Utils.MenuManager.MenuManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

import static jevin10.paintball.Paintball.plugin;

public class PaintballCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player p) {
            // toLegacyText is IMPORTANT for scoreboards.

            // Register pbWorld as the game world
            Paintball.setPbWorld(p.getWorld());
            BossBars.showMyBossBar(p);

            Paintball.getGameScoreboard().addPlayerToTeam("no", p);

            CountdownTimer.setTimer(120);
            CountdownTimer.startTimer();

            try {
                MenuManager.openMenu(ChooseTeamMenu.class, p);
                BukkitTask updateChooseTeamMenu = new ChooseTeamMenuRunnable(p).runTaskTimer(Paintball.getPlugin(), 0L, 10L);
            } catch (MenuManagerException | MenuManagerNotSetupException e) {
                e.printStackTrace();
            }

            BukkitTask scoreboardRunnable = new ScoreboardRunnable(p).runTaskTimer(plugin, 0L, 10L);
        }
        return true;
    }
}
