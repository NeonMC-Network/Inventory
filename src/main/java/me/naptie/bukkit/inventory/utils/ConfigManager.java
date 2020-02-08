package me.naptie.bukkit.inventory.utils;

import me.naptie.bukkit.inventory.Main;
import me.naptie.bukkit.inventory.Messages;
import me.naptie.bukkit.player.commands.Language;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"deprecation", "ConstantConditions"})
public class ConfigManager {

	public static final Map<Action, String> actionMap1 = new HashMap<>();
	private static final FileConfiguration config = Main.getInstance().getConfig();
	private static final Map<String, Action[]> actionMap0 = new HashMap<>();

	public ConfigManager() {
		Action[] leftClick = {Action.LEFT_CLICK_AIR, Action.LEFT_CLICK_BLOCK};
		Action[] leftClickAir = {Action.LEFT_CLICK_AIR};
		Action[] leftClickBlock = {Action.LEFT_CLICK_AIR};
		Action[] rightClick = {Action.RIGHT_CLICK_AIR, Action.RIGHT_CLICK_BLOCK};
		Action[] rightClickAir = {Action.RIGHT_CLICK_AIR};
		Action[] rightClickBlock = {Action.RIGHT_CLICK_AIR};
		Action[] physical = {Action.PHYSICAL};
		actionMap0.put("left-click", leftClick);
		actionMap0.put("left-click-air", leftClickAir);
		actionMap0.put("left-click-block", leftClickBlock);
		actionMap0.put("right-click", rightClick);
		actionMap0.put("right-click-air", rightClickAir);
		actionMap0.put("right-click-block", rightClickBlock);
		actionMap0.put("physical", physical);

		actionMap1.put(Action.LEFT_CLICK_AIR, "left-click-air");
		actionMap1.put(Action.LEFT_CLICK_BLOCK, "left-click-block");
		actionMap1.put(Action.RIGHT_CLICK_AIR, "right-click-air");
		actionMap1.put(Action.RIGHT_CLICK_BLOCK, "right-click-block");
		actionMap1.put(Action.PHYSICAL, "physical");
	}

	@SuppressWarnings("WeakerAccess")
	public static Inventory getItem(OfflinePlayer player, Inventory i, String item) {
		Material material = Material.valueOf(config.getString("items." + item + ".material"));
		int count = config.getInt("items." + item + ".count");
		ItemStack stack;
		if (!material.equals(Material.LEGACY_SKULL_ITEM)) {
			stack = new ItemStack(material, count);
		} else {
			stack = new ItemStack(material, count, (short) 3);
		}
		ItemMeta meta = stack.getItemMeta();
		meta.setDisplayName(CU.t(config.getString("items." + item + ".name." + me.naptie.bukkit.player.utils.ConfigManager.getLanguageName(player))));
		meta.setLore(CU.t(config.getStringList("items." + item + ".lore." + me.naptie.bukkit.player.utils.ConfigManager.getLanguageName(player))));
		if (material.equals(Material.LEGACY_SKULL_ITEM)) {
			if (config.getString("items." + item + ".owner") != null) {
				String owner = config.getString("items." + item + ".owner");
				if (owner.contains("?sender")) {
					owner = owner.replace("?sender", player.getName());
				}
				((SkullMeta) meta).setOwner(owner);
			} else {
				((SkullMeta) meta).setOwner("Steve");
			}
		}
		stack.setItemMeta(meta);
		int slot = config.getInt("items." + item + ".slot");
		i.setItem(slot, stack);
		return i;
	}

