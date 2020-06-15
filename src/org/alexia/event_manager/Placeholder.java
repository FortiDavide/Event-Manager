package org.alexia.event_manager;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Placeholder {

	public static String parser(String phrase) {
		phrase = ChatColor.translateAlternateColorCodes('&', phrase);
		return phrase;
	}
	
	public static String parser(String phrase, String[] args) {
		phrase = ChatColor.translateAlternateColorCodes('&', phrase);
		
		for(int i = 0; i < args.length; ) {
			phrase.replaceAll(args[i], args[i + 1]);
			i += 2;
		}
		
		return phrase;
	}
	
	public static String parser(String phrase, Player p) {
		phrase.replaceAll("{player_displayName}", p.getDisplayName());
		phrase.replaceAll("{player_customName}", p.getCustomName());
		phrase.replaceAll("{player_Name}", p.getName());
		phrase.replaceAll("{player_PlayerListName}", p.getPlayerListName());
		phrase = ChatColor.translateAlternateColorCodes('&', phrase);
		return phrase;
	}

}
