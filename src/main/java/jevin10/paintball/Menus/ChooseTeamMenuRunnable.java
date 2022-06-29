package jevin10.paintball.Menus;

import jevin10.paintball.ComponentHandler;
import jevin10.paintball.Exceptions.MenuManagerException;
import jevin10.paintball.Exceptions.MenuManagerNotSetupException;
import jevin10.paintball.Paintball;
import jevin10.paintball.Utils.MenuManager.MenuManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import static jevin10.paintball.Utils.ItemUtils.makeItem;

public class ChooseTeamMenuRunnable extends BukkitRunnable {
    Player p;

    public ChooseTeamMenuRunnable(Player p) {
        this.p = p;
    }

    @Override
    public void run() {

        if (!p.getOpenInventory().getTitle().contains("Choose a team")) {
            return;
        }

        ItemStack chooseTeamBlue = makeItem(Material.BLUE_WOOL, ComponentHandler.getBlueTeamComponent().toLegacyText(), "&#ffffffJoin blue team!", " ", "&#ffffff☻ " + Paintball.getGameScoreboard().getTeamPlayers("blue").size());
        ItemStack chooseTeamRed = makeItem(Material.RED_WOOL, ComponentHandler.getRedTeamComponent().toLegacyText(), "&#ffffffJoin red team!", " ", "&#ffffff☻ " + Paintball.getGameScoreboard().getTeamPlayers("red").size());

        p.getOpenInventory().setItem(3, chooseTeamBlue);
        p.getOpenInventory().setItem(5, chooseTeamRed);

        p.updateInventory();

    }
}
