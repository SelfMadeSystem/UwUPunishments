package uwu.smsgamer.uwup.Utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import uwu.smsgamer.uwup.ConfigManager.ConfigManager;
import uwu.smsgamer.uwup.UwUPunishments;

/**
 * Utils for config stuff.
 */
public class ConfigUtils {
    /**
     * @param p Player to set vl.
     * @param reason Reason to set vl.
     * @param set True to set, false to add.
     * @param vl Amount to set or add.
     */
    public static void setVl(String p, String reason, boolean set, int vl){
        if(set){
            ConfigManager.instance.getPlayers().set("Punishments." + reason + ".Level." + p, vl);
        }else{
            ConfigManager.instance.getPlayers().set("Punishments." + reason + ".Level." + p,
                    ConfigManager.instance.getPlayers().getInt("Punishments." + reason + ".Level." + p)  + vl);
        }
        if(ConfigManager.instance.getPlayers().getInt("Punishments." + reason + ".Level." + p)<0){
            ConfigManager.instance.getPlayers().set("Punishments." + reason + ".Level." + p, 0);
        }
        ConfigManager.instance.savePlayers();
    }

    /**
     * @param p Player to do the command.
     * @param forgive True to do forgive command, false for punish command.
     * @param type The type to use.
     * @param reason The reason to use.
     */
    public static void doCmd(String p, boolean forgive, String type, String reason){
        if(!forgive){for (int i = 0; i < UwUPunishments.instance.getConfig().getStringList("types." + type + ".punishments." +
                ConfigManager.instance.getPlayers().get("Punishments." + type + ".Level." + p))
                .toArray().length; i++) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                    ChatUtils.phReplace(ChatUtils.colorize(
                            UwUPunishments.instance.getConfig().getStringList("types." + type + ".punishments." +
                                    ConfigManager.instance.getPlayers().get("Punishments." + type + ".Level." + p)).toArray()[i] + ""),
                            p, reason, ConfigManager.instance.getPlayers().getInt("Punishments." + type + ".Level." + p)));
        }
        }else{
            for (int i = 0; i < UwUPunishments.instance.getConfig().getStringList("types." + type + ".forgivements." +
                    ConfigManager.instance.getPlayers().get("Punishments." + type + ".Level." + p))
                    .toArray().length; i++) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                        ChatUtils.phReplace(ChatUtils.colorize(
                                UwUPunishments.instance.getConfig().getStringList("types." + type + ".forgivements." +
                                        ConfigManager.instance.getPlayers().get("Punishments." + type + ".Level." + p)).toArray()[i] + ""),
                                p, reason, ConfigManager.instance.getPlayers().getInt("Punishments." + type + ".Level." + p)));
            }
        }
    }
}
