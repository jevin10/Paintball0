package jevin10.paintball;

import jevin10.paintball.Utils.ColorTranslator;
import net.md_5.bungee.api.chat.TextComponent;

public class ComponentHandler {
    static int colorFrame = 0;
    static TextComponent blueTeamComponent;
    static TextComponent tdmComponent = ColorTranslator.translateColorCodesToTextComponent("&#22fd5fT&#30f057D&#3ee24eM");

    public static TextComponent getBlueTeamComponent() {
        return blueTeamComponent;
    }

    public static void setBlueTeamComponent(TextComponent blueTeamComponentParam) {
        blueTeamComponent = blueTeamComponentParam;
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
}
