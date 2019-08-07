package uwu.smsgamer.uwup;

import java.io.File;

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

		getCommand("fo").setExecutor(cmds);
		getCommand("forgive").setExecutor(cmds);
		

		getCommand("cv").setExecutor(cmds);
		getCommand("checkvl").setExecutor(cmds);
		getCommand("checkviolations").setExecutor(cmds);

		getCommand("weload").setExecutor(cmds);
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
		File configFile = new File(this.getDataFolder(), "config.yml");
		if (!configFile.exists()) {
			getConfig().options().copyDefaults(true);
		}
		saveConfig();
	}
}