	public static ItemStack getItem(OfflinePlayer player, String item) {
		Material material = Material.valueOf(config.getString("items." + item + ".material"));
		int count = config.getInt("items." + item + ".count");
		ItemStack stack;
		if (!material.equals(Material.LEGACY_SKULL_ITEM)) {
			stack = new ItemStack(material, count);
		} else {
			stack = new ItemStack(material, count, (short) 3);
		}
		ItemMeta meta = stack.getItemMeta();
		meta.setDisplayName(CU.t(config.getString("items." + item + ".name." + me.naptie.bukkit.player.utils.ConfigManager.getLanguageName(player))));
		meta.setLore(CU.t(config.getStringList("items." + item + ".lore." + me.naptie.bukkit.player.utils.ConfigManager.getLanguageName(player))));
		if (material.equals(Material.LEGACY_SKULL_ITEM)) {
			if (config.getString("items." + item + ".owner") != null) {
				String owner = config.getString("items." + item + ".owner");
				if (owner.contains("?sender")) {
					owner = owner.replace("?sender", player.getName());
				}
				((SkullMeta) meta).setOwner(owner);
			} else {
				((SkullMeta) meta).setOwner("Steve");
			}
		}
		stack.setItemMeta(meta);
		return stack;
	}

	@SuppressWarnings("deprecation")
	public static Inventory getItems(Inventory i, boolean spawn) {
		OfflinePlayer player = Bukkit.getOfflinePlayer("Steve");
		if (i.getHolder() instanceof Player) {
			player = (Player) i.getHolder();
		}
		if (config.getConfigurationSection("items") != null) {
			for (String item : config.getConfigurationSection("items").getKeys(false)) {
				if (spawn && !config.getBoolean("items." + item + ".spawn")) {
					continue;
				}
				Material material = Material.valueOf(config.getString("items." + item + ".material"));
				int count = config.getInt("items." + item + ".count");
				ItemStack stack;
				if (!material.equals(Material.LEGACY_SKULL_ITEM)) {
					stack = new ItemStack(material, count);
				} else {
					stack = new ItemStack(material, count, (short) 3);
				}
				ItemMeta meta = stack.getItemMeta();
				meta.setDisplayName(CU.t(config.getString("items." + item + ".name." + me.naptie.bukkit.player.utils.ConfigManager.getLanguageName(player))));
				meta.setLore(CU.t(config.getStringList("items." + item + ".lore." + me.naptie.bukkit.player.utils.ConfigManager.getLanguageName(player))));
				if (material.equals(Material.LEGACY_SKULL_ITEM)) {
					if (config.getString("items." + item + ".owner") != null) {
						String owner = config.getString("items." + item + ".owner");
						if (owner.contains("?sender")) {
							owner = owner.replace("?sender", player.getName());
						}
						((SkullMeta) meta).setOwner(owner);
					} else {
						((SkullMeta) meta).setOwner("Steve");
					}
				}
				stack.setItemMeta(meta);
				int slot = config.getInt("items." + item + ".slot");
				i.setItem(slot, stack);
			}
			return i;
		} else {
			Main.getInstance().getLogger().warning(Messages.getMessage("zh-CN", "CONFIG_IS_NULL"));
			return i;
		}
	}

	public static ItemStack[] getItems(OfflinePlayer player, boolean spawn) {
		ItemStack[] stacks;
		int times = 0;
		if (config.getConfigurationSection("items") != null) {
			Set<String> items = config.getConfigurationSection("items").getKeys(false);
			stacks = new ItemStack[items.size()];
			for (String item : items) {
				if (spawn && !config.getBoolean("items." + item + ".spawn")) {
					continue;
				}
				Material material = Material.valueOf(config.getString("items." + item + ".material"));
				int count = config.getInt("items." + item + ".count");
				ItemStack stack;
				if (!material.equals(Material.LEGACY_SKULL_ITEM)) {
					stack = new ItemStack(material, count);
				} else {
					stack = new ItemStack(material, count, (short) 3);
				}
				ItemMeta meta = stack.getItemMeta();
				meta.setDisplayName(CU.t(config.getString("items." + item + ".name." + me.naptie.bukkit.player.utils.ConfigManager.getLanguageName(player))));
				meta.setLore(CU.t(config.getStringList("items." + item + ".lore." + me.naptie.bukkit.player.utils.ConfigManager.getLanguageName(player))));
				if (material.equals(Material.LEGACY_SKULL_ITEM)) {
					if (config.getString("items." + item + ".owner") != null) {
						String owner = config.getString("items." + item + ".owner");
						if (owner.contains("?sender")) {
							owner = owner.replace("?sender", player.getName());
						}
						((SkullMeta) meta).setOwner(owner);
					} else {
						((SkullMeta) meta).setOwner("Steve");
					}
				}
				stack.setItemMeta(meta);
				if (times == 0) {
					stacks[0] = stack;
					times = times + 1;
				} else {
					stacks[times] = stack;
					times = times + 1;
				}
			}
			return stacks;
		} else {
			Main.getInstance().getLogger().severe(Messages.getMessage("zh-CN", "CONFIG_IS_NULL"));
			stacks = new ItemStack[0];
			return stacks;
		}
	}

