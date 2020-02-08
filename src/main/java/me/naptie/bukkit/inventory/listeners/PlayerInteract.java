package me.naptie.bukkit.inventory.listeners;

import me.naptie.bukkit.inventory.utils.ConfigManager;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class PlayerInteract implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack target = event.getItem();
        if (ConfigManager.isIncluded(target, false)) {
            String item = ConfigManager.searchItem(target, false);
            for (Action action : ConfigManager.getActions(item)) {
                if (action != null && action.name().equals(event.getAction().name())) {
                    ConfigManager.act(player, item + ".actions." + ConfigManager.actionMap1.get(action));
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                    break;
                }
            }
        }
    }

}
