package jevin10.paintball.BossBars;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class LobbyBossBar {
    static List<LobbyBossBar> lobbyBossBarList = new ArrayList<>();
    private int taskId = -1;
    private final Plugin plugin;
    private BossBar bossBar;

    public static List<LobbyBossBar> getLobbyBossBarList() {
        return lobbyBossBarList;
    }
    public LobbyBossBar(Plugin plugin) {
        this.plugin = plugin;
        lobbyBossBarList.add(this);
    }

    public void addPlayer(Player player) {
        bossBar.addPlayer(player);
    }

    public BossBar getBossBar() {
        return bossBar;
    }

    public void createBar() {
        bossBar = Bukkit.createBossBar("Paintball", BarColor.PURPLE, BarStyle.SOLID);
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
            double progress = 1.0;
            double time = 1.0 / 10;

            @Override
            public void run() {
                bossBar.setProgress(progress);

                switch (count) {
                    case -1 -> {}
                    case 0 -> {
                        bossBar.setColor(BarColor.PINK);
                        bossBar.setTitle("Learn more: www.metacraft.market");
                    }
                    default -> {
                        bossBar.setColor(BarColor.PURPLE);
                        bossBar.setTitle("Paintball");
                        count = -1;
                    }
                }

                progress = progress - time;
                if (progress <= 0) {
                    count++;
                    progress = 1.0;
                }
            }

        }, 0L, 20L);
    }

}
