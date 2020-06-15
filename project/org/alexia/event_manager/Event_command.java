package org.alexia.event_manager;

import java.util.LinkedList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Event_command implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		Player p = null;
		
		if(sender instanceof Player) {
			p = (Player) sender;
		} else {
			sender.sendMessage(Placeholder.parser(Main.plugin.getConfig().getString("console-sender-error")));
			return true;
		}
		
		if(args.length == 2) {
			switch(args[0].toLowerCase()) {
				case "create":
					if(p.hasPermission("event.create")) {
						if(Main.event.containsKey(args[1])) {
							sender.sendMessage(Placeholder.parser(Main.plugin.getConfig().getString("event-already-exists")));
							return true;
						} else {
							Main.event.put(args[1], new LinkedList<Player>());
							sender.sendMessage(Placeholder.parser(Main.plugin.getConfig().getString("event-created")));
							return true;
						}
					} else {
						sender.sendMessage(Placeholder.parser(Main.plugin.getConfig().getString("permissions-error")));
						return true;
					}
					
					
				case "join":
					if(p.hasPermission("event.join")) {
						if(Main.event.containsKey(args[1])) {
							if(Main.event.get(args[1]).contains(p)) {
								sender.sendMessage(Placeholder.parser(Main.plugin.getConfig().getString("event-already-joined")));
								return true;
							} else {
								Main.event.get(args[1]).add(p);
								sender.sendMessage(Placeholder.parser(Main.plugin.getConfig().getString("event-joined")));
								return true;
							}
						} else {
							sender.sendMessage(Placeholder.parser(Main.plugin.getConfig().getString("event-not-exsists")));
							return true;
						}
					} else {
						sender.sendMessage(Placeholder.parser(Main.plugin.getConfig().getString("permissions-error")));
						return true;
					}
					
				case "leave":
					if(p.hasPermission("event.leave")) {
						if(Main.event.containsKey(args[1])) {
							if(Main.event.get(args[1]).contains(p)) {
								Main.event.get(args[1]).remove(p);
								sender.sendMessage(Placeholder.parser(Main.plugin.getConfig().getString("event-leaved")));
								return true;
							} else {
								sender.sendMessage(Placeholder.parser(Main.plugin.getConfig().getString("event-not-joined")));
								return true;
							}
						} else {
							sender.sendMessage(Placeholder.parser(Main.plugin.getConfig().getString("event-not-exsists")));
							return true;
						}
					} else {
						sender.sendMessage(Placeholder.parser(Main.plugin.getConfig().getString("permissions-error")));
						return true;
					}
					
				case "start":
					if(p.hasPermission("event.start")) {
						if(Main.event.containsKey(args[1])) {
							sender.sendMessage(Placeholder.parser(Main.plugin.getConfig().getString("event-started"), new String[] {"event_name", args[1] }));
							for(Player player: Main.event.get(args[1])) {
								if(p != player) {
									sender.sendMessage(Placeholder.parser(Main.plugin.getConfig().getString("event-started")));
									player.teleport(p.getLocation());
								}
							}
							return true;
						} else {
							sender.sendMessage(Placeholder.parser(Main.plugin.getConfig().getString("event-not-exsists")));
							return true;
						}
					} else {
						sender.sendMessage(Placeholder.parser(Main.plugin.getConfig().getString("permissions-error")));
						return true;
					}
					
				case "disband":
					if(p.hasPermission("event.disband")) {
						if(Main.event.containsKey(args[1])) {
							sender.sendMessage(Placeholder.parser(Main.plugin.getConfig().getString("event-disbanded")));
							Main.event.remove(args[1]);
							return true;
						} else {
							sender.sendMessage(Placeholder.parser(Main.plugin.getConfig().getString("event-not-exsists")));
							return true;
						}
					} else {
						sender.sendMessage(Placeholder.parser(Main.plugin.getConfig().getString("permissions-error")));
						return true;
					}
			}	
		}
		
		return false;
	}

	
	
}
