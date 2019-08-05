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
				if (args.length <= 2) {
					sender.sendMessage(ChatColor.RED + "UwU >> Usage: /punish <player> <broadcast> <reason>");
					return true;
				}
				for (Player p : Bukkit.getOnlinePlayers()) {

					if (args[0].equalsIgnoreCase(p.getName())) {
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
									Main.instance.getConfig().getString("no_type"), p.getName(), args[2], 0)));
						}
						return true;
					}
				}
				sender.sendMessage(ChatUtils.colorize(
						ChatUtils.phReplace(Main.instance.getConfig().getString("no_player"), args[0], args[2], 0)));
			} else {
				sender.sendMessage(Vars.no_perm);
			}
		}
		if (cmd.getName().equalsIgnoreCase("fo") || cmd.getName().equalsIgnoreCase("forgive")) {
			if (sender.hasPermission("uwu.forgive.use")) {
				if (args.length <= 1) {
					sender.sendMessage(ChatColor.RED + "UwU >> Usage: /forgive <player> <reason>");
					return true;
				}
				for (Player p : Bukkit.getOnlinePlayers()) {

					if (args[0].equalsIgnoreCase(p.getName())) {
						if (Main.instance.getConfig().get("types." + args[1]) != null) {
							// set vl
							if (ConfigManager.instance.getPlayers()
									.get("Punishments." + args[1] + ".Level." + p.getUniqueId()) != null) {
								if (ConfigManager.instance.getPlayers()
										.getInt("Punishments." + args[1] + ".Level." + p.getUniqueId()) != 0) {

									ConfigManager.instance.getPlayers()
											.set("Punishments." + args[1] + ".Level." + p.getUniqueId(),
													ConfigManager.instance.getPlayers().getInt(
															("Punishments." + args[1] + ".Level." + p.getUniqueId()))
															- 1);
								}
							}
							// send message
							sender.sendMessage(ChatUtils.phReplace(Vars.forgive, p.getName(), args[1],
									ConfigManager.instance.getPlayers()
											.getInt("Punishments." + args[1] + ".Level." + p.getUniqueId())));
							ConfigManager.instance.savePlayers();
							// no type
						} else {
							sender.sendMessage(ChatUtils.colorize(ChatUtils.phReplace(
									Main.instance.getConfig().getString("no_type"), p.getName(), args[1], 0)));
						}
						return true;
					}
				}
				sender.sendMessage(ChatUtils.colorize(ChatUtils.phReplace(Vars.no_player, args[0], args[1], 0)));
			} else {
				sender.sendMessage(Vars.no_perm);
			}
		}
		if (cmd.getName().equalsIgnoreCase("weload")) {
			if (sender.hasPermission("uwu.pu.reload.use")) {
				Main.instance.reloadConfig();
				ConfigManager.instance.reloadPlayers();
				sender.sendMessage(ChatColor.GREEN + "Reloaded config.");
			} else {
				sender.sendMessage(Vars.no_perm);
			}
		}
		return true;
	}
}