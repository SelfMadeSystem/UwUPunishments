package uwu.smsgamer.uwup;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigManager {

	public static ConfigManager instance;
	
	private Main plugin = Main.getPlugin(Main.class);
	// Files & File Configs Here
	public FileConfiguration playerscfg;
	public File playersfile;
	
	public FileConfiguration logscfg;
	public File logsfile;
	// --------------------------

	public void setup() {
		instance = this;
		if (!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdir();
		}

		playersfile = new File(plugin.getDataFolder(), "players.yml");
		logsfile = new File(plugin.getDataFolder(), "logs.yml");

		if (!playersfile.exists()) {
			try {
				playersfile.createNewFile();
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "The players.yml file has been created");
			} catch (IOException e) {
				Bukkit.getServer().getConsoleSender()
						.sendMessage(ChatColor.RED + "Could not create the players.yml file");
			}
		}
		
		if (!logsfile.exists()) {
			try {
				logsfile.createNewFile();
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "The logs.yml file has been created");
			} catch (IOException e) {
				Bukkit.getServer().getConsoleSender()
						.sendMessage(ChatColor.RED + "Could not create the logs.yml file");
			}
		}

		playerscfg = YamlConfiguration.loadConfiguration(playersfile);
		
		logscfg = YamlConfiguration.loadConfiguration(logsfile);
	}

	public FileConfiguration getPlayers() {
		instance = this;
		return playerscfg;
	}

	public void savePlayers() {
		try {
			playerscfg.save(playersfile);
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "The players.yml file has been saved");

		} catch (IOException e) {
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Could not save the players.yml file");
		}
	}

	public void reloadPlayers() {
		playerscfg = YamlConfiguration.loadConfiguration(playersfile);
		Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.BLUE + "The players.yml file has been reload");

	}
	
	public FileConfiguration getLogs() {
		instance = this;
		return logscfg;
	}

	public void saveLogs() {
		try {
			logscfg.save(playersfile);
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "The logs.yml file has been saved");

		} catch (IOException e) {
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Could not save the logs.yml file");
		}
	}

	public void reloadLogs() {
		playerscfg = YamlConfiguration.loadConfiguration(playersfile);
		Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.BLUE + "The logs.yml file has been reload");

	}
	
	
	//------------------------
}
