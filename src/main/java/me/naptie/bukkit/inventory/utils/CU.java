package me.naptie.bukkit.inventory.utils;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class CU {

    public static String t(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    static List<String> t(List<String> stringList) {
        List<String> newStringList = new ArrayList<>();
        for (String s : stringList) {
            newStringList.add(ChatColor.translateAlternateColorCodes('&', s));
        }
        return newStringList;
    }

}
