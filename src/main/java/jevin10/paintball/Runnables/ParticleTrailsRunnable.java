package jevin10.paintball.Runnables;

import jevin10.paintball.ComponentHandler;
import jevin10.paintball.Paintball;
import jevin10.paintball.Scoreboards.GameScoreboard;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.Particle;
import org.bukkit.entity.Projectile;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;
import xyz.xenondevs.particle.data.color.RegularColor;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class ParticleTrailsRunnable extends BukkitRunnable {

    static Set<Projectile> snowballs = new HashSet<>();

    @Override
    public void run() {
        try {
            for (Projectile snowball : snowballs) {
                if(!snowball.isValid()) {
                    snowballs.remove(snowball);
                } else {
                    Location location = snowball.getLocation();
                    String team = Paintball.getGameScoreboard().getScoreboard().getPlayerTeam((OfflinePlayer) snowball.getShooter()).getName();
                    switch (team) {
                        case "red" -> new ParticleBuilder(ParticleEffect.REDSTONE, location)
                                .setParticleData(new RegularColor(255, 0, 0))
                                .display();
                        case "blue" -> new ParticleBuilder(ParticleEffect.REDSTONE, location)
                                .setParticleData(new RegularColor(0, 0, 255))
                                .display();
                        case "no" -> new ParticleBuilder(ParticleEffect.REDSTONE, location)
                                .setParticleData(new RegularColor(255, 255, 0))
                                .display();
                    }
                }
            }
        } catch (Exception ignored) {
        }

    }

    public static Set<Projectile> getSnowballs() {
        return snowballs;
    }
}
