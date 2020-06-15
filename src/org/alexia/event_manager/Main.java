package org.alexia.event_manager;

import java.util.LinkedList;
import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

	public static Plugin plugin = null;
	public static HashMap<String, LinkedList<Player>> event = new HashMap<String, LinkedList<Player>>();
	
	@Override
	public void onEnable() {
		 
		Main.plugin = this;
		this.saveDefaultConfig();
		this.getCommand("event").setExecutor(new Event_command());
	}
	 
	@Override
	public void onDisable() {
		 
	}
	
}
