package jevin10.paintball;

import jevin10.paintball.Exceptions.MenuManagerException;
import jevin10.paintball.Exceptions.MenuManagerNotSetupException;
import jevin10.paintball.Menus.ChooseTeamMenu;
import jevin10.paintball.Menus.ChooseTeamMenuRunnable;
import jevin10.paintball.Runnables.CountdownTimer;
import jevin10.paintball.Runnables.ScoreboardRunnable;
import jevin10.paintball.Scoreboards.BossBars.LobbyBossBar;
import jevin10.paintball.Utils.MenuManager.MenuManager;
import jevin10.paintball.Utils.Processes.SetupInventory;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Location;
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

            if (args.length == 0) {
                if (!Paintball.getGameScoreboard().getGameInstance().equals("null")) {
                    p.sendMessage(ChatColor.RED + "A game of Paintball already exists!");
                    return true;
                }

                // Register pbWorld as the game world
                Paintball.setPbWorld(p.getWorld());
                Paintball.getGameScoreboard().setGameInstance("lobby");

                // set up players
                for (Player player : Paintball.getPbWorld().getPlayers()) {
                    LobbyBossBar.showMyBossBar(player);
                    Paintball.getGameScoreboard().addPlayerToTeam("no", player);
                    SetupInventory.lobby(player);

                    try {
                        MenuManager.openMenu(ChooseTeamMenu.class, player);
                        BukkitTask updateChooseTeamMenu = new ChooseTeamMenuRunnable(player).runTaskTimer(Paintball.getPlugin(), 0L, 10L);
                    } catch (MenuManagerException | MenuManagerNotSetupException e) {
                        e.printStackTrace();
                    }

                    BukkitTask scoreboardRunnable = new ScoreboardRunnable(player).runTaskTimer(plugin, 0L, 10L);
                }

                CountdownTimer.setTimer(30);
                CountdownTimer.startTimer();

                return true;
            }

            if (args[0].equals("setLobby")) {
                Location location = p.getLocation();
                if (Paintball.getPlugin().getConfig().getLocation("lobbyLocation") != null) {
                    p.sendMessage(Component.text("Overriding previous location..."));
                }
                Paintball.getPlugin().getConfig().set("lobbyLocation", location);
                Paintball.getPlugin().saveConfig();
                p.sendMessage(Component.text("Location set!"));
                return true;
            }


        }
        return true;
    }
}