	private static ItemStack[] getItems(String language, boolean spawn) {
		ItemStack[] stacks;
		int times = 0;
		if (config.getConfigurationSection("items") != null) {
			Set<String> items = config.getConfigurationSection("items").getKeys(false);
			stacks = new ItemStack[items.size()];
			for (String item : items) {
				if (spawn && !config.getBoolean("items." + item + ".spawn")) {
					continue;
				}
				Material material = Material.valueOf(config.getString("items." + item + ".material"));
				int count = config.getInt("items." + item + ".count");
				ItemStack stack;
				if (!material.equals(Material.LEGACY_SKULL_ITEM)) {
					stack = new ItemStack(material, count);
				} else {
					stack = new ItemStack(material, count, (short) 3);
				}
				ItemMeta meta = stack.getItemMeta();
				meta.setDisplayName(CU.t(config.getString("items." + item + ".name." + language)));
				meta.setLore(CU.t(config.getStringList("items." + item + ".lore." + language)));
				if (material.equals(Material.LEGACY_SKULL_ITEM)) {
					((SkullMeta) meta).setOwner("Steve");
				}
				stack.setItemMeta(meta);
				if (times == 0) {
					stacks[0] = stack;
					times = times + 1;
				} else {
					stacks[times] = stack;
					times = times + 1;
				}
			}
			return stacks;
		} else {
			Main.getInstance().getLogger().severe(Messages.getMessage("zh-CN", "CONFIG_IS_NULL"));
			stacks = new ItemStack[0];
			return stacks;
		}
	}

	private static ItemStack createItem(OfflinePlayer player, String item) {
		Material material = Material.valueOf(config.getString("items." + item + ".material"));
		int count = config.getInt("items." + item + ".count");
		ItemStack stack;
		if (!material.equals(Material.LEGACY_SKULL_ITEM)) {
			stack = new ItemStack(material, count);
		} else {
			stack = new ItemStack(material, count, (short) 3);
		}
		ItemMeta meta = stack.getItemMeta();
		meta.setDisplayName(CU.t(config.getString("items." + item + ".name." + me.naptie.bukkit.player.utils.ConfigManager.getLanguageName(player))));
		meta.setLore(CU.t(config.getStringList("items." + item + ".lore." + me.naptie.bukkit.player.utils.ConfigManager.getLanguageName(player))));
		if (material.equals(Material.LEGACY_SKULL_ITEM)) {
			if (config.getString("items." + item + ".owner") != null) {
				String owner = config.getString("items." + item + ".owner");
				if (owner.contains("?sender")) {
					owner = owner.replace("?sender", player.getName());
				}
				((SkullMeta) meta).setOwner(owner);
			} else {
				((SkullMeta) meta).setOwner("Steve");
			}
		}
		stack.setItemMeta(meta);
		return stack;
	}

