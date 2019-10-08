package uwu.smsgamer.uwup.Commands.SubCommands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import uwu.smsgamer.uwup.ConfigManager.ConfigManager;
import uwu.smsgamer.uwup.Utils.ChatUtils;
import uwu.smsgamer.uwup.Utils.ConfigUtils;
import uwu.smsgamer.uwup.Utils.LogUtils;
import uwu.smsgamer.uwup.UwUPunishments;
import uwu.smsgamer.uwup.Vars.Vars;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Punish command.
 */
public class PunishCommand {
    /**
     * Executes the Punish command.
     *
     * @param sender  Source of the command
     * @param cmd Command which was executed
     * @param label   Alias of the command which was used
     * @param args    Passed command arguments
     */
    public static void punishCommand(CommandSender sender, Command cmd, String label, String[] args){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        if (args.length <= 1) {
            sender.sendMessage(ChatColor.RED + "UwU >> Usage: /"+label+" <player> <reason>");
            return;
        }
        Player p = Bukkit.getPlayer(args[0]);
        if (p != null && !p.hasPermission("uwu.pu.exempt")) {
            for (String num : UwUPunishments.instance.getConfig().getConfigurationSection("types").getKeys(false)) {
                if (UwUPunishments.instance.getConfig().getStringList("types." + num + ".alias").contains(args[1])) {

                    // set vl

                    ConfigUtils.setVl(p.getName(), num, false, 1);

                    // set log
                    LogUtils.logToFile(ChatUtils.logReplace(UwUPunishments.instance.getConfig().getString("log.punish"),
                            dtf.format(now), sender.getName(), p.getName(), args[1], ConfigManager.instance.getPlayers()
                                    .getInt("Punishments." + num + ".Level." + p.getName())));

                    // send message

                    sender.sendMessage(
                            ChatUtils.phReplace(Vars.ps_msg, p.getName(), args[1], (int) ConfigManager.instance
                                    .getPlayers().get("Punishments." + num + ".Level." + p.getName())));

                    // do command
                    ConfigUtils.doCmd(p.getName(),false, num, args[1]);

                    ConfigManager.instance.savePlayers();
                    return;
                }
            }
            sender.sendMessage(ChatUtils.colorize(ChatUtils.phReplace(Vars.no_type, args[0], args[2], 0)));

        } else {
            sender.sendMessage(ChatUtils.colorize(ChatUtils.phReplace(Vars.no_player, args[0], args[2], 0)));
        }
    }
}
