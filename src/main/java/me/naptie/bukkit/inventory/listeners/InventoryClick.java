package me.naptie.bukkit.inventory.listeners;

import me.naptie.bukkit.inventory.utils.ConfigManager;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class InventoryClick implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();
            ItemStack target = event.getCurrentItem();
            if (event.getClickedInventory() != null) {
                InventoryView view = event.getView();
                if (ConfigManager.isIncluded(view.getTitle())) {
                    if (ConfigManager.isIncluded(target, false)) {
                        String item = ConfigManager.searchItem(target, false);
                        try {
                            ConfigManager.act(player, item + ".actions.inv-click");
                        } catch (NullPointerException ignored) {
                        }
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                    }
                }
            }
        }
    }

}
