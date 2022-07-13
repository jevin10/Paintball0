package jevin10.paintball.Runnables;

import jevin10.paintball.Paintball;
import jevin10.paintball.Utils.PlayerData;
import jevin10.paintball.Utils.Processes.SetupInventory;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;
import java.util.List;

public class LobbyTimer {
    int time;
    int taskID;
    static List<LobbyTimer> lobbyTimers = new ArrayList<>();

    public LobbyTimer(int time) {
        this.time = time;
        lobbyTimers.add(this);
    }

    public static List<LobbyTimer> getLobbyTimers() {
        return lobbyTimers;
    }

    /**
     * Starts the CountdownTimer
     */
    public void startTimer() {
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        taskID = scheduler.scheduleSyncRepeatingTask(Paintball.getPlugin(), new Runnable() {
            @Override
            public void run() {
                if(time == 0) {
                    if (!Paintball.getGameScoreboard().getGameInstance().equals("lobby")) {
                        stopTimer();
                        return;
                    }

                    for (Player player : Paintball.getGameScoreboard().getPlayers()) {
                        if (Paintball.getGameScoreboard().getScoreboard().getPlayerTeam(player).getName().equals("no")) {
                            player.sendMessage("You have been removed from the game because you didn't join the game in time.");
                            continue;
                        }
                        String playerTeam = Paintball.getGameScoreboard().getScoreboard().getPlayerTeam(player).getName();
                        if (playerTeam.equals("red")) {
                            Location location = new Location(Paintball.getPbWorld(), 3366.0, -60.0, 205.0, 90, -4);
                            player.teleport(location);
                        } else if (playerTeam.equals("blue")) {
                            Location location = new Location(Paintball.getPbWorld(),  3307.0, -60.0, 254.0, -90, -4);
                            player.teleport(location);
                        }
                        SetupInventory.arena(player);
                        PlayerData.resetPlayerData(player);
                    }

                    stopTimer();

                    Paintball.getGameScoreboard().setGameInstance("arena");

                    ArenaTimer arenaTimer = new ArenaTimer(600);
                    arenaTimer.startTimer();

                    return;
                }
                time--;
            }
        }, 0L, 20L);
    }

    public void stopTimer() {
        System.out.println("Stopping Lobby Timer");
        Bukkit.getScheduler().cancelTask(taskID);
    }

    public String getCountdownTimer() {
        int minutes = Math.floorDiv(time, 60);
        int seconds = time%60;
        if (Paintball.getGameScoreboard().getGameInstance().contains("arena")) {
            return ChatColor.AQUA + "IN-GAME";
        }
        if (seconds < 10) {
            return minutes + ":0" + seconds;
        } else {
            return minutes + ":" + seconds;
        }
    }

    public ChatColor getTimerColor() {
        if (time >= 60) {
            return ChatColor.GREEN;
        } else if (time > 10) {
            return ChatColor.GOLD;
        } else {
            return ChatColor.RED;
        }
    }
}