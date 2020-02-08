package me.naptie.bukkit.inventory.commands;

import me.naptie.bukkit.inventory.Main;
import me.naptie.bukkit.inventory.Messages;
import me.naptie.bukkit.inventory.Permissions;
import me.naptie.bukkit.inventory.utils.ConfigManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Inventories implements CommandExecutor {

	@SuppressWarnings("ConstantConditions")
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
		if (commandSender instanceof Player) {
			Player player = (Player) commandSender;

			if (player.hasPermission(Permissions.MENU)) {
				if (strings.length != 0) {
					for (String inv : Main.getInstance().getConfig().getConfigurationSection("menus").getKeys(false)) {
						if (strings[0].equalsIgnoreCase(inv)) {
							openInventory(player, inv);
							return true;
						}
					}
					player.sendMessage(Messages.getMessage(player, "MENU_NOT_FOUND").replace("%menu%", strings[0]));
					return true;
				} else {
					player.sendMessage(Messages.getMessage(player, "USAGE").replace("%usage%", "/menu <menu>"));
					return true;
				}
			} else {
				player.sendMessage(Messages.getMessage(player, "PERMISSION_DENIED"));
				return true;
			}

		} else {
			commandSender.sendMessage(Messages.getMessage("zh-CN", "NOT_A_PLAYER"));
			return true;
		}
	}

	private void openInventory(Player player, String inv) {
		org.bukkit.inventory.Inventory i = ConfigManager.createMenu(player, inv);
		player.openInventory(i);
	}

}
