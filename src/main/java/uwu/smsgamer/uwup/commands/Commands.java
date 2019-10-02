package uwu.smsgamer.uwup.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import uwu.smsgamer.uwup.Commands.SubCommands.CheckVlCommand;
import uwu.smsgamer.uwup.Commands.SubCommands.ForgiveCommand;
import uwu.smsgamer.uwup.Commands.SubCommands.PunishCommand;
import uwu.smsgamer.uwup.Commands.SubCommands.SetVlCommand;
import uwu.smsgamer.uwup.Utils.ChatUtils;
import uwu.smsgamer.uwup.Vars.Vars;

/**
 * Main command executor.
 */
public class Commands implements CommandExecutor {
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
}
