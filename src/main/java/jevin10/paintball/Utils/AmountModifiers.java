package jevin10.paintball.Utils;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Modifies the amount of a certain item
 */
public class AmountModifiers {
    /**
     * Removes a certain item from a player's inventory
     * @param inventory Inventory to remove the item from
     * @param type Material of the item to be removed
     * @param amount Amount of that item to be removed
     */
    public static void removeItems(Inventory inventory, Material type, int amount) {
        if (amount <= 0) return;
        int size = inventory.getSize();
        for (int slot = 0; slot < size; slot++) {
            ItemStack is = inventory.getItem(slot);
            if (is == null) continue;
            if (type == is.getType()) {
                int newAmount = is.getAmount() - amount;
                if (newAmount > 0) {
                    is.setAmount(newAmount);
                    break;
                } else {
                    inventory.clear(slot);
                    amount = -newAmount;
                    if (amount == 0) break;
                }
            }
        }
    }

    /**
     * Get whether there is a certain material from an inventory
     * @param inventory Inventory to read
     * @param type Material of the item to read
     * @return  Boolean of whether there is at least 1 amount of material in the specified inventory
     */
    public static boolean itemHas(Inventory inventory, Material type) {
        int size = inventory.getSize();
        for (int slot = 0; slot < size; slot++) {
            ItemStack is = inventory.getItem(slot);
            if (is == null) continue;
            if (type == is.getType()) {
                int amount = is.getAmount();
                return amount > 0;
            }
        }
        return false;
    }

    /**
     * Get the amount of items in an inventory
     * @param inventory Inventory to read
     * @param type Material of item to read
     * @return Integer amount of the items in the specified inventory
     */
    public static int totalItemHas(Inventory inventory, Material type) {
        int size = inventory.getSize();
        int amount = 0;
        for (int slot = 0; slot < size; slot++) {
            ItemStack is = inventory.getItem(slot);
            if (is == null) continue;
            if (type == is.getType()) {
                amount = amount + is.getAmount();
            }
        }
        return amount;
    }
}
