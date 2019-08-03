package uwu.smsgamer.uwup;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import uwu.smsgamer.uwup.commands.Commands;

public class Main extends JavaPlugin implements Listener {

	private ConfigManager cfgm;

	public static Main instance;

	public void onEnable() {
		instance = this;
		Commands cmds = new Commands();
		loadConfig();
		loadConfigManager();
		getCommand("pu").setExecutor(cmds);
		getCommand("punish").setExecutor(cmds);
	}

	public void onDisable() {
	}

	public void loadConfigManager() {
		cfgm = new ConfigManager();
		cfgm.setup();
		cfgm.savePlayers();
		cfgm.reloadPlayers();
	}

	public void loadConfig() {
		saveConfig();
	}
}