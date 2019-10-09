package uwu.smsgamer.uwup.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import uwu.smsgamer.uwup.Commands.SubCommands.*;
import uwu.smsgamer.uwup.Utils.ChatUtils;
import uwu.smsgamer.uwup.UwUPunishments;
import uwu.smsgamer.uwup.Vars.Vars;

import java.util.ArrayList;
import java.util.List;

/**
 * Main command executor.
 */
public class Commands implements CommandExecutor, TabCompleter {
    /**
     * Executes the given command, returning its success.
     * <br>
     * If false is returned, then the "usage" plugin.yml entry for this command
     * (if defined) will be sent to the player.
     *
     * @param sender  Source of the command
     * @param cmd Command which was executed
     * @param label   Alias of the command which was used
     * @param args    Passed command arguments
     * @return true if a valid command, otherwise false
     */
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("uwupunishments")) {
            MainCommand.mainCommand(sender, cmd, label, args);
            return true;
        }
        commandSelector(sender, cmd, label, args);
        return true;
    }

    public static boolean commandSelector(CommandSender sender, Command cmd, String label, String[] args){
        if(cmd.getName().equalsIgnoreCase("punish")&&sender.hasPermission("uwu.punish.use")){
            PunishCommand.punishCommand(sender, cmd, label, args);
            return true;
        }else if(!sender.hasPermission("uwu.punish.use")){
            sender.sendMessage(ChatUtils.phReplace(Vars.no_perm, sender.getName(), label, 0));
            return true;
        }
        if(cmd.getName().equalsIgnoreCase("forgive")&&sender.hasPermission("uwu.forgive.use")){
            ForgiveCommand.forgiveCommand(sender, cmd, label, args);
            return true;
        }else if(!sender.hasPermission("uwu.forgive.use")){
            sender.sendMessage(ChatUtils.phReplace(Vars.no_perm, sender.getName(), label, 0));
            return true;
        }
        if(cmd.getName().equalsIgnoreCase("setviolations")&&sender.hasPermission("uwu.setvl.use")){
            SetVlCommand.setVlCommand(sender, cmd, label, args);
            return true;
        }else if(!sender.hasPermission("uwu.setvl.use")){
            sender.sendMessage(ChatUtils.phReplace(Vars.no_perm, sender.getName(), label, 0));
            return true;
        }
        if(cmd.getName().equalsIgnoreCase("checkviolations")&&sender.hasPermission("uwu.checkvl.use")){
            CheckVlCommand.checkVlCommand(sender, cmd, label, args);
            return true;
        }else if(!sender.hasPermission("uwu.checkvl.use")){
            sender.sendMessage(ChatUtils.phReplace(Vars.no_perm, sender.getName(), label, 0));
            return true;
        }
        sender.sendMessage(ChatUtils.phReplace(Vars.no_perm, sender.getName(), label, 0));
        return true;
    }

    /**
     * Requests a list of possible completions for a command argument.
     *
     * @param sender  Source of the command.  For players tab-completing a
     *                command inside of a command block, this will be the player, not
     *                the command block.
     * @param command Command which was executed
     * @param alias   The alias used
     * @param args    The arguments passed to the command, including final
     *                partial argument to be completed and command label
     * @return A List of possible completions for the final argument, or null
     * to default to the command executor
     */
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if((command.getName().equalsIgnoreCase("punish")&&!sender.hasPermission("uwu.punish.use")) ||
        (command.getName().equalsIgnoreCase("forgive")&&!sender.hasPermission("uwu.forgive.use")) ||
        (command.getName().equalsIgnoreCase("setviolations")&&!sender.hasPermission("uwu.setvl.use")) ||
        (command.getName().equalsIgnoreCase("checkviolations")&&!sender.hasPermission("uwu.checkvl.use")))
            return null;
        List<String> tab = new ArrayList<>();
        if(command.getName().equalsIgnoreCase("uwupunishments")){
        }else{
            if(args.length == 1){
                return null;
            }
            if(args.length == 2 && !command.getName().equalsIgnoreCase("checkviolations")){
                tab.addAll(UwUPunishments.instance.getConfig().getConfigurationSection("types").getKeys(false));
            }
            if(args.length == 3 && command.getName().equalsIgnoreCase("setviolations")){
                tab.add("punish");
                tab.add("forgive");
                tab.add("none");
            }
        }
        return tab;
    }
}
