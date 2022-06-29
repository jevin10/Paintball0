package jevin10.paintball.Runnables;

import jevin10.paintball.Paintball;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitScheduler;

public class CountdownTimer {
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
                    Bukkit.broadcastMessage(ChatColor.RED + "Time is up!");
                    stopTimer();
                    return;
                }
                time--;
            }
        }, 0L, 20L);
    }

    public static void stopTimer() {
        Bukkit.getScheduler().cancelTask(taskID);
    }

    public static int getTime() {
        return time;
    }
}