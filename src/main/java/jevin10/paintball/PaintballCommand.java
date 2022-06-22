package jevin10.paintball;

import me.kodysimpson.simpapi.colors.ColorTranslator;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.chat.TextComponent;
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
                TextComponent supNigga = ColorTranslator.translateColorCodesToTextComponent("&#084cfbS&#2064fbu&#377cfcp &#4f94fcn&#66abfci&#7ec3fcg&#95dbfdg&#adf3fda");
                p.sendMessage(supNigga);
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
