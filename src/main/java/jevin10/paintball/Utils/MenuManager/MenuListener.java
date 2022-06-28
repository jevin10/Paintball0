package jevin10.paintball.Utils.MenuManager;

import jevin10.paintball.Exceptions.MenuManagerException;
import jevin10.paintball.Exceptions.MenuManagerNotSetupException;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;

public class MenuListener implements Listener {

    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {

        InventoryHolder holder = e.getInventory().getHolder();
        //If the inventoryholder of the inventory clicked on
        // is an instance of Menu, then gg. The reason that
        // an InventoryHolder can be a Menu is because our Menu
        // class implements InventoryHolder!!
        if (holder instanceof Menu) {
            if (e.getCurrentItem() == null) { //deal with null exceptions
                return;
            }
            //Since we know our inventoryholder is a menu, get the Menu Object representing
            // the menu we clicked on
            Menu menu = (Menu) holder;

            if (menu.cancelAllClicks()) {
                e.setCancelled(true);
            }

            //Call the handleMenu object which takes the event and processes it
            try {
                menu.handleMenu(e);
            } catch (MenuManagerNotSetupException menuManagerNotSetupException) {
                System.out.println(ChatColor.RED + "THE MENU MANAGER HAS NOT BEEN CONFIGURED. CALL MENUMANAGER.SETUP()");
            } catch (MenuManagerException menuManagerException) {
                menuManagerException.printStackTrace();
            }
        }

    }

}