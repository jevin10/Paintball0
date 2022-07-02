package jevin10.paintball.Listeners;

import com.destroystokyo.paper.event.entity.ProjectileCollideEvent;
import jevin10.paintball.Paintball;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;
import xyz.xenondevs.particle.data.color.RegularColor;
import xyz.xenondevs.particle.data.texture.ItemTexture;

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

    @EventHandler
    public void onSnowballHitBlock(ProjectileHitEvent event) {
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
