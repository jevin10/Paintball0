package jevin10.paintball.Scoreboards.BossBars;

import jevin10.paintball.Paintball;
import jevin10.paintball.Runnables.BossBars.ArenaBossBarRunnable;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.checkerframework.checker.nullness.qual.NonNull;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ArenaBossBar {
    private static @Nullable BossBar activeBar;
    public static void showMyBossBar(final @NonNull Audience target) {
        final Component gameMode = Component.text("⚑ Team Deathmatch");
        final Component nameBlue = Component.text("⚔ Blue Kills: ");
        final Component nameRed = Component.text("⚔ Red Kills: ");
        // final TextColor[] blueNames = getBlueNames();
        // final TextColor[] redNames = getRedNames();

        Float blueProgress = (float) (Paintball.getGameScoreboard().getBlueTeamKills() / Paintball.getGameScoreboard().getMaxKills());
        Float redProgress = (float) (Paintball.getGameScoreboard().getRedTeamKills() / Paintball.getGameScoreboard().getMaxKills());

        final BossBar[]
                gameModeBar = new BossBar[4],
                blueScoreBar = new BossBar[4],
                redScoreBar = new BossBar[4];

        for (int i = 0; i < 4; i++) {
            gameModeBar[i] = BossBar.bossBar(gameMode, 1F, BossBar.Color.WHITE, BossBar.Overlay.PROGRESS);
            blueScoreBar[i] = BossBar.bossBar(nameBlue.append(Component.text("Null").color(TextColor.color(0x006FFF))), blueProgress, BossBar.Color.BLUE, BossBar.Overlay.PROGRESS);
            redScoreBar[i] = BossBar.bossBar(nameRed.append(Component.text("Null").color(TextColor.color(0xFF2D2D))), redProgress, BossBar.Color.RED, BossBar.Overlay.PROGRESS);
        }

        if (ArenaBossBarRunnable.getAnnouncerFrame() < 4) {
            if (activeBar != null) {
                hideActiveBossBar(target);
            }
            activeBar = gameModeBar[ArenaBossBarRunnable.getAnnouncerFrame()];
            target.showBossBar(activeBar);
        } else if (ArenaBossBarRunnable.getAnnouncerFrame() < 8) {
            if (activeBar != null) {
                hideActiveBossBar(target);
            }
            activeBar = blueScoreBar[ArenaBossBarRunnable.getAnnouncerFrame() - 4];
            target.showBossBar(activeBar);
        } else if (ArenaBossBarRunnable.getAnnouncerFrame() < 12) {
            if (activeBar != null) {
                hideActiveBossBar(target);
            }
            activeBar = redScoreBar[ArenaBossBarRunnable.getAnnouncerFrame() - 8];
            target.showBossBar(activeBar);
        }
    }
/*
    public static TextColor[] getBlueNames() {
        List<TextColor> blueList = new ArrayList<>();
        blueList.add(TextColor.color(0x75f5fd));
        blueList.add(TextColor.color(0x40b1e2));
        blueList.add(TextColor.color(0x75f5fd));
        blueList.add(TextColor.color(0x40b1e2));

        final TextColor[] nameBlueList = new TextColor[4];
        for (int i = 0; i < blueList.size(); i++) {
            nameBlueList[i] = TextColor.color(blueList.get(i));
        }
        return nameBlueList;
    }

    public static TextColor[] getRedNames() {
        List<TextColor> redList = new ArrayList<>();
        redList.add(TextColor.color(0xFD3E49));
        redList.add(TextColor.color(0xE20A65));
        redList.add(TextColor.color(0xFD3E49));
        redList.add(TextColor.color(0xE20A65));

        final TextColor[] nameRedList = new TextColor[4];
        for (int i = 0; i < redList.size(); i++) {
            nameRedList[i] = TextColor.color(redList.get(i));
        }
        return nameRedList;
    }

 */

    public static void hideActiveBossBar(final @NonNull Audience target) {
        target.hideBossBar(activeBar);
        activeBar = null;
    }
}
