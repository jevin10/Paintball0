package jevin10.paintball.BossBars;

import jevin10.paintball.Paintball;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class ArenaBossBar {
    static List<ArenaBossBar> arenaBossBarList = new ArrayList<>();
    private int taskId = -1;
    private final Plugin plugin;
    private BossBar bossBar;

    public static List<ArenaBossBar> getArenaBossBarList() {
        return arenaBossBarList;
    }

    public ArenaBossBar(Plugin plugin) {
        this.plugin = plugin;
        arenaBossBarList.add(this);
    }

    public void addPlayer(Player player) {
        bossBar.addPlayer(player);
    }

    public BossBar getBossBar() {
        return bossBar;
    }

    public void createBar() {
        bossBar = Bukkit.createBossBar("Gamemode: TDM", BarColor.GREEN, BarStyle.SOLID);
        bossBar.setVisible(true);
        cast();
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public void cast() {
        taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

            int count = -1;
            double timeProgress = 1.0;
            double time = 1.0 / 10;

            @Override
            public void run() {

                switch (count) {
                    case -1 -> {}
                    case 0 -> {
                        bossBar.setColor(BarColor.BLUE);
                        bossBar.setTitle("⚔ Blue Kills: " + ChatColor.BLUE + Paintball.getGameScoreboard().getBlueTeamKills());
                        bossBar.setProgress((double) Paintball.getGameScoreboard().getBlueTeamKills() / Paintball.getGameScoreboard().getMaxKills());
                    }
                    case 1 -> {
                        bossBar.setColor(BarColor.RED);
                        bossBar.setTitle("⚔ Red Kills: " + ChatColor.RED + Paintball.getGameScoreboard().getRedTeamKills());
                        bossBar.setProgress((double) Paintball.getGameScoreboard().getRedTeamKills() / Paintball.getGameScoreboard().getMaxKills());
                    }
                    default -> {
                        bossBar.setColor(BarColor.GREEN);
                        bossBar.setTitle("Gamemode: TDM");
                        bossBar.setProgress(timeProgress);
                        count = -1;
                    }
                }

                timeProgress = timeProgress - time;
                if (timeProgress <= 0) {
                    count++;
                    timeProgress = 1.0;
                }
            }

        }, 0L, 20L);
    }
}