	private static ItemStack createItem(String language, String item) {
		Material material = Material.valueOf(config.getString("items." + item + ".material"));
		int count = config.getInt("items." + item + ".count");
		ItemStack stack;
		if (!material.equals(Material.LEGACY_SKULL_ITEM)) {
			stack = new ItemStack(material, count);
		} else {
			stack = new ItemStack(material, count, (short) 3);
		}
		ItemMeta meta = stack.getItemMeta();
		meta.setDisplayName(CU.t(config.getString("items." + item + ".name." + language)));
		meta.setLore(CU.t(config.getStringList("items." + item + ".lore." + language)));
		if (material.equals(Material.LEGACY_SKULL_ITEM)) {
			((SkullMeta) meta).setOwner("Steve");
		}
		stack.setItemMeta(meta);
		return stack;
	}

	public static String searchItem(ItemStack target, boolean spawn) {
		for (String item : config.getConfigurationSection("items").getKeys(false)) {
			if (spawn && !config.getBoolean("items." + item + ".spawn")) {
				continue;
			}
			for (String language : Language.supportedLanguages) {
				ItemStack reference = createItem(language, item);
				if (isMatching(target, reference)) {
					return item;
				}
			}
		}
		return null;
	}

	public static boolean isMatching(ItemStack target, ItemStack reference) {
		if (target != null && target.getItemMeta() != null && reference != null && reference.getItemMeta() != null) {
			Material material = reference.getType();
			int count = reference.getAmount();
			String name = reference.getItemMeta().getDisplayName();
			String targetName = target.getItemMeta().getDisplayName();
			if (targetName != null) {
				if (targetName.contains("§")) {
					String[] targetNameSections = target.getItemMeta().getDisplayName().split("§");
					boolean nameMatching = false;
					int matchingTimes = 0;
					for (String targetNameSection : targetNameSections) {
						if (name.contains(targetNameSection)) {
							matchingTimes = matchingTimes + 1;
						}
						if (matchingTimes == targetNameSections.length) {
							nameMatching = true;
						}
					}
					return (target.getType().equals(material) && target.getAmount() == count && nameMatching);
				} else {
					return (target.getType().equals(material) && target.getAmount() == count && targetName.equalsIgnoreCase(name));
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@SuppressWarnings("SingleStatementInBlock")
	public static Inventory createMenu(OfflinePlayer player, String name) {
		if (config.getConfigurationSection("menus." + name) != null) {
			Inventory menu = Bukkit.createInventory(null, config.getInt("menus." + name + ".size"), config.getString("menus." + name + ".title." + me.naptie.bukkit.player.utils.ConfigManager.getLanguageName(player)));
			for (String item : config.getStringList("menus." + name + ".contents")) {
				getItem(player, menu, item);
			}
			return menu;
		} else {
			return Bukkit.createInventory(null, 54, "Inventory");
		}
	}

	public static Action[] getActions(String item) {
		Action[] actions;
		if (config.getConfigurationSection("items." + item + ".actions") != null) {
			Set<String> actionSet = config.getConfigurationSection("items." + item + ".actions").getKeys(false);
			actions = new Action[actionSet.size() + 2];
			int times = 0;
			for (String action : actionSet) {
				if (action.contains("left-click")) {
					if (action.equalsIgnoreCase("left-click")) {
						actions[times] = Action.LEFT_CLICK_AIR;
						actions[times + 1] = Action.LEFT_CLICK_BLOCK;
						times = times + 2;
					} else if (action.equalsIgnoreCase("left-click-air")) {
						actions[times] = Action.LEFT_CLICK_AIR;
						times = times + 1;
					} else if (action.equalsIgnoreCase("left-click-block")) {
						actions[times] = Action.LEFT_CLICK_BLOCK;
						times = times + 1;
					}
				} else if (action.contains("right-click")) {
					if (action.equalsIgnoreCase("right-click")) {
						actions[times] = Action.RIGHT_CLICK_AIR;
						actions[times + 1] = Action.RIGHT_CLICK_BLOCK;
						times = times + 2;
					} else if (action.equalsIgnoreCase("right-click-air")) {
						actions[times] = Action.RIGHT_CLICK_AIR;
						times = times + 1;
					} else if (action.equalsIgnoreCase("right-click-block")) {
						actions[times] = Action.RIGHT_CLICK_BLOCK;
						times = times + 1;
					}
				} else if (action.equalsIgnoreCase("physical")) {
					actions[times] = Action.PHYSICAL;
					times = times + 1;
				}
			}
			return actions;
		} else {
			actions = new Action[0];
			return actions;
		}
	}

	public static boolean isIncluded(ItemStack target, boolean spawn) {
		for (String language : Language.supportedLanguages) {
			for (ItemStack stack : getItems(language, spawn)) {
				if (isMatching(target, stack))
					return true;
			}
		}
		return false;
	}

	public static boolean isIncluded(String inventoryName) {
		for (String language : Language.supportedLanguages) {
			for (String reference : config.getConfigurationSection("menus").getKeys(false)) {
				if (config.getString("menus." + reference + ".title." + language).equals(inventoryName))
					return true;
			}
		}
		return false;
	}

	public static void act(Player player, String path) {
		if (path == null || path.equals("")) {
			return;
		}
		if (!path.startsWith("items.")) {
			path = "items." + path;
		}
		if (path.endsWith(".left-click") || path.endsWith(".right-click") || path.endsWith("-air") || path.endsWith("-block") || path.endsWith(".physical")) {
			if (path.endsWith("-air") && !config.getConfigurationSection(path.replace(path.split("actions")[1], "")).getKeys(false).contains("-air")) {
				String path1 = path.replace("-air", "");
				for (String action : config.getConfigurationSection(path1).getKeys(false)) {
					if (action.equalsIgnoreCase("inventory")) {
						Inventory inventory = createMenu(player, config.getString(path1 + ".inventory"));
						player.openInventory(inventory);
					} else if (action.equalsIgnoreCase("bungee")) {
						me.naptie.bukkit.core.Main.getInstance().connect(player, config.getString(path1 + ".bungee"), true);
					} else if (action.equalsIgnoreCase("command")) {
						Bukkit.dispatchCommand(player, config.getString(path1 + ".command"));
					}
				}
			} else if (path.endsWith("-block") && !config.getConfigurationSection(path.replace(path.split("actions")[1], "")).getKeys(false).contains("-block")) {
				String path1 = path.replace("-block", "");
				for (String action : config.getConfigurationSection(path1).getKeys(false)) {
					if (action.equalsIgnoreCase("inventory")) {
						Inventory inventory = createMenu(player, config.getString(path1 + ".inventory"));
						player.openInventory(inventory);
					} else if (action.equalsIgnoreCase("bungee")) {
						me.naptie.bukkit.core.Main.getInstance().connect(player, config.getString(path1 + ".bungee"), true);
					} else if (action.equalsIgnoreCase("command")) {
						Bukkit.dispatchCommand(player, config.getString(path1 + ".command"));
					}
				}
			} else {
				for (String action : config.getConfigurationSection(path).getKeys(false)) {
					if (action.equalsIgnoreCase("inventory")) {
						Inventory inventory = createMenu(player, config.getString(path + ".inventory"));
						player.openInventory(inventory);
					} else if (action.equalsIgnoreCase("bungee")) {
						me.naptie.bukkit.core.Main.getInstance().connect(player, config.getString(path + ".bungee"), true);
					} else if (action.equalsIgnoreCase("command")) {
						Bukkit.dispatchCommand(player, config.getString(path + ".command"));
					}
				}
			}

		} else if (path.endsWith("inv-click")) {
			for (String action : config.getConfigurationSection(path).getKeys(false)) {
				if (action.equalsIgnoreCase("inventory")) {
					Inventory inventory = createMenu(player, config.getString(path + ".inventory"));
					player.openInventory(inventory);
				} else if (action.equalsIgnoreCase("bungee")) {
					me.naptie.bukkit.core.Main.getInstance().connect(player, config.getString(path + ".bungee"), true);
				} else if (action.equalsIgnoreCase("command")) {
					Bukkit.dispatchCommand(player, config.getString(path + ".command"));
				}
			}
			player.closeInventory();
		}

	}

}
