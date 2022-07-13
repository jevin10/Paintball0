package jevin10.paintball.Runnables;

import jevin10.paintball.Paintball;
import jevin10.paintball.Utils.Processes.GameEvents;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;
import java.util.List;

public class ArenaTimer {
    int time;
    int taskID;
    static List<ArenaTimer> arenaTimers = new ArrayList<>();

    public ArenaTimer(int time) {
        this.time = time;
        arenaTimers.add(this);
    }

    public static List<ArenaTimer> getArenaTimers() {
        return arenaTimers;
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
                    if (!Paintball.getGameScoreboard().getGameInstance().equals("arena")) {
                        stopTimer();
                        return;
                    }
                    GameEvents.gameTimeOut();
                    stopTimer();
                    Paintball.getGameScoreboard().setGameInstance("end");
                }
                time--;
            }
        }, 0L, 20L);
    }

    public void stopTimer() {
        System.out.println("Stopping Arena Timer");
        Bukkit.getScheduler().cancelTask(taskID);
    }

    public String getCountdownTimer() {
        int minutes = Math.floorDiv(time, 60);
        int seconds = time%60;
        if (Paintball.getGameScoreboard().getGameInstance().contains("end")) {
            return ChatColor.AQUA + "GAME-END";
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