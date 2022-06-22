package jevin10.paintball;

import jevin10.paintball.Runnables.TeamComponentRunnable;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;


public final class Paintball extends JavaPlugin {

    static Plugin plugin;
    static World pbWorld = null;

    @Override
    public void onEnable() {

        plugin = this;

        // Plugin startup logic
        getCommand("paintball").setExecutor(new PaintballCommand());
        BukkitTask teamComponentRunnable = new TeamComponentRunnable().runTaskTimer(plugin, 20L, 20L);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    public static World getPbWorld() {
        return pbWorld;
    }

    public static void setPbWorld(World pbWorld) {
        Paintball.pbWorld = pbWorld;
    }
}
