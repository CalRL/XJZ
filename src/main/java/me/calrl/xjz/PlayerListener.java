package me.calrl.xjz;

import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class PlayerListener implements Listener {
    private XJZ plugin;
    private StoredEntityManager storedEntityManager;
    public PlayerListener(XJZ plugin) {
        this.plugin = plugin;
        this.storedEntityManager = plugin.getStoredEntityManager();
    }

    @EventHandler
    private void onPlayerInteract(PlayerInteractEvent event) {
        final Action action = event.getAction();
        final Player player = event.getPlayer();

        if (event.getHand() != EquipmentSlot.HAND) {
            return;
        }

        if(event.getPlayer().getInventory().getItemInMainHand().getType() != Material.AIR) return;


        if(action != Action.RIGHT_CLICK_AIR && action != Action.RIGHT_CLICK_BLOCK) return;

        final Entity entity = storedEntityManager.getStoredEntity(player);
        if(entity == null) {
            player.sendMessage(Component.text("You have no mob stored."));
            return;
        }

        final Location spawnLocation = event.getInteractionPoint();
        spawnLocation.getWorld().spawnEntity(spawnLocation, entity.getType());
        player.sendMessage(Component.text("Your mob has been spawned"));
        storedEntityManager.clearStoredEntity(player);


    }

    @EventHandler
    private void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        final Player player = event.getPlayer();
        final Entity clickedEntity = event.getRightClicked();
        final Entity storedEntity = storedEntityManager.getStoredEntity(player);

        if (event.getHand() != EquipmentSlot.HAND) {
            return;
        }
        if(event.getPlayer().getInventory().getItemInMainHand().getType() != Material.AIR) return;

        if(storedEntity == null) {
            player.sendMessage(Component.text("Your mob has been saved"));
            storedEntityManager.setStoredEntity(player, clickedEntity);
            clickedEntity.remove();
        }

    }
}
