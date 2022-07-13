package jevin10.paintball.Listeners;

import jevin10.paintball.Paintball;
import jevin10.paintball.Utils.Processes.SetupInventory;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawnListener implements Listener {
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player p = event.getPlayer();
        if (Paintball.getPbWorld() == null) {
            return;
        }
        if (p.getWorld() == Paintball.getPbWorld()) {
            if (!Paintball.getGameScoreboard().getPlayers().contains(p)) {
                Paintball.getGameScoreboard().addPlayerToTeam("no", p);
            }
            if (Paintball.getGameScoreboard().getGameInstance().equals("lobby") | Paintball.getGameScoreboard().getGameInstance().equals("end")) {
                event.setRespawnLocation(Paintball.getPlugin().getConfig().getLocation("lobbyLocation"));
            }
            else if (Paintball.getGameScoreboard().getGameInstance().equals("arena")) {
                String team = Paintball.getGameScoreboard().getScoreboard().getEntityTeam(p).getName();
                switch (team) {
                    case "red" -> event.setRespawnLocation(new Location(Paintball.getPbWorld(), 3366.0, -60.0, 205.0, 90, -4));
                    case "blue" -> event.setRespawnLocation(new Location(Paintball.getPbWorld(),  3307.0, -60.0, 254.0, -90, -4));
                    case "no" -> event.setRespawnLocation(Paintball.getPlugin().getConfig().getLocation("lobbyLocation"));
                }
            }

        }
    }
}

/*
Location location = new Location(gameWorld, 3339.0, -37.0, 230.0, -90, 90);;
            switch (teamName) {
                case "RED" -> location = new Location(gameWorld, 3366.0, -60.0, 205.0, 90, -4);
                case "BLUE" -> location = new Location(gameWorld, 3307.0, -60.0, 254.0, -90, -4);
                case "NO" -> location = new Location(gameWorld, 3339.0, -37.0, 230.0, -90, 90);
            }
            event.setRespawnLocation(location);
 */