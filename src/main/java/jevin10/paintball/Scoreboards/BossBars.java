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

        if (LobbyAnnouncerRunnable.getAnnouncerFrame() < 100) {
            if (activeBar != null) {
                hideActiveBossBar(target);
            }
            target.showBossBar(a[LobbyAnnouncerRunnable.getAnnouncerFrame()]);
            activeBar = a[LobbyAnnouncerRunnable.getAnnouncerFrame()];
        } else if (LobbyAnnouncerRunnable.getAnnouncerFrame() < 200) {
            if (activeBar != null) {
                hideActiveBossBar(target);
            }
            target.showBossBar(b[LobbyAnnouncerRunnable.getAnnouncerFrame() - 100]);
            activeBar = b[LobbyAnnouncerRunnable.getAnnouncerFrame() - 100];
        }
    }

    public static void hideActiveBossBar(final @NonNull Audience target) {
        target.hideBossBar(activeBar);
        activeBar = null;
    }
}
