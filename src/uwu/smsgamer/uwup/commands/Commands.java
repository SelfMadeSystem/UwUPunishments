package uwu.smsgamer.uwup.commands;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		if (cmd.getName().equalsIgnoreCase("pu") || cmd.getName().equalsIgnoreCase("punish")) {
			if (sender.hasPermission("uwu.punish.use")) {
				if (args.length <= 2) {
					sender.sendMessage(ChatColor.RED + "UwU >> Usage: /punish <player> <broadcast> <reason>");
					return true;
				}
				if (Bukkit.getPlayer(args[0]) != null) {
					Player p = Bukkit.getPlayer(args[0]);
					for (String num : Main.instance.getConfig().getConfigurationSection("types").getKeys(false)) {
						if (Main.instance.getConfig().getStringList("types." + num + ".alias").contains(args[2])) {

							// broadcast

							if (args[1].startsWith("t") || args[1].startsWith("y")) {
								Bukkit.broadcastMessage(ChatUtils.colorize(ChatUtils.phReplace(Vars.bc_msg, p.getName(),
										args[2], (int) ConfigManager.instance.getPlayers()
												.get("Punishments." + num + ".Level." + p.getUniqueId()))));
							}

							// set vl

							ConfigManager.instance.getPlayers().set("Punishments." + num + ".Level." + p.getUniqueId(),
									ConfigManager.instance.getPlayers()
											.getInt(("Punishments." + num + ".Level." + p.getUniqueId())) + 1);

							// set log

							logToFile(dtf.format(now) + " | " + sender.getName() + " punished " + p.getName() + " for "
									+ args[2] + ".");

							// send message

							sender.sendMessage(
									ChatUtils.phReplace(Vars.ps_msg, p.getName(), args[2], (int) ConfigManager.instance
											.getPlayers().get("Punishments." + num + ".Level." + p.getUniqueId())));

							// do command

							if (Main.instance.getConfig().get("types." + num + "." + ConfigManager.instance.getPlayers()
									.get("Punishments." + num + ".Level." + p.getUniqueId())) != null) {
								Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
										ChatUtils
												.colorize(ChatUtils.phReplace(
														(String) Main.instance.getConfig()
																.get("types." + num + "."
																		+ ConfigManager.instance.getPlayers()
																				.get("Punishments." + num + ".Level."
																						+ p.getUniqueId())),
														p.getName(), num, (int) ConfigManager.instance.getPlayers().get(
																"Punishments." + num + ".Level." + p.getUniqueId()))));
							}

							ConfigManager.instance.savePlayers();
							return true;
						}
					}
					sender.sendMessage(ChatUtils.colorize(
							ChatUtils.phReplace(Main.instance.getConfig().getString("no_type"), args[0], args[2], 0)));

				} else {
					sender.sendMessage(ChatUtils.colorize(ChatUtils
							.phReplace(Main.instance.getConfig().getString("no_player"), args[0], args[2], 0)));
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
				if (Bukkit.getPlayer(args[0]) != null) {
					Player p = Bukkit.getPlayer(args[0]);
					for (String num : Main.instance.getConfig().getConfigurationSection("types").getKeys(false)) {
						if (Main.instance.getConfig().getStringList("types." + num + ".alias").contains(args[1])) {

							// set vl
							if (ConfigManager.instance.getPlayers()
									.get("Punishments." + num + ".Level." + p.getUniqueId()) != null) {
								if (ConfigManager.instance.getPlayers()
										.getInt("Punishments." + num + ".Level." + p.getUniqueId()) != 0) {
									ConfigManager.instance.getPlayers().set(
											"Punishments." + num + ".Level." + p.getUniqueId(),
											ConfigManager.instance.getPlayers()
													.getInt(("Punishments." + num + ".Level." + p.getUniqueId())) - 1);
								}
							}

							// send message

							sender.sendMessage(
									ChatUtils.phReplace(Vars.forgive, p.getName(), args[1], ConfigManager.instance
											.getPlayers().getInt("Punishments." + num + ".Level." + p.getUniqueId())));

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

		if (cmd.getName().equalsIgnoreCase("cv") || cmd.getName().equalsIgnoreCase("checkvl")
				|| cmd.getName().equalsIgnoreCase("checkviolations")) {
			if (sender.hasPermission("uwu.checkvl.use")) {
				if (args.length == 0) {
					sender.sendMessage(ChatColor.RED + "UwU >> Usage: /checkviolations <player> [reason]");
					return true;
				}
				if (Bukkit.getPlayer(args[0]) != null) {
					Player p = Bukkit.getPlayer(args[0]);
					for (String num : Main.instance.getConfig().getConfigurationSection("types").getKeys(false)) {
						if (args.length != 1) {
							if (Main.instance.getConfig().getStringList("types." + num + ".alias").contains(args[1])) {
								sender.sendMessage(ChatUtils.phReplace(Vars.check_vl, p.getName(), num,
										ConfigManager.instance.getPlayers()
												.getInt("Punishments." + num + ".Level." + p.getUniqueId())));
								return true;
							}
						}else {
							sender.sendMessage(ChatUtils.phReplace(Vars.check_vl, p.getName(), num,
									ConfigManager.instance.getPlayers()
											.getInt("Punishments." + num + ".Level." + p.getUniqueId())));
						}
					}
					return true;
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
				sender.sendMessage(ChatColor.GREEN + "Maybe reloaded config.");
			} else {
				sender.sendMessage(Vars.no_perm);
			}
		}
		return true;
	}

	public void logToFile(String message) {

		try {
			File dataFolder = Main.instance.getDataFolder();

			if (!dataFolder.exists()) {
				dataFolder.mkdir();
			}

			File saveTo = new File(Main.instance.getDataFolder(), "Punishment.log");
			if (!saveTo.exists()) {
				saveTo.createNewFile();
			}

			FileWriter fw = new FileWriter(saveTo, true);

			PrintWriter pw = new PrintWriter(fw);

			pw.println(message);

			pw.flush();

			pw.close();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

}