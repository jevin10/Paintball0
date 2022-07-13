package jevin10.paintball.Menus;

import jevin10.paintball.ComponentHandler;
import jevin10.paintball.Exceptions.MenuManagerException;
import jevin10.paintball.Exceptions.MenuManagerNotSetupException;
import jevin10.paintball.Paintball;
import jevin10.paintball.Utils.MenuManager.Menu;
import jevin10.paintball.Utils.MenuManager.MenuManager;
import jevin10.paintball.Utils.MenuManager.PlayerMenuUtility;
import jevin10.paintball.Utils.Processes.SetupInventory;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ChooseTeamMenu extends Menu {

    public ChooseTeamMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "Choose a team";
    }

    @Override
    public int getSlots() {
        return 9;
    }

    @Override
    public boolean cancelAllClicks() {
        return true;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) throws MenuManagerNotSetupException, MenuManagerException {
        Player p = (Player) e.getWhoClicked();
        if (Paintball.getGameScoreboard().getGameInstance().equals("end")) {
            p.sendMessage("You cannot join a team in the end game!");
            return;
        }
        switch (e.getCurrentItem().getType()) {
            case BLUE_WOOL -> {
                Paintball.getGameScoreboard().addPlayerToTeam("blue", p);
                if (Paintball.getGameScoreboard().getGameInstance().equals("arena")) {
                    SetupInventory.arena(p);
                    p.teleport(new Location(Paintball.getPbWorld(), 3307.0, -60.0, 254.0, -90, -4));
                }
            }
            case RED_WOOL -> {
                Paintball.getGameScoreboard().addPlayerToTeam("red", p);
                if (Paintball.getGameScoreboard().getGameInstance().equals("arena")) {
                    SetupInventory.arena(p);
                    p.teleport(new Location(Paintball.getPbWorld(), 3366.0, -60.0, 205.0, 90, -4));
                }
            }
        }
        p.closeInventory();
    }

    @Override
    public void setMenuItems() {
        ItemStack chooseTeamBlue = makeItem(Material.BLUE_WOOL, ComponentHandler.getBlueTeamComponent().toLegacyText(), "&#ffffffJoin blue team!", " ", "&#ffffff☻ " + Paintball.getGameScoreboard().getTeamPlayers("blue").size());
        ItemStack chooseTeamRed = makeItem(Material.RED_WOOL, ComponentHandler.getRedTeamComponent().toLegacyText(), "&#ffffffJoin red team!", " ", "&#ffffff☻ " + Paintball.getGameScoreboard().getTeamPlayers("red").size());

        inventory.setItem(3, chooseTeamBlue);
        inventory.setItem(5, chooseTeamRed);
    }
}
