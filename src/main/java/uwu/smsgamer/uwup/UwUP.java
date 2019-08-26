package uwu.smsgamer.uwup;

import java.io.File;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import uwu.smsgamer.uwup.commands.Commands;

/**
 * Main class for this plugin.
 */
public class UwUP extends JavaPlugin implements Listener {

	private ConfigManager cfgm;

	public static UwUP instance;

	/**
	 * Used to do stuff when the plugin gets enabled (after reload, on server start)
	 */
	public void onEnable() {

		instance = this;
		Commands cmds = new Commands();
		loadConfig();
		loadConfigManager();
		getCommand("punish").setExecutor(cmds);

		getCommand("forgive").setExecutor(cmds);

		getCommand("checkviolations").setExecutor(cmds);

		getCommand("weload").setExecutor(cmds);

		getCommand("setviolations").setExecutor(cmds);

	}

	/**
	 * Used to do stuff when the plugin gets disabled (before reload, on server stop)
	 */
	public void onDisable() {
	}

	/**
	 * Used to load configuration file: players.yml
	 */
	public void loadConfigManager() {
		cfgm = new ConfigManager();
		cfgm.setup();
		cfgm.savePlayers();
		cfgm.reloadPlayers();
	}

	/**
	 * Used to load configuration file: config.yml
	 */
	public void loadConfig() {
		File configFile = new File(this.getDataFolder(), "config.yml");
		if (!configFile.exists()) {
			saveDefaultConfig();
		}
	}
}