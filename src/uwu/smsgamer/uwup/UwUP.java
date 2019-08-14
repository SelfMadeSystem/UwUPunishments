package uwu.smsgamer.uwup;

import java.io.File;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import uwu.smsgamer.uwup.commands.Commands;

public class UwUP extends JavaPlugin implements Listener {

	private ConfigManager cfgm;
	
	public static UwUP instance;
	
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