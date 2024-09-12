package me.calrl.xjz;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class StoredEntityManager {

    private XJZ plugin;
    public StoredEntityManager (XJZ plugin) {
        this.plugin = plugin;
    }

    Map<Player, Entity> storedEntities = new HashMap<>();

    /**
     * set the entity to be stored under a specific player
     *
     * @param player the player that's storing the mob
     * @param entity the entity to store
     */
    public void setStoredEntity(Player player, Entity entity) {
        storedEntities.put(player, entity);
    }

    /**
     * get the entity assigned to the player
     *
     * @param player get the stored entity under "player"
     * @return the stored entity
     */
    public Entity getStoredEntity(Player player) {
        return storedEntities.get(player);
    }

    /**
     * clear the stored entity
     *
     * @param player the player whose stored entity should be cleared
     */
    public void clearStoredEntity(Player player) {
        storedEntities.remove(player);
    }
}
