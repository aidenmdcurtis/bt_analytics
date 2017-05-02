package com.aidencurtis.bt_analytics;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class AnalyticsPlugin extends JavaPlugin {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("feedback")) {
			if(args.length < 1) {
				sender.sendMessage("Don't leave empty feedback!");
				return false;
			}
			String fbOut = "";
			for(int i = 0; i < args.length; i++) {
				fbOut += args[i] + " ";
			}
			
			feedbackLogger(fbOut + " --sender: " + sender.getName() + "\n");
			sender.sendMessage("Thanks for the feedback!");
			
		}
		
		return false;
	}
	
	@Override
	public void onEnable() {
		getLogger().info("bt-analytics has been activated");
	}
	
	@Override
	public void onDisable() {
		getLogger().info("bt-analytics has been deactivated");
	}

	public void feedbackLogger(String message) {
		try {
			File bt_analytics = getDataFolder();
			if(!bt_analytics.exists()) {
				bt_analytics.mkdir();
			}
			
			File file = new File(getDataFolder(), "feedback.txt");
			if(!file.exists()) {
				file.createNewFile();
			}
			
			FileWriter fw = new FileWriter(file, true);
			PrintWriter pw = new PrintWriter(fw);
			
			pw.println(message);
			pw.flush();
			pw.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
