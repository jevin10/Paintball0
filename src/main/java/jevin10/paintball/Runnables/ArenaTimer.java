package jevin10.paintball.Runnables;

import jevin10.paintball.Paintball;
import jevin10.paintball.Utils.Processes.GameEvents;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitScheduler;

public class ArenaTimer {
    static int time;
    static int taskID;

    /**
     * Resets the CountdownTimer to the amount specified.
     * @param amount Integer in seconds that you'd like to set the countdown timer to.
     */
    public static void setTimer(int amount) {
        time = amount;
    }

    /**
     * Starts the CountdownTimer
     */
    public static void startTimer() {
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        taskID = scheduler.scheduleSyncRepeatingTask(Paintball.getPlugin(), new Runnable() {
            @Override
            public void run() {
                if(time == 0) {
                    GameEvents.gameTimeOut();
                    stopTimer();

                    Paintball.getGameScoreboard().setGameInstance("end");
                    return;
                }
                time--;
            }
        }, 0L, 20L);
    }

    public static void stopTimer() {
        Bukkit.getScheduler().cancelTask(taskID);
    }

    public static String getCountdownTimer() {
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

    public static ChatColor getTimerColor() {
        if (time >= 60) {
            return ChatColor.GREEN;
        } else if (time > 10) {
            return ChatColor.GOLD;
        } else {
            return ChatColor.RED;
        }
    }
}