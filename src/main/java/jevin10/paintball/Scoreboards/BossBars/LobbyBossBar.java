package jevin10.paintball.Scoreboards.BossBars;

import jevin10.paintball.Runnables.BossBars.LobbyBossBarRunnable;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import org.checkerframework.checker.nullness.qual.NonNull;

import javax.annotation.Nullable;

public class LobbyBossBar {
    private static @Nullable BossBar activeBar;
    public static void showMyBossBar(final @NonNull Audience target) {
        final Component nameA = Component.text("You're Playing Paintball!");
        final Component nameB = Component.text("Learn More: www.metacraft.market");

        final BossBar[]
                a = new BossBar[100],
                b = new BossBar[100];

        for (int i = 0; i < a.length; i++) {
            if (i == 0) {
                a[i] = BossBar.bossBar(nameA, 0F, BossBar.Color.WHITE, BossBar.Overlay.PROGRESS);
                b[i] = BossBar.bossBar(nameB, 0F, BossBar.Color.WHITE, BossBar.Overlay.PROGRESS);
            } else {
                a[i] = BossBar.bossBar(nameA, a[i-1].progress() + 0.01F, BossBar.Color.WHITE, BossBar.Overlay.PROGRESS);
                b[i] = BossBar.bossBar(nameB, b[i-1].progress() + 0.01F, BossBar.Color.WHITE, BossBar.Overlay.PROGRESS);
            }
        }

        if (LobbyBossBarRunnable.getAnnouncerFrame() < 100) {
            if (activeBar != null) {
                hideActiveBossBar(target);
            }
            activeBar = a[LobbyBossBarRunnable.getAnnouncerFrame()];
            target.showBossBar(activeBar);
        } else if (LobbyBossBarRunnable.getAnnouncerFrame() < 200) {
            if (activeBar != null) {
                hideActiveBossBar(target);
            }
            activeBar = b[LobbyBossBarRunnable.getAnnouncerFrame() - 100];
            target.showBossBar(activeBar);
        }
    }

    public static void hideActiveBossBar(final @NonNull Audience target) {
        target.hideBossBar(activeBar);
        activeBar = null;
    }
}
