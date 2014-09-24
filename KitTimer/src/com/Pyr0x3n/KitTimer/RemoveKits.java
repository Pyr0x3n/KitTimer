package com.Pyr0x3n.KitTimer;

import java.sql.SQLException;
import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class RemoveKits implements CommandExecutor{
	private Main plugin;
	public RemoveKits(Main plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (sender instanceof Player) {
			sender.sendMessage(ChatColor.RED + "This command can only be used by the console.");
			return true;
		}
		if (cmd.getName().equalsIgnoreCase("RemoveKits") && args.length !=1 ){
			sender.sendMessage(ChatColor.GREEN + "Syntax error, usage: /RemoveKits <PlayerUUID>");
			return true;
		}
		if (cmd.getName().equalsIgnoreCase("RemoveKits") && args.length == 1){
			if (!(args[0].length() > 30)){
				sender.sendMessage(ChatColor.GREEN + "You must enter an UUID.");
				return true;
				
			}
			try{
				this.plugin.mySQL.resetPlayerKits(fixUUID(args[0]));
			}catch(SQLException e){
				 this.plugin.log("Couldn't remove players' kits", Level.WARNING);
			}	
			sender.sendMessage(ChatColor.GREEN + "All the kits of player with uuid: "+ args[0] + " have been removed : ");	
			return true;
		}	
		return false;
	}
	
	public String fixUUID(String playerUUID){
		//01a6ec63-4c69-4430-9939-8a834d0dc0e0
		String fixedUUID = playerUUID.substring(0, 8) + "-" 
				+ playerUUID.substring(8,12) + "-"
				+ playerUUID.substring(12,16) + "-"
				+ playerUUID.substring(16,20) + "-"
				+ playerUUID.substring(20,32);
		return fixedUUID;
	}
}
