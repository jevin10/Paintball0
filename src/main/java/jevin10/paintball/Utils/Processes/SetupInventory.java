package jevin10.paintball.Utils.Processes;

import jevin10.paintball.Utils.ColorTranslator;
import jevin10.paintball.Utils.ItemUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Responsible for setting up the inventory of players on instantiation or when changing states.
 */
public class SetupInventory {
    /**
     * Sets up the inventory for a player in the lobby.
     * @param p Player to change inventory of.
     */
    public static void lobby(Player p) {
        Inventory inventory = p.getInventory();
        ItemStack menu = ItemUtils.makeItem(Material.COMPASS, ChatColor.AQUA + "Menu", ChatColor.WHITE + "Open the Paintball menu.");
        ItemStack stats = ItemUtils.makeItem(Material.BOOK, ChatColor.AQUA + "Stats", ChatColor.WHITE + "View your Paintball stats.");

        for (ItemStack item : inventory.getContents()) {
            inventory.remove(item);
        }

        inventory.setItem(7, menu);
        inventory.setItem(8, stats);
    }
}
