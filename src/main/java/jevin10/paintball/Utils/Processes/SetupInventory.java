package jevin10.paintball.Utils.Processes;

import jevin10.paintball.Paintball;
import jevin10.paintball.Utils.ColorTranslator;
import jevin10.paintball.Utils.ItemUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CrossbowMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

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
            try {
                assert item != null;
                inventory.remove(item);
            } catch (Exception ignored) {
            }
        }

        inventory.setItem(7, menu);
        inventory.setItem(8, stats);
    }

    /**
     * Clears player inventory, then gives players a paintball gun with a fresh PersistentDataContainer as well as ammo
     * @param p Player to affect inventory of
     */
    public static void arena(Player p) {
        Inventory inventory = p.getInventory();

        ItemStack paintballGun = ItemUtils.makeItem(Material.CROSSBOW, ChatColor.WHITE + "Paintball Gun", ChatColor.WHITE + "A basic paintball gun.");
        CrossbowMeta cbMeta = (CrossbowMeta) paintballGun.getItemMeta();
        cbMeta.addChargedProjectile(ItemUtils.makeItem(Material.ARROW, ChatColor.WHITE + "6 DMG"));
        PersistentDataContainer paintballGunData = cbMeta.getPersistentDataContainer();
        paintballGunData.set(new NamespacedKey(Paintball.getPlugin(), "name"), PersistentDataType.STRING, "Paintball Gun");
        paintballGun.setItemMeta(cbMeta);

        ItemStack ammo = ItemUtils.makeItem(Material.IRON_INGOT, ChatColor.WHITE + "Ammo", ChatColor.WHITE + "Your ammo.");
        ammo.setAmount(32);

        for (ItemStack item : inventory.getContents()) {
            try {
                assert item != null;
                inventory.remove(item);
            } catch (Exception ignored) {
            }
        }

        inventory.setItem(0, paintballGun);
        inventory.setItem(1, ammo);
    }
}
