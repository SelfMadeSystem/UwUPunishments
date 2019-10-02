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
 * SetVl command.
 */
public class SetVlCommand {
    /**
     * Executes the SetViolation command.
     *
     * @param sender  Source of the command
     * @param cmd Command which was executed
     * @param label   Alias of the command which was used
     * @param args    Passed command arguments
     */
    public static void setVlCommand(CommandSender sender, Command cmd, String label, String[] args) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        if (args.length <= 2) {
            sender.sendMessage(ChatColor.RED + "UwU >> Usage: /" + label + " <player> <reason> <amount> [punish/forgive/none]");
            return;
        }
        Player p = Bukkit.getPlayer(args[0]);
        if (p != null && !p.hasPermission("uwu.pu.exempt")) {
            for (String num : UwUPunishments.instance.getConfig().getConfigurationSection("types").getKeys(false)) {
                if (UwUPunishments.instance.getConfig().getStringList("types." + num + ".alias").contains(args[1])) {
                    //svl Sms_Gamer spam 7 fo

                    // Set vl
                    ConfigUtils.setVl(p.getName(), num, true, Integer.parseInt(args[2]));

                    // Do command

                    if(args.length>3){
                        if(args[3].startsWith("f")){
                        ConfigUtils.doCmd(args[0], true, num, args[1]);
                    }
                        if(args[3].startsWith("p")){
                            ConfigUtils.doCmd(args[0], false, num, args[1]);
                        }
                    }


                    // set log
                    LogUtils.logToFile(ChatUtils.logReplace(UwUPunishments.instance.getConfig().getString("log.setvl"),
                            dtf.format(now), sender.getName(), p.getName(), args[1], ConfigManager.instance.getPlayers()
                                    .getInt("Punishments." + num + ".Level." + p.getName())));

                    // Send message
                    sender.sendMessage(
                            ChatUtils.phReplace(Vars.set_vl, p.getName(), args[1],
                                    ConfigManager.instance.getPlayers().getInt("Punishments." + num + ".Level." + p.getName())));
                    return;
                }
            }
            sender.sendMessage(ChatUtils.colorize(ChatUtils.phReplace(Vars.no_type, args[0], args[2], 0)));

        } else {
            sender.sendMessage(ChatUtils.colorize(ChatUtils.phReplace(Vars.no_player, args[0], args[2], 0)));
        }
    }
}
