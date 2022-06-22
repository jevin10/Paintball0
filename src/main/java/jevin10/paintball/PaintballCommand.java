package jevin10.paintball;

import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PaintballCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player p) {
            if (args[0] == null) {
                p.sendMessage(Component.text("Paintball argument can't be empty!"));
                return true;
            }
            if (args[0].equalsIgnoreCase("chooseteam")) {
                // Check if paintball is instantiated
                // Open team menu
                return true;
            }

        }
        return true;
    }
}
