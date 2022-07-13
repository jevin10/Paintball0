package jevin10.paintball;

import jevin10.paintball.BossBars.ArenaBossBar;
import jevin10.paintball.BossBars.LobbyBossBar;
import jevin10.paintball.Listeners.*;
import jevin10.paintball.Utils.MenuManager.MenuManager;
import jevin10.paintball.Runnables.TeamComponentRunnable;
import jevin10.paintball.Scoreboards.GameScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;


public final class Paintball extends JavaPlugin {

    static Plugin plugin;
    static World pbWorld = null;

    static GameScoreboard gameScoreboard = null;

    public LobbyBossBar lobbyBossBar;

    @Override
    public void onEnable() {

        plugin = this;

        // Plugin startup logic
        getCommand("paintball").setExecutor(new PaintballCommand());
        getConfig().options().copyDefaults();


        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerChangedWorldListener(), this);
        getServer().getPluginManager().registerEvents(new LobbyInteractListener(), this);
        getServer().getPluginManager().registerEvents(new ArenaInteractListener(), this);
        getServer().getPluginManager().registerEvents(new PaintballHitListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerRespawnListener(), this);


        // Setup MenuManager
        MenuManager.setup(this.getServer(), plugin);

        BukkitTask teamComponentRunnable = new TeamComponentRunnable().runTaskTimer(plugin, 0L, 10L);

        // Setup GameScoreboard
        gameScoreboard = new GameScoreboard();

        // Setup LobbyBossBar
        lobbyBossBar = new LobbyBossBar(plugin);
        lobbyBossBar.createBar();


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        lobbyBossBar.getBossBar().removeAll();
        ArenaBossBar arenaBossBar = ArenaBossBar.getArenaBossBarList().get(ArenaBossBar.getArenaBossBarList().size() - 1);
        arenaBossBar.getBossBar().removeAll();
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
