package jevin10.paintball.Scoreboards;

import jevin10.paintball.Runnables.LobbyAnnouncerRunnable;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import org.checkerframework.checker.nullness.qual.NonNull;

import javax.annotation.Nullable;

public class BossBars {
    private static @Nullable BossBar activeBar;
    public static void showMyBossBar(final @NonNull Audience target) {
        final Component name = Component.text("You're Playing Paintball!");
        // Creates a red boss bar which has no progress and no notches
        final BossBar a_0 = BossBar.bossBar(name, 0.2F, BossBar.Color.RED, BossBar.Overlay.PROGRESS);
        final BossBar a_1 = BossBar.bossBar(name, 0.4F, BossBar.Color.RED, BossBar.Overlay.PROGRESS);
        final BossBar a_2 = BossBar.bossBar(name, 0.6F, BossBar.Color.RED, BossBar.Overlay.PROGRESS);
        final BossBar a_3 = BossBar.bossBar(name, 0.8F, BossBar.Color.RED, BossBar.Overlay.PROGRESS);
        final BossBar a_4 = BossBar.bossBar(name, 1F, BossBar.Color.RED, BossBar.Overlay.PROGRESS);
        // Creates a green boss bar which has 50% progress and 10 notches
        final BossBar halfBar = BossBar.bossBar(name, 0.5f, BossBar.Color.GREEN, BossBar.Overlay.NOTCHED_10);
        // etc..
        final BossBar fullBar = BossBar.bossBar(name, 1, BossBar.Color.BLUE, BossBar.Overlay.NOTCHED_20);

        switch (LobbyAnnouncerRunnable.getAnnouncerFrame()) {
            case 0,5 -> {
                if (activeBar != null) {
                    hideActiveBossBar(target);
                }
                target.showBossBar(a_0);
                activeBar = a_0;
            }
            case 1,6 -> {
                if (activeBar != null) {
                    hideActiveBossBar(target);
                }
                target.showBossBar(a_1);
                activeBar = a_1;
            }
            case 2,7 -> {
                if (activeBar != null) {
                    hideActiveBossBar(target);
                }
                target.showBossBar(a_2);
                activeBar = a_2;
            }
            case 3,8 -> {
                if (activeBar != null) {
                    hideActiveBossBar(target);
                }
                target.showBossBar(a_3);
                activeBar = a_3;
            }
            case 4,9 -> {
                if (activeBar != null) {
                    hideActiveBossBar(target);
                }
                target.showBossBar(a_4);
                activeBar = a_4;
            }
        }
    }

    public static void hideActiveBossBar(final @NonNull Audience target) {
        target.hideBossBar(activeBar);
        activeBar = null;
    }
}
