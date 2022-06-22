package jevin10.paintball.Runnables;

import jevin10.paintball.ComponentStuff;
import jevin10.paintball.Utils.ColorTranslator;
import org.bukkit.scheduler.BukkitRunnable;

public class TeamComponentRunnable extends BukkitRunnable {

    @Override
    public void run() {
        int colorFrame = ComponentStuff.getColorFrame();
        switch(colorFrame) {
            case 0 -> ComponentStuff.setBlueTeamComponent(ColorTranslator.translateColorCodesToTextComponent("&#75f5fdB&#52c8ebl&#40b1e2u&#40b1e2e"));
            case 1 -> ComponentStuff.setBlueTeamComponent(ColorTranslator.translateColorCodesToTextComponent("&#40b1e2B&#63def4l&#63def4u&#40b1e2e"));
            case 2 -> ComponentStuff.setBlueTeamComponent(ColorTranslator.translateColorCodesToTextComponent("&#40b1e2B&#40b1e2l&#52c8ebu&#75f5fde"));
        }
        ComponentStuff.incrementColorFrame();

    }
}
