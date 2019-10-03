package uwu.smsgamer.uwup.ConfigManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import uwu.smsgamer.uwup.UwUPunishments;

import java.io.File;
import java.io.IOException;

/**
 * Manages configuration file: players.yml
 */
public class ConfigManager {

	public static ConfigManager instance;

	private UwUPunishments plugin = UwUPunishments.getPlugin(UwUPunishments.class);
	// Files & File Configs Here
	public FileConfiguration playerscfg;
	public File playersfile;
	// --------------------------

	/**
	 * Sets up the configuration files.
	 */
	public void setup() {
		instance = this;
		if (!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdir();
		}

		playersfile = new File(plugin.getDataFolder(), "players.yml");

		if (!playersfile.exists()) {
			try {
				playersfile.createNewFile();
			} catch (IOException e) {
				Bukkit.getServer().getConsoleSender()
						.sendMessage(ChatColor.RED + "Could not create the players.yml file");
			}
		}

		playerscfg = YamlConfiguration.loadConfiguration(playersfile);
	}

	/**
	 * Used to get the configuration file: players.yml
	 * @return Will returns the FileConfiguration of players.yml
	 */
	public FileConfiguration getPlayers() {
		instance = this;
		return playerscfg;
	}

	/**
	 * Used to save the configuration file: players.yml
	 */
	public void savePlayers() {
		try {
			playerscfg.save(playersfile);

		} catch (IOException e) {
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Could not save the players.yml file");
		}
	}

	/**
	 * Used to reload the configuration file: players.yml
	 */
	public void reloadPlayers() {
		playerscfg = YamlConfiguration.loadConfiguration(playersfile);

	}

	// ------------------------
}
