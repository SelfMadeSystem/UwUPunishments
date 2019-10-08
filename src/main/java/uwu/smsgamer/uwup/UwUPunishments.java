package uwu.smsgamer.uwup;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import uwu.smsgamer.uwup.Commands.Commands;
import uwu.smsgamer.uwup.ConfigManager.ConfigManager;
import uwu.smsgamer.uwup.Utils.MySQLUtils;

import java.io.File;

/**
 * Main class for this plugin.
 */
public class UwUPunishments extends JavaPlugin implements Listener {

    public static boolean isSql = false;

    public static UwUPunishments instance;

    /**
     * Used to do stuff when the plugin gets enabled (after reload, on server start)
     */
    public void onEnable() {

        instance = this;
        Commands cmds = new Commands();
        loadConfig();
        loadConfigManager();
        if(this.getConfig().getBoolean("MySql.enabled")){
            mysqlSetup();
            isSql = true;
        }
        getCommand("punish").setExecutor(cmds);

        getCommand("forgive").setExecutor(cmds);

        getCommand("checkviolations").setExecutor(cmds);

        //getCommand("weload").setExecutor(cmds);

        getCommand("setviolations").setExecutor(cmds);

    }

    /**
     * Used to do stuff when the plugin gets disabled (before reload, on server stop)
     */
    public void onDisable() {
        MySQLUtils.disconnect();
    }

    /**
     * Used to load configuration file: players.yml
     */
    public void loadConfigManager() {
        ConfigManager cfgm = new ConfigManager();
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
        }else{
            FileConfiguration config =  YamlConfiguration.loadConfiguration(configFile);
            if(config.getDouble("config-version")<2.1){
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED+"\n\n" +
                        "   ____        _      _       _           _    _____             __ _         ______ _ _        \n" +
                        "  / __ \\      | |    | |     | |         | |  / ____|           / _(_)       |  ____(_) |       \n" +
                        " | |  | |_   _| |_ __| | __ _| |_ ___  __| | | |     ___  _ __ | |_ _  __ _  | |__   _| | ___   \n" +
                        " | |  | | | | | __/ _` |/ _` | __/ _ \\/ _` | | |    / _ \\| '_ \\|  _| |/ _` | |  __| | | |/ _ \\  \n" +
                        " | |__| | |_| | || (_| | (_| | ||  __/ (_| | | |___| (_) | | | | | | | (_| | | |    | | |  __/_ \n" +
                        "  \\____/ \\__,_|\\__\\__,_|\\__,_|\\__\\___|\\__,_|  \\_____\\___/|_| |_|_| |_|\\__, | |_|    |_|_|\\___(_)\n" +
                        "                                                                       __/ |                    \n" +
                        "                                                                      |___/                     ");
                File configBakFile = new File(this.getDataFolder(), "config.yml.bak");
                configFile.renameTo(configBakFile);
                saveDefaultConfig();
            }
        }
    }

    public void mysqlSetup(){
        MySQLUtils.host = this.getConfig().getString("MySql.host");
        MySQLUtils.port = this.getConfig().getInt("MySql.port");
        MySQLUtils.database = this.getConfig().getString("MySql.database");
        MySQLUtils.username = this.getConfig().getString("MySql.username");
        MySQLUtils.password = this.getConfig().getString("MySql.password");
        MySQLUtils.tablePrefix = this.getConfig().getString("MySql.table");

        MySQLUtils.connect();
    }
}