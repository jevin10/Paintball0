package jevin10.paintball.Listeners;

import jevin10.paintball.Paintball;
import jevin10.paintball.Utils.AmountModifiers;
import jevin10.paintball.Utils.Processes.SetupInventory;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

import static org.bukkit.Sound.ENTITY_SNOWBALL_THROW;

public class ArenaInteractListener implements Listener {
    /**
     * Deals with players interacting with a loaded paintball gun in-hand.
     */
    @EventHandler
    public void onPlayerInteractPaintballGun(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        // return statements
        if (Paintball.getPbWorld() == null) {
            return;
        }
        if (p.getWorld() != Paintball.getPbWorld()) {
            return;
        }
        if (!event.hasItem()) {
            return;
        }
        if (!event.getItem().hasItemMeta()) {
            return;
        }

        if (event.getItem().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Paintball.getPlugin(), "name"))) {
            PersistentDataContainer paintballGunData = event.getItem().getItemMeta().getPersistentDataContainer();
            if (Objects.equals(paintballGunData.get(new NamespacedKey(Paintball.getPlugin(), "name"), PersistentDataType.STRING), "Paintball Gun")) {
                if(!AmountModifiers.itemHas(p.getInventory(), Material.IRON_INGOT)) {
                    // reload
                    SetupInventory.reloading(p);
                    event.setCancelled(true);
                    return;
                }

                p.playSound(p.getLocation(), ENTITY_SNOWBALL_THROW, 20, 100);
                Location location = getNewLocation(p);
                Snowball snowball = p.getWorld().spawn(location, Snowball.class);
                snowball.setShooter(p);
                snowball.setVelocity(p.getEyeLocation().getDirection().multiply(2.0));

                AmountModifiers.removeItems(p.getInventory(), Material.IRON_INGOT, 1);
            }
            event.setCancelled(true);
        }

    }
    public static Location getNewLocation(Player player) {
        return new Location(player.getWorld(), player.getEyeLocation().getX(), player.getEyeLocation().getY() - 0.5, player.getEyeLocation().getZ());
    }
    /**
     * Cancels inventory click events in the arena for relevant items.
     * @param event The click event
     */
    @EventHandler
    public void onPlayerClick(InventoryClickEvent event) {
        //return statements
        if (Paintball.getPbWorld() == null) {
            return;
        }
        if (event.getWhoClicked().getWorld() != Paintball.getPbWorld()) {
            return;
        }
        if (!Paintball.getGameScoreboard().getGameInstance().equals("arena")) {
            return;
        }
        if (event.getCurrentItem() == null) {
            return;
        }

        event.setCancelled(true);
    }
    /**
     * Cancels inventory drag events in the arena for relevant items.
     * @param event The drag event
     */
    @EventHandler
    public void onPlayerClick(InventoryDragEvent event) {
        // return statements
        if (Paintball.getPbWorld() == null) {
            return;
        }
        if (event.getWhoClicked().getWorld() != Paintball.getPbWorld()) {
            return;
        }
        if (!Paintball.getGameScoreboard().getGameInstance().equals("arena")) {
            return;
        }
        event.setCancelled(true);
    }
    @EventHandler
    public void onPlayerPickup(InventoryMoveItemEvent event) {
        // return statements
        if (Paintball.getPbWorld() == null) {
            return;
        }
        if (!Paintball.getGameScoreboard().getGameInstance().equals("arena")) {
            return;
        }
        event.setCancelled(true);
    }
}
