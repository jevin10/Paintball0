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
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * All listeners pertaining to the lobby.
 */
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

    /**
     * Cancels inventory click events in the lobby for relevant items.
     * @param event The click event
     */
    @EventHandler
    public void onPlayerClick(InventoryClickEvent event, InventoryPickupItemEvent event2) {
        if (Paintball.getPbWorld() == null) {
            return;
        }
        if (event.getWhoClicked().getWorld() != Paintball.getPbWorld()) {
            return;
        }
        if (!Paintball.getGameScoreboard().getGameInstance().equals("lobby")) {
            return;
        }

        if (event.getCurrentItem().getType() == Material.COMPASS | event.getCurrentItem().getType() == Material.BOOK) {
            event.setCancelled(true);
            event2.setCancelled(true);
        }
    }
    /**
     * Cancels inventory drag events in the lobby for relevant items.
     * @param event The drag event
     */
    @EventHandler
    public void onPlayerClick(InventoryDragEvent event) {
        if (Paintball.getPbWorld() == null) {
            return;
        }
        if (event.getWhoClicked().getWorld() != Paintball.getPbWorld()) {
            return;
        }
        if (!Paintball.getGameScoreboard().getGameInstance().equals("lobby")) {
            return;
        }

        if (event.getCursor().getType() == Material.COMPASS | event.getCursor().getType() == Material.BOOK) {
            event.setCancelled(true);
        }
    }
}
