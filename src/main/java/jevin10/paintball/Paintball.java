package jevin10.paintball;

import jevin10.paintball.Listeners.LobbyInteractListener;
import jevin10.paintball.Listeners.PlayerChangedWorldListener;
import jevin10.paintball.Listeners.PlayerJoinListener;
import jevin10.paintball.Utils.MenuManager.MenuManager;
import jevin10.paintball.Runnables.LobbyAnnouncerRunnable;
import jevin10.paintball.Runnables.TeamComponentRunnable;
import jevin10.paintball.Scoreboards.GameScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;


public final class Paintball extends JavaPlugin {

    static Plugin plugin;
    static World pbWorld = null;

    static GameScoreboard gameScoreboard = new GameScoreboard();

    @Override
    public void onEnable() {

        plugin = this;

        // Plugin startup logic
        getCommand("paintball").setExecutor(new PaintballCommand());


        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerChangedWorldListener(), this);
        getServer().getPluginManager().registerEvents(new LobbyInteractListener(), this);

        // Setup MenuManager
        MenuManager.setup(this.getServer(), plugin);

        BukkitTask teamComponentRunnable = new TeamComponentRunnable().runTaskTimer(plugin, 0L, 10L);

        BukkitTask lobbyAnnouncerRunnable1 = new LobbyAnnouncerRunnable().runTaskTimer(plugin, 0L, 2L);

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

    public static GameScoreboard getGameScoreboard() {
        return gameScoreboard;
    }
}
