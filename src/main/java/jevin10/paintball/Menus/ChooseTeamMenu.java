package jevin10.paintball.Menus;

import jevin10.paintball.ComponentHandler;
import jevin10.paintball.Exceptions.MenuManagerException;
import jevin10.paintball.Exceptions.MenuManagerNotSetupException;
import jevin10.paintball.Utils.MenuManager.Menu;
import jevin10.paintball.Utils.MenuManager.PlayerMenuUtility;
import org.bukkit.Material;
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

    }

    @Override
    public void setMenuItems() {
        ItemStack chooseTeamBlue = makeItem(Material.BLUE_WOOL, ComponentHandler.getBlueTeamComponent().toLegacyText(), "Choose blue team!");

        inventory.setItem(3, chooseTeamBlue);
    }
}
