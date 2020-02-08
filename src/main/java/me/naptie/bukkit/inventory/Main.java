package me.naptie.bukkit.inventory;

import me.naptie.bukkit.inventory.commands.Inventories;
import me.naptie.bukkit.inventory.listeners.InventoryClick;
import me.naptie.bukkit.inventory.listeners.PlayerInteract;
import me.naptie.bukkit.inventory.utils.ConfigManager;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Logger;


public class Main extends JavaPlugin {

    private static Main instance;
    private static Logger logger;
    private PluginDescriptionFile descriptionFile;

    public void onEnable() {
        instance = this;
        logger = getLogger();
        new ConfigManager();

        registerCommands();
        registerConfig();
        registerEvents();

        logger.info("Enabled " + descriptionFile.getName() + " v" + descriptionFile.getVersion());
    }

    public void onDisable() {
        instance = null;
        logger.info("Disabled " + descriptionFile.getName() + " v" + descriptionFile.getVersion());
        logger = null;
    }

    private void registerConfig() {
        descriptionFile = getDescription();
        getConfig().options().copyDefaults(true);
        getConfig().options().copyHeader(true);
        saveDefaultConfig();
        for (String language : getConfig().getStringList("languages")) {
            File localeFile = new File(getDataFolder(), language + ".yml");
            if (localeFile.exists()) {
                if (getConfig().getBoolean("update-language-files")) {
                    saveResource(language + ".yml", true);
                }
            } else {
                saveResource(language + ".yml", false);
            }
        }
    }

    private void registerCommands() {
        getCommand("inventory").setExecutor(new Inventories());
    }

    private void registerEvents() {
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new PlayerInteract(), this);
        pm.registerEvents(new InventoryClick(), this);
    }

    public static Main getInstance() {
        return instance;
    }

}
