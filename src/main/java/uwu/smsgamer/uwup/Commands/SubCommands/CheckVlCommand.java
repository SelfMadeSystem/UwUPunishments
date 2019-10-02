package uwu.smsgamer.uwup.Commands.SubCommands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import uwu.smsgamer.uwup.ConfigManager.ConfigManager;
import uwu.smsgamer.uwup.Utils.ChatUtils;
import uwu.smsgamer.uwup.UwUPunishments;
import uwu.smsgamer.uwup.Vars.Vars;

/**
 * CheckVl command.
 */
public class CheckVlCommand {
    /**
     * Executes the CheckViolation command.
     *
     * @param sender Source of the command
     * @param cmd    Command which was executed
     * @param label  Alias of the command which was used
     * @param args   Passed command arguments
     */
    public static void checkVlCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "UwU >> Usage: /" + label + " <player> [reason]");
            return;
        }
        Player p = Bukkit.getPlayer(args[0]);
        if (p != null && p.isOnline()) {
            for (String num : UwUPunishments.instance.getConfig().getConfigurationSection("types").getKeys(false)) {
                if (args.length != 1) {
                    if (UwUPunishments.instance.getConfig().getStringList("types." + num + ".alias").contains(args[1])) {
                        sender.sendMessage(ChatUtils.phReplace(Vars.check_vl, p.getName(), num,
                                ConfigManager.instance.getPlayers()
                                        .getInt("Punishments." + num + ".Level." + p.getUniqueId())));
                        return;
                    }
                } else {
                    sender.sendMessage(
                            ChatUtils.phReplace(Vars.check_vl, p.getName(), num, ConfigManager.instance
                                    .getPlayers().getInt("Punishments." + num + ".Level." + p.getUniqueId())));
                }
            }
            return;
        }
        sender.sendMessage(ChatUtils.colorize(ChatUtils.phReplace(Vars.no_player, args[0], args[1], 0)));
    }
}
