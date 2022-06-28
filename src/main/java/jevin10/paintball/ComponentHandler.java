package jevin10.paintball;

import jevin10.paintball.Utils.ColorTranslator;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.Objects;

public class ComponentHandler {
    static int colorFrame = 0;
    static TextComponent blueTeamComponent;
    static TextComponent redTeamComponent;
    static TextComponent tdmComponent = ColorTranslator.translateColorCodesToTextComponent("&#22fd5fT&#30f057D&#3ee24eM");

    public static TextComponent getBlueTeamComponent() {
        return blueTeamComponent;
    }
    public static TextComponent getRedTeamComponent() {
        return redTeamComponent;
    }

    public static void setBlueTeamComponent(TextComponent blueTeamComponentParam) {
        blueTeamComponent = blueTeamComponentParam;
    }

    public static void setRedTeamComponent(TextComponent redTeamComponentParam) {
        redTeamComponent = redTeamComponentParam;
    }

    public static int getColorFrame() {
        return colorFrame;
    }

    public static void incrementColorFrame() {
        if (colorFrame >= 3) {
            colorFrame = 0;
        } else {
            colorFrame++;
        }
    }

    public static TextComponent getTdmComponent() {
        return tdmComponent;
    }

    /**
     *
     * @param p Player that is already instantiated with a team in the GameScoreboard
     * @return TextComponent corresponding to p's team name, updated by TeamComponentRunnable
     */
    public static TextComponent getTeamComponent(Player p) {
        String playerTeam = Paintball.getGameScoreboard().getScoreboard().getPlayerTeam(p).getName();
        switch (playerTeam) {
            case "blue" -> {
                return getBlueTeamComponent();
            }
            case "red" -> {
                return getRedTeamComponent();
            }
            case "no" -> {
                return new TextComponent("None");
            }
        }
        return new TextComponent("Null");
    }
}
