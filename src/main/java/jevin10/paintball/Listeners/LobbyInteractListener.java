package jevin10.paintball.Listeners;

import jevin10.paintball.Exceptions.MenuManagerException;
import jevin10.paintball.Exceptions.MenuManagerNotSetupException;
import jevin10.paintball.Menus.ChooseTeamMenu;
import jevin10.paintball.Paintball;
import jevin10.paintball.Utils.MenuManager.Menu;
import jevin10.paintball.Utils.MenuManager.MenuManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class LobbyInteractListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) throws MenuManagerException, MenuManagerNotSetupException {
        if (Paintball.getPbWorld() == null) {
            return;
        }
        if (event.getPlayer().getWorld() != Paintball.getPbWorld()) {
            return;
        }
        if (!Paintball.getGameScoreboard().getGameInstance().equals("lobby")) {
            return;
        }

        if (event.getItem().getType() == Material.COMPASS) {
            MenuManager.openMenu(ChooseTeamMenu.class, event.getPlayer());
        } else if (event.getItem().getType() == Material.BOOK) {
            event.getPlayer().sendMessage(ChatColor.RED + "Player stats is a work in progress. Check back later!");
        }
    }
}
