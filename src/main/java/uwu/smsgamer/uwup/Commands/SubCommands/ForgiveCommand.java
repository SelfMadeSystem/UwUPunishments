package uwu.smsgamer.uwup.Commands.SubCommands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import uwu.smsgamer.uwup.ConfigManager.ConfigManager;
import uwu.smsgamer.uwup.Utils.ChatUtils;
import uwu.smsgamer.uwup.Utils.ConfigUtils;
import uwu.smsgamer.uwup.Utils.LogUtils;
import uwu.smsgamer.uwup.UwUPunishments;
import uwu.smsgamer.uwup.Vars.Vars;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Forgive command.
 */
public class ForgiveCommand {
    /**
     * Executes the Forgive command.
     *
     * @param sender  Source of the command
     * @param cmd Command which was executed
     * @param label   Alias of the command which was used
     * @param args    Passed command arguments
     */
    public static void forgiveCommand(CommandSender sender, Command cmd, String label, String[] args){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        if (args.length <= 1) {
            sender.sendMessage(ChatColor.RED + "UwU >> Usage: /" + label + " <player> <reason>");
            return;
        }
        String p = args[0];
        try{
            for (String num : UwUPunishments.instance.getConfig().getConfigurationSection("types").getKeys(false)) {
                if (UwUPunishments.instance.getConfig().getStringList("types." + num + ".alias").contains(args[1])) {
                    ConfigManager.instance.getPlayers().get("Punishments."+num+"."+args[0]);
                }
            }
        }catch (NullPointerException e){
            sender.sendMessage(ChatUtils.phReplace(Vars.forgive_not_found, args[1], "%reason%", 0));
            return;
        }
        if (p != null) {
            for (String num : UwUPunishments.instance.getConfig().getConfigurationSection("types").getKeys(false)) {
                if (UwUPunishments.instance.getConfig().getStringList("types." + num + ".alias").contains(args[1])) {
                    // set vl

                    ConfigUtils.setVl(p, num, false, -1);

                    // set log
                    LogUtils.logToFile(ChatUtils.logReplace(UwUPunishments.instance.getConfig().getString("log.punish"),
                            dtf.format(now), sender.getName(), p, args[1], ConfigManager.instance.getPlayers()
                                    .getInt("Punishments." + num + ".Level." + p)));

                    // send message

                    sender.sendMessage(
                            ChatUtils.phReplace(Vars.ps_msg, p, args[1], (int) ConfigManager.instance
                                    .getPlayers().get("Punishments." + num + ".Level." + p)));

                    // do command
                    ConfigUtils.doCmd(p,true, num, args[1]);

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
