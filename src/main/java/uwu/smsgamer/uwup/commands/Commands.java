package uwu.smsgamer.uwup.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import uwu.smsgamer.uwup.ConfigManager;
import uwu.smsgamer.uwup.UwUP;
import uwu.smsgamer.uwup.utils.ChatUtils;
import uwu.smsgamer.uwup.utils.LogUtils;
import uwu.smsgamer.uwup.vars.Vars;

/**
 * Main command executor.
 */
public class Commands implements CommandExecutor {
	/**
	 * Main command executor.
	 *
	 * @param sender The user (sender, player, console) that's executing this command.
	 * @param cmd The command that is being executed (not alias).
	 * @param label The command that the sender is executing (could be alias).
	 * @param args The arguments of the command.
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		if (cmd.getName().equalsIgnoreCase("pu") || cmd.getName().equalsIgnoreCase("punish")) {
			if (sender.hasPermission("uwu.punish.use")) {
				if (args.length <= 2) {
					sender.sendMessage(ChatColor.RED + "UwU >> Usage: /punish <player> <broadcast> <reason>");
					return true;
				}

				Player p = Bukkit.getPlayer(args[0]);

				if (p != null && !p.hasPermission("uwu.pu.exempt")) {
					for (String num : UwUP.instance.getConfig().getConfigurationSection("types").getKeys(false)) {
						if (UwUP.instance.getConfig().getStringList("types." + num + ".alias").contains(args[2])) {

							// broadcast

							if (args[1].startsWith("t") || args[1].startsWith("y")) {
								Bukkit.broadcastMessage(ChatUtils.colorize(ChatUtils.phReplace(Vars.bc_msg, p.getName(),
										args[2], (int) ConfigManager.instance.getPlayers()
												.get("Punishments." + num + ".Level." + p.getUniqueId()))));
							}
							// set vl

							ChatUtils.pP(p, num, 1);

							// set log
							LogUtils.logToFile(ChatUtils.logReplace(UwUP.instance.getConfig().getString("log.punish"),
									dtf.format(now), sender.getName(), p.getName(), ConfigManager.instance.getPlayers()
											.getInt("Punishments." + num + ".Level." + p.getUniqueId())));

							// send message

							sender.sendMessage(
									ChatUtils.phReplace(Vars.ps_msg, p.getName(), args[2], (int) ConfigManager.instance
											.getPlayers().get("Punishments." + num + ".Level." + p.getUniqueId())));

							// do command
							for (int i = 0; i < UwUP.instance.getConfig()
									.getStringList("types." + num + ".punishments."
											+ ConfigManager.instance.getPlayers()
													.get("Punishments." + num + ".Level." + p.getUniqueId()))
									.toArray().length; i++) {
								Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
										ChatUtils.phReplace(
												ChatUtils.colorize(UwUP.instance.getConfig()
														.getStringList("types." + num + ".punishments."
																+ ConfigManager.instance.getPlayers()
																		.get("Punishments." + num + ".Level."
																				+ p.getUniqueId()))
														.toArray()[i] + ""),
												p.getName(), args[1], ConfigManager.instance.getPlayers()
														.getInt("Punishments." + num + ".Level." + p.getUniqueId())));
							}

							ConfigManager.instance.savePlayers();
							return true;
						}
					}
					sender.sendMessage(ChatUtils.colorize(
							ChatUtils.phReplace(UwUP.instance.getConfig().getString("no_type"), args[0], args[2], 0)));

				} else {
					sender.sendMessage(ChatUtils.colorize(ChatUtils
							.phReplace(UwUP.instance.getConfig().getString("no_player"), args[0], args[2], 0)));
				}
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
				Player p = Bukkit.getPlayer(args[0]);
				if (p != null && !p.hasPermission("uwu.pu.exempt")) {
					for (String num : UwUP.instance.getConfig().getConfigurationSection("types").getKeys(false)) {
						if (UwUP.instance.getConfig().getStringList("types." + num + ".alias").contains(args[1])) {

							// set vl
							if (ConfigManager.instance.getPlayers()
									.get("Punishments." + num + ".Level." + p.getUniqueId()) != null) {
								if (ConfigManager.instance.getPlayers()
										.getInt("Punishments." + num + ".Level." + p.getUniqueId()) > 0) {
									ChatUtils.pP(p, num, -1);
								}
							}

							// send message

							sender.sendMessage(
									ChatUtils.phReplace(Vars.forgive, p.getName(), args[1], ConfigManager.instance
											.getPlayers().getInt("Punishments." + num + ".Level." + p.getUniqueId())));

							// set log
							LogUtils.logToFile(ChatUtils.logReplace(UwUP.instance.getConfig().getString("log.forgive"),
									dtf.format(now), sender.getName(), p.getName(), ConfigManager.instance.getPlayers()
											.getInt("Punishments." + num + ".Level." + p.getUniqueId())));

							// Do command

							for (int i = 0; i < UwUP.instance.getConfig()
									.getStringList("types." + num + ".forgivements."
											+ ConfigManager.instance.getPlayers()
													.get("Punishments." + num + ".Level." + p.getUniqueId()))
									.toArray().length; i++) {
								Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
										ChatUtils.phReplace(
												ChatUtils.colorize(UwUP.instance.getConfig()
														.getStringList("types." + num + ".forgivements."
																+ ConfigManager.instance.getPlayers()
																		.get("Punishments." + num + ".Level."
																				+ p.getUniqueId()))
														.toArray()[i] + ""),
												p.getName(), args[1], ConfigManager.instance.getPlayers()
														.getInt("Punishments." + num + ".Level." + p.getUniqueId())));
							}

							ConfigManager.instance.savePlayers();
							return true;
						}
					}
					sender.sendMessage(ChatUtils.colorize(ChatUtils.phReplace(Vars.no_type, args[0], args[1], 0)));
					return true;
				} else {
					sender.sendMessage(ChatUtils.colorize(ChatUtils.phReplace(Vars.no_player, args[0], args[1], 0)));
				}
			} else {
				sender.sendMessage(Vars.no_perm);
			}
		}

		if (cmd.getName().equalsIgnoreCase("cvl") || cmd.getName().equalsIgnoreCase("checkvl")
				|| cmd.getName().equalsIgnoreCase("checkviolations")) {
			if (sender.hasPermission("uwu.checkvl.use")) {
				if (args.length == 0) {
					sender.sendMessage(ChatColor.RED + "UwU >> Usage: /checkviolations <player> [reason]");
					return true;
				}
				Player p = Bukkit.getPlayer(args[0]);
				if (p != null && p.isOnline()) {
					for (String num : UwUP.instance.getConfig().getConfigurationSection("types").getKeys(false)) {
						if (args.length != 1) {
							if (UwUP.instance.getConfig().getStringList("types." + num + ".alias").contains(args[1])) {
								sender.sendMessage(ChatUtils.phReplace(Vars.check_vl, p.getName(), num,
										ConfigManager.instance.getPlayers()
												.getInt("Punishments." + num + ".Level." + p.getUniqueId())));
								return true;
							}
						} else {
							sender.sendMessage(
									ChatUtils.phReplace(Vars.check_vl, p.getName(), num, ConfigManager.instance
											.getPlayers().getInt("Punishments." + num + ".Level." + p.getUniqueId())));
						}
					}
					return true;
				}
				sender.sendMessage(ChatUtils.colorize(ChatUtils.phReplace(Vars.no_player, args[0], args[1], 0)));
			} else {
				sender.sendMessage(Vars.no_perm);
			}
		}

		if (cmd.getName().equalsIgnoreCase("svl") || cmd.getName().equalsIgnoreCase("setvl")
				|| cmd.getName().equalsIgnoreCase("setviolations")) {
			if (sender.hasPermission("uwu.setvl.use")) {
				if (args.length <= 3) {
					sender.sendMessage(
							ChatColor.RED + "UwU >> Usage: /setviolations <player> <reason> <pu,fo,none> <amount>");
					return true;
				}

				Player p = Bukkit.getPlayer(args[0]);
				if (p != null && !p.hasPermission("uwu.pu.exempt")) {
					for (String num : UwUP.instance.getConfig().getConfigurationSection("types").getKeys(false)) {
						if (UwUP.instance.getConfig().getStringList("types." + num + ".alias").contains(args[1])) {

							// set vl
							try {
								ConfigManager.instance.getPlayers().set(
										"Punishments." + num + ".Level." + p.getUniqueId(), Integer.parseInt(args[3]));
							} catch (NumberFormatException e) {
								sender.sendMessage(ChatColor.RED + "UwU >> " + args[3] + " is not a valid number.");
								return true;
							}

							// set log
							LogUtils.logToFile(ChatUtils.logReplace(UwUP.instance.getConfig().getString("log.setvl"),
									dtf.format(now), sender.getName(), p.getName(), ConfigManager.instance.getPlayers()
											.getInt("Punishments." + num + ".Level." + p.getUniqueId())));

							// send message

							sender.sendMessage(
									ChatUtils.phReplace(Vars.set_vl, p.getName(), args[1], ConfigManager.instance
											.getPlayers().getInt("Punishments." + num + ".Level." + p.getUniqueId())));
							// Do command

							if (args[2].startsWith("p")) {
								for (int i = 0; i < UwUP.instance.getConfig()
										.getStringList("types." + num + ".punishments."
												+ ConfigManager.instance.getPlayers()
														.get("Punishments." + num + ".Level." + p.getUniqueId()))
										.toArray().length; i++) {
									Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
											ChatUtils.phReplace(
													ChatUtils.colorize(UwUP.instance.getConfig()
															.getStringList("types." + num + ".punishments."
																	+ ConfigManager.instance.getPlayers()
																			.get("Punishments." + num + ".Level."
																					+ p.getUniqueId()))
															.toArray()[i] + ""),
													p.getName(), args[1], ConfigManager.instance.getPlayers().getInt(
															"Punishments." + num + ".Level." + p.getUniqueId())));
								}
							}

							if (args[2].startsWith("f")) {
								for (int i = 0; i < UwUP.instance.getConfig()
										.getStringList("types." + num + ".forgivements."
												+ ConfigManager.instance.getPlayers()
														.get("Punishments." + num + ".Level." + p.getUniqueId()))
										.toArray().length; i++) {
									Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
											ChatUtils.phReplace(
													ChatUtils.colorize(UwUP.instance.getConfig()
															.getStringList("types." + num + ".forgivements."
																	+ ConfigManager.instance.getPlayers()
																			.get("Punishments." + num + ".Level."
																					+ p.getUniqueId()))
															.toArray()[i] + ""),
													p.getName(), args[1], ConfigManager.instance.getPlayers().getInt(
															"Punishments." + num + ".Level." + p.getUniqueId())));
								}
							}

							ConfigManager.instance.savePlayers();
							return true;
						}
					}
					sender.sendMessage(ChatUtils.colorize(
							ChatUtils.phReplace(UwUP.instance.getConfig().getString("no_type"), args[0], args[2], 0)));

				} else {
					sender.sendMessage(ChatUtils.colorize(ChatUtils
							.phReplace(UwUP.instance.getConfig().getString("no_player"), args[0], args[2], 0)));
				}
			} else {
				sender.sendMessage(Vars.no_perm);
			}
		}
		return true;
	}




}