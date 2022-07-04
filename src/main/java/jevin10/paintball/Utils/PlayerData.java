package jevin10.paintball.Utils;

import jevin10.paintball.Paintball;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class PlayerData {
    /**
     * Resets the player's data container to a fresh one. (Only affects kills and deaths)
     * @param player Player to reset data container of.
     */
    public static void resetPlayerData(Player player) {
        PersistentDataContainer pData = player.getPersistentDataContainer();
        pData.set(new NamespacedKey(Paintball.getPlugin(), "kills"), PersistentDataType.INTEGER, 0);
        pData.set(new NamespacedKey(Paintball.getPlugin(), "deaths"), PersistentDataType.INTEGER, 0);
    }

    /**
     * Adds a win to the player's data.
     * @param player Player to add win to.
     */
    public static void addWin(Player player) {
        PersistentDataContainer pData = player.getPersistentDataContainer();
        if (!pData.has(new NamespacedKey(Paintball.getPlugin(), "wins"), PersistentDataType.INTEGER)) {
            pData.set(new NamespacedKey(Paintball.getPlugin(), "wins"), PersistentDataType.INTEGER, 0);
        }
        int wins = pData.get(new NamespacedKey(Paintball.getPlugin(), "wins"), PersistentDataType.INTEGER);
        pData.set(new NamespacedKey(Paintball.getPlugin(), "wins"), PersistentDataType.INTEGER, wins + 1);
    }

    /**
     * Adds a loss to the player's data.
     * @param player Player to add loss to.
     */
    public static void addLoss(Player player) {
        PersistentDataContainer pData = player.getPersistentDataContainer();
        if (!pData.has(new NamespacedKey(Paintball.getPlugin(), "losses"), PersistentDataType.INTEGER)) {
            pData.set(new NamespacedKey(Paintball.getPlugin(), "losses"), PersistentDataType.INTEGER, 0);
        }
        int losses = pData.get(new NamespacedKey(Paintball.getPlugin(), "losses"), PersistentDataType.INTEGER);
        pData.set(new NamespacedKey(Paintball.getPlugin(), "losses"), PersistentDataType.INTEGER, losses + 1);
    }

    /**
     * Adds a kill to the player's data.
     * @param player Player to add kill to.
     */
    public static void addKill(Player player) {
        PersistentDataContainer pData = player.getPersistentDataContainer();
        if (!pData.has(new NamespacedKey(Paintball.getPlugin(), "kills"), PersistentDataType.INTEGER)) {
            pData.set(new NamespacedKey(Paintball.getPlugin(), "kills"), PersistentDataType.INTEGER, 0);
        }
        int kills = getKills(player);
        pData.set(new NamespacedKey(Paintball.getPlugin(), "kills"), PersistentDataType.INTEGER, kills + 1);
    }

    /**
     * Adds a death to the player's data.
     * @param player Player to add death to.
     */
    public static void addDeath(Player player) {
        PersistentDataContainer pData = player.getPersistentDataContainer();
        if (!pData.has(new NamespacedKey(Paintball.getPlugin(), "deaths"), PersistentDataType.INTEGER)) {
            pData.set(new NamespacedKey(Paintball.getPlugin(), "deaths"), PersistentDataType.INTEGER, 0);
        }
        int deaths = getDeaths(player);
        pData.set(new NamespacedKey(Paintball.getPlugin(), "deaths"), PersistentDataType.INTEGER, deaths + 1);
    }

    /**
     *
     * @param player Player to get kills from.
     * @return Integer of player's kill-count.
     */
    public static @Nullable Integer getKills(Player player) {
        PersistentDataContainer pData = player.getPersistentDataContainer();
        return pData.get(new NamespacedKey(Paintball.getPlugin(), "kills"), PersistentDataType.INTEGER);
    }

    /**
     *
     * @param player Player to get deaths from.
     * @return Integer of player's death count.
     */
    public static @Nullable Integer getDeaths(Player player) {
        PersistentDataContainer pData = player.getPersistentDataContainer();
        return pData.get(new NamespacedKey(Paintball.getPlugin(), "deaths"), PersistentDataType.INTEGER);
    }

    public static Player getMVP() {
        Set<Player> players = Paintball.getGameScoreboard().getPlayers();
        Player mvp = null;
        int mvpKills = 0;
        for (Player player : players) {
            int kills = getKills(player);
            if (kills > mvpKills) {
                mvp = player;
                mvpKills = kills;
            }
        }
        return mvp;
    }

}
