package jevin10.paintball.Listeners;

import jevin10.paintball.Exceptions.MenuManagerException;
import jevin10.paintball.Exceptions.MenuManagerNotSetupException;
import jevin10.paintball.Menus.ChooseTeamMenu;
import jevin10.paintball.Paintball;
import jevin10.paintball.Runnables.ScoreboardRunnable;
import jevin10.paintball.Utils.MenuManager.MenuManager;
import jevin10.paintball.Utils.Processes.SetupInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitTask;

/**
 * A listener that handles a player the moment they join the game.
 */

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws MenuManagerException, MenuManagerNotSetupException {
        Player p = event.getPlayer();
        // cancel if player is not online
        if (Paintball.getPbWorld() == null) {
            return;
        }
        if (p.getWorld() == Paintball.getPbWorld()) {
            if (Paintball.getGameScoreboard().getGameInstance().equals("lobby") || Paintball.getGameScoreboard().getGameInstance().equals("end")) {
                p.teleport(Paintball.getPlugin().getConfig().getLocation("lobbyLocation"));
                SetupInventory.lobby(p);
            }
            if (Paintball.getGameScoreboard().getGameInstance().equals("arena")) {
                Paintball.getGameScoreboard().addPlayerToTeam("no", p);
                p.teleport(Paintball.getPlugin().getConfig().getLocation("lobbyLocation"));
                MenuManager.openMenu(ChooseTeamMenu.class, p);
                SetupInventory.lobby(p);
            }
            if(!Paintball.getGameScoreboard().getPlayers().contains(p)) {
                Paintball.getGameScoreboard().addPlayerToTeam("no", p);
                MenuManager.openMenu(ChooseTeamMenu.class, p);
            }
            BukkitTask scoreboardRunnable = new ScoreboardRunnable(p).runTaskTimer(Paintball.getPlugin(), 0L, 10L);
        }
    }
}
