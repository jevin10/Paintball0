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

public class EndTimer {
    int time;
    int taskID;
    static List<EndTimer> endTimers = new ArrayList<>();

    public EndTimer(int time) {
        this.time = time;
        endTimers.add(this);
    }

    public static List<EndTimer> getEndTimers() {
        return endTimers;
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
                    if (!Paintball.getGameScoreboard().getGameInstance().equals("end")) {
                        stopTimer();
                        return;
                    }
                    for (Player player : Paintball.getGameScoreboard().getPlayers()) {

                        Location location = Paintball.getPlugin().getConfig().getLocation("lobbyLocation");
                        assert location != null : Bukkit.broadcast(Component.text("Set a lobby location with /paintball setLobby first!"));
                        SetupInventory.lobby(player);
                        player.teleport(location);

                    }
                    stopTimer();

                    Paintball.getGameScoreboard().setGameInstance("lobby");

                    LobbyTimer lobbyTimer = new LobbyTimer(60);
                    lobbyTimer.startTimer();

                    return;
                }
                time--;
            }
        }, 0L, 20L);
    }

    public void stopTimer() {
        System.out.println("Stopping End Timer");
        Bukkit.getScheduler().cancelTask(taskID);
    }

    public String getCountdownTimer() {
        int minutes = Math.floorDiv(time, 60);
        int seconds = time%60;
        if (Paintball.getGameScoreboard().getGameInstance().contains("lobby")) {
            return ChatColor.AQUA + "IN-LOBBY";
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