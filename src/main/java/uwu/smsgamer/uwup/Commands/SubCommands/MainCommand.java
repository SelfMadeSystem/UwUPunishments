package uwu.smsgamer.uwup.Commands.SubCommands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import uwu.smsgamer.uwup.ConfigManager.ConfigManager;
import uwu.smsgamer.uwup.Utils.ChatUtils;
import uwu.smsgamer.uwup.UwUPunishments;
import uwu.smsgamer.uwup.Vars.Vars;

/**
 * CheckVl command.
 */
public class MainCommand {
    /**
     * Executes the CheckViolation command.
     *
     * @param sender Source of the command
     * @param cmd    Command which was executed
     * @param label  Alias of the command which was used
     * @param args   Passed command arguments
     */
    public static void mainCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!sender.hasPermission("uwu.main.use")){
            sender.sendMessage(Vars.no_perm);
            return;
        }
        sender.sendMessage("This command does nothing as of right now.");
        /*if(args.length == 0){
            sender.sendMessage(ChatColor.RED + "/"+label+" [reload] [...]");
            return;
        }
        if(args[0].equalsIgnoreCase("reload")){
            if(sender.hasPermission("uwu.reload")){
                try {
                    UwUPunishments.instance.rC();
                    UwUPunishments.instance.reloadConfig();
                    sender.sendMessage(
                            ChatUtils.phReplace(Vars.reload_success, sender.getName(), "%reason%", 0));
                }catch (Exception e){
                    e.printStackTrace();
                    sender.sendMessage(
                            ChatUtils.phReplace(Vars.reload_error, sender.getName(), "%reason%", 0));
                }
            }else {
                sender.sendMessage(Vars.no_perm);
                return;
            }
        }*/
    }
}
