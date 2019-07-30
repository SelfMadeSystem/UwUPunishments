package uwu.smsgamer.uwup;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import uwu.smsgamer.uwup.commands.Commands;
import uwu.smsgamer.uwup.events.Events;

public class Main extends JavaPlugin implements Listener {
    
    public static Main instance;
    
    public void onEnable() {
        instance = this;
        Commands cmds = new Commands();
        loadConfig();
        getServer().getPluginManager().registerEvents(new Events(), this);
        getCommand("pu").setExecutor(cmds);
        getCommand("punish").setExecutor(cmds);
    }
    
    public void onDisable() {
    }
    
    public void loadConfig() {
        saveConfig();
    }
}