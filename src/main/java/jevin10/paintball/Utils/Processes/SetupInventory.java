package jevin10.paintball.Utils.Processes;

import jevin10.paintball.Paintball;
import jevin10.paintball.Utils.ColorTranslator;
import jevin10.paintball.Utils.ItemUtils;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.CrossbowMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import static org.bukkit.Bukkit.getServer;
import static org.bukkit.Sound.UI_BUTTON_CLICK;

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

        PlayerInventory playerInventory = p.getInventory();
        try {
            playerInventory.setChestplate(null);
        } catch (Exception ignored) {}

        inventory.setItem(7, menu);
        inventory.setItem(8, stats);
    }

    /**
     * Clears player inventory, then gives players a paintball gun with a fresh PersistentDataContainer as well as ammo
     * @param p Player to affect inventory of
     */
    public static void arena(Player p) {
        Inventory inventory = p.getInventory();
        String playerTeam = Paintball.getGameScoreboard().getScoreboard().getPlayerTeam(p).getName();

        // Paintball gun
        ItemStack paintballGun = ItemUtils.makeItem(Material.CROSSBOW, ChatColor.WHITE + "Paintball Gun", ChatColor.WHITE + "A basic paintball gun.");
        CrossbowMeta cbMeta = (CrossbowMeta) paintballGun.getItemMeta();
        cbMeta.addChargedProjectile(ItemUtils.makeItem(Material.ARROW, ChatColor.WHITE + "6 DMG"));
        PersistentDataContainer paintballGunData = cbMeta.getPersistentDataContainer();
        paintballGunData.set(new NamespacedKey(Paintball.getPlugin(), "name"), PersistentDataType.STRING, "Paintball Gun");
        paintballGun.setItemMeta(cbMeta);

        // Paintball vest
        ItemStack paintballVest = ItemUtils.makeItem(Material.LEATHER_CHESTPLATE, ChatColor.WHITE + "Paintball Vest", ChatColor.WHITE + "A basic paintball vest.");

        switch(playerTeam) {
            case "red" -> {
                ItemMeta vestMeta = paintballVest.getItemMeta();
                vestMeta.setDisplayName(ChatColor.RED + "Paintball Vest");
                LeatherArmorMeta redVestMeta = (LeatherArmorMeta) vestMeta;
                redVestMeta.setColor(Color.RED);
                paintballVest.setItemMeta(redVestMeta);
            }
            case "blue" -> {
                ItemMeta vestMeta2 = paintballVest.getItemMeta();
                vestMeta2.setDisplayName(ChatColor.BLUE + "Paintball Vest");
                LeatherArmorMeta blueVestMeta = (LeatherArmorMeta) vestMeta2;
                blueVestMeta.setColor(Color.BLUE);
                paintballVest.setItemMeta(blueVestMeta);
            }
            case "none" -> {
                ItemMeta vestMeta3 = paintballVest.getItemMeta();
                vestMeta3.setDisplayName(ChatColor.WHITE + "Paintball Vest");
                LeatherArmorMeta noneVestMeta = (LeatherArmorMeta) vestMeta3;
                noneVestMeta.setColor(Color.WHITE);
                paintballVest.setItemMeta(noneVestMeta);
            }
        }

        // Paintball ammo
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
        PlayerInventory playerInventory = p.getInventory();
        playerInventory.setChestplate(paintballVest);
    }

    /**
     * Reloads a players inventory, changing their weapon slot to a reloading gun and their ammo into a copper ingot. (USE ONLY FOR Paintball Gun)
     * @param p Player to reload
     */
    public static void reloading(Player p) {
        Inventory inventory = p.getInventory();

        ItemStack reloadingPaintballGun = ItemUtils.makeItem(Material.CARROT_ON_A_STICK, ChatColor.WHITE + "*Reloading*", ChatColor.WHITE + "Reloading Paintball Gun...");
        ItemStack reloadingAmmo = ItemUtils.makeItem(Material.COPPER_INGOT, ChatColor.WHITE + "*Reloading*", ChatColor.WHITE + "Reloading Paintball Gun...");

        inventory.setItem(0, reloadingPaintballGun);
        inventory.setItem(1, reloadingAmmo);
        getServer().getScheduler().scheduleAsyncDelayedTask(Paintball.getPlugin(), new Runnable() {
            public void run() {
                p.playSound(p.getLocation(), UI_BUTTON_CLICK, 50, 30);
                getServer().getScheduler().scheduleAsyncDelayedTask(Paintball.getPlugin(), new Runnable() {
                    public void run() {
                        p.playSound(p.getLocation(), UI_BUTTON_CLICK, 50, 5);
                    }
                }, 3L);
                arena(p);
            }
        }, 17L);
    }
}
