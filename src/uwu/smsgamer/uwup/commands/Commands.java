package uwu.smsgamer.uwup.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import uwu.smsgamer.uwup.ConfigManager;
import uwu.smsgamer.uwup.Main;
import uwu.smsgamer.uwup.utils.ChatUtils;
import uwu.smsgamer.uwup.vars.Vars;

public class Commands implements CommandExecutor {

	public String cmd1 = "verbose";

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("pu") || cmd.getName().equalsIgnoreCase("punish")) {
			if (sender.hasPermission("uwu.punish.use")) {
				if (args.length == 0) {
					sender.sendMessage(Vars.prefix + "Usage: /punish <player> <is silent> <reason> [extra]");
					sender.sendMessage(Vars.prefix + "Possible reasons: hacks, banavoid, spam, other");
					sender.sendMessage(Vars.prefix + "[extra] is only in use when the reason is \"other\"");
					return true;
				}
				for (Player p : Bukkit.getOnlinePlayers()) {

					if (args[0].equalsIgnoreCase(p.getName())) {
						// test

						if (Main.instance.getConfig().get("types." + args[2]) != null) {
							// broadcast
							if (args[1].startsWith("t") || args[1].startsWith("y")) {
								Bukkit.broadcastMessage(ChatUtils.colorize(ChatUtils.phReplace(Vars.bc_msg, p.getName(),
										args[2], (int) ConfigManager.instance.getPlayers()
												.get("Punishments." + args[2] + ".Level." + p.getUniqueId()))));
							}
							// set vl
							ConfigManager.instance.getPlayers().set(
									"Punishments." + args[2] + ".Level." + p.getUniqueId(),
									ConfigManager.instance.getPlayers()
											.getInt(("Punishments." + args[2] + ".Level." + p.getUniqueId())) + 1);
							// send message
							sender.sendMessage(
									ChatUtils.phReplace(Vars.ps_msg, p.getName(), args[2], (int) ConfigManager.instance
											.getPlayers().get("Punishments." + args[2] + ".Level." + p.getUniqueId())));
							// do command
							if (Main.instance.getConfig()
									.get("types." + args[2] + "." + ConfigManager.instance.getPlayers()
											.get("Punishments." + args[2] + ".Level." + p.getUniqueId())) != null) {
								Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
										ChatUtils.colorize(ChatUtils.phReplace(
												(String) Main.instance.getConfig()
														.get("types." + args[2] + "."
																+ ConfigManager.instance.getPlayers()
																		.get("Punishments." + args[2] + ".Level."
																				+ p.getUniqueId())),
												p.getName(), args[2], (int) ConfigManager.instance.getPlayers()
														.get("Punishments." + args[2] + ".Level." + p.getUniqueId()))));
							}
							ConfigManager.instance.savePlayers();
							// No type
						} else {
							sender.sendMessage(ChatUtils.colorize(ChatUtils.phReplace(
									(String) Main.instance.getConfig().get("no_type"), p.getName(), args[2], 0)));
						}
					}

				}
			} else {
				int rand = (int) Math.floor(Math.random() * 9);
				if (rand == 0) {
					sender.sendMessage(Vars.prefix + "Really? Ya think ya got perms for dat buddy? Nah brah.");
				}
				if (rand == 1) {
					sender.sendMessage(ChatColor.RED
							+ "I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
				}
				if (rand == 2) {
					sender.sendMessage(ChatColor.DARK_RED + "You do not have access to that command.");
				}
				if (rand == 3) {
					sender.sendMessage(Vars.prefix + "OwOooof. You don't got perms buddy, sorry.");
				}
				if (rand == 4) {
					sender.sendMessage(ChatColor.RED + "No permission");
				}
				if (rand == 5) {
					sender.sendMessage("Unkown command. Type \"/help\" for help.");
				}
				if (rand == 6) {
					sender.sendMessage(ChatColor.YELLOW + "Oof");
				}
				if (rand == 7) {
					sender.sendMessage(ChatColor.MAGIC + "Hi, I exist.");
				}
				if (rand == 8) {
					sender.sendMessage(
							Vars.prefix + ChatColor.DARK_RED + ChatColor.BOLD + "“" + ChatColor.DARK_RED + "w”");
				}

			}
		}
		if (cmd.getName().equalsIgnoreCase("fo") || cmd.getName().equalsIgnoreCase("forgive")) {
			if (sender.hasPermission("uwu.forgive.use")) {

			}
		}
		return true;
	}
}
