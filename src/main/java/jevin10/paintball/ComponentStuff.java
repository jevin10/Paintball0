package jevin10.paintball;

import net.md_5.bungee.api.chat.TextComponent;

public class ComponentStuff {
    static int colorFrame = 0;
    static TextComponent blueTeamComponent;

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
}
