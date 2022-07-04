package jevin10.paintball.Listeners;

import com.destroystokyo.paper.event.entity.ProjectileCollideEvent;
import jevin10.paintball.Paintball;
import jevin10.paintball.Utils.PlayerData;
import jevin10.paintball.Utils.Processes.GameEvents;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;
import xyz.xenondevs.particle.data.color.RegularColor;
import xyz.xenondevs.particle.data.texture.ItemTexture;

/**
 * A listener that handles a snowball the moment it hits a player, making it break.
 */
public class PaintballHitListener implements Listener {
    @EventHandler
    public void onSnowballCollideEntity(ProjectileCollideEvent event) {
        if (!event.getEntity().getType().equals(EntityType.SNOWBALL)) {
            return;
        }
        Location location = event.getEntity().getLocation();
        if (!location.getWorld().equals(Paintball.getPbWorld())) {
            return;
        }
        String team = Paintball.getGameScoreboard().getScoreboard().getPlayerTeam((OfflinePlayer) event.getEntity().getShooter()).getName();
        switch (team) {
            case "red" -> redBreak(location);
            case "blue" -> blueBreak(location);
            case "no" -> noBreak(location);
        }
    }

    /**
     * The breaking animation of a snowball.
     * @param event The event that caused the snowball to break.
     */
    @EventHandler
    public void onSnowballHitBlock(ProjectileHitEvent event) {
        if (event.getHitEntity() == null) {
            return;
        }
        if (!event.getEntity().getType().equals(EntityType.SNOWBALL)) {
            return;
        }
        Location location = event.getEntity().getLocation();
        if (!location.getWorld().equals(Paintball.getPbWorld())) {
            return;
        }
        String team = Paintball.getGameScoreboard().getScoreboard().getPlayerTeam((OfflinePlayer) event.getEntity().getShooter()).getName();
        switch (team) {
            case "red" -> redBreak(location);
            case "blue" -> blueBreak(location);
            case "no" -> noBreak(location);
        }
    }

    /**
     * Handles the snowball damage on projectile hit.
     * @param event The projectile hit event.
     */
    @EventHandler
    public void onSnowballHitPlayer(ProjectileHitEvent event) {
        if (event.getHitEntity() == null) {
            return;
        }
        if (!event.getEntity().getType().equals(EntityType.SNOWBALL)) {
            return;
        }
        if (!event.getHitEntity().getType().equals(EntityType.PLAYER)) {
            return;
        }
        Location location = event.getEntity().getLocation();
        if (!location.getWorld().equals(Paintball.getPbWorld())) {
            return;
        }
        String hitPlayerTeam = Paintball.getGameScoreboard().getScoreboard().getPlayerTeam((OfflinePlayer) event.getEntity().getShooter()).getName();
        String shooterTeam = Paintball.getGameScoreboard().getScoreboard().getPlayerTeam((OfflinePlayer) event.getHitEntity()).getName();
        if (hitPlayerTeam.equals(shooterTeam)) {
            System.out.println("Same team");
            event.setCancelled(true);
        } else {
            Player hitPlayer = (Player) event.getHitEntity();
            hitPlayer.damage(6);
            if (hitPlayer.getHealth() <= 0) {
                GameEvents.playerKill(hitPlayer, (Player) event.getEntity().getShooter());
            }
            event.setCancelled(false);
        }
    }

    public void redBreak(Location location) {
        new ParticleBuilder(ParticleEffect.ITEM_CRACK, location)
                .setParticleData(new ItemTexture(new ItemStack(Material.REDSTONE_BLOCK)))
                .display();
        new ParticleBuilder(ParticleEffect.REDSTONE, location)
                .setParticleData(new RegularColor(255, 0, 0))
                .display();
        new ParticleBuilder(ParticleEffect.ITEM_CRACK, location)
                .setParticleData(new ItemTexture(new ItemStack(Material.REDSTONE_BLOCK)))
                .display();
    }
    public void blueBreak(Location location) {
        new ParticleBuilder(ParticleEffect.ITEM_CRACK, location)
                .setParticleData(new ItemTexture(new ItemStack(Material.LAPIS_BLOCK)))
                .display();
        new ParticleBuilder(ParticleEffect.REDSTONE, location)
                .setParticleData(new RegularColor(0, 0, 255))
                .display();
        new ParticleBuilder(ParticleEffect.ITEM_CRACK, location)
                .setParticleData(new ItemTexture(new ItemStack(Material.LAPIS_BLOCK)))
                .display();

    }
    public void noBreak(Location location) {
        new ParticleBuilder(ParticleEffect.ITEM_CRACK, location)
                .setParticleData(new ItemTexture(new ItemStack(Material.SPONGE)))
                .display();
        new ParticleBuilder(ParticleEffect.REDSTONE, location)
                .setParticleData(new RegularColor(255, 255, 0))
                .display();
        new ParticleBuilder(ParticleEffect.ITEM_CRACK, location)
                .setParticleData(new ItemTexture(new ItemStack(Material.SPONGE)))
                .display();
    }
}
