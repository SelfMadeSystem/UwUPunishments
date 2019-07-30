package uwu.smsgamer.uwup.commands;

import org.bukkit.Bukkit;

import java.util.Date;

import org.bukkit.BanList.Type;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import uwu.smsgamer.uwup.Main;
import uwu.smsgamer.uwup.vars.Vars;

public class Commands implements CommandExecutor {

	public String cmd1 = "verbose";

	

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("pu") || cmd.getName().equalsIgnoreCase("punish")) {
			if (args.length == 0) {
				sender.sendMessage(Vars.prefix + "Usage: /punish <player> <is silent> <reason> [extra]");
				sender.sendMessage(Vars.prefix + "Possible reasons: hacks, banavoid, spam, other");
				sender.sendMessage(Vars.prefix + "[extra] is only in use when the reason is \"other\"");
				return true;
			}
			for (Player p : Bukkit.getOnlinePlayers()) {

				if (args[0].equalsIgnoreCase(p.getName())) {
					// yes or true
					if (args[1].startsWith("n") || args[1].startsWith("f")) {
						// hax
						if (args[2].startsWith("h")) {
							Bukkit.broadcastMessage(Vars.prefix + args[0] + " has been punished for hacking.");
							if (Main.instance.getConfig().get("Punishments.Hax.Level." + p.getName()) == null) {
								Main.instance.getConfig().set("Punishments.Hax.Level." + p.getName(),
										Main.instance.getConfig().getInt(("Punishments.Hax.Level." + p.getName())) + 1);
								p.kickPlayer(Vars.prefix + "Kicked for hacking by a moderator.");
							} else {
								Main.instance.getConfig().set("Punishments.Hax.Level." + p.getName(),
										Main.instance.getConfig().getInt(("Punishments.Hax.Level." + p.getName())) + 1);
								if (Main.instance.getConfig().getInt("Punishments.Hax.Level." + p.getName()) <= 3) {
									p.kickPlayer(
											Vars.prefix + "Kicked for hacking by a moderator. Hacking punishments: "
													+ Main.instance.getConfig().getInt("Punishments.Hax.Level." + p.getName()) + "h");
								} else {
									p.kickPlayer("Banned for hacking by a moderator.");
								}
								if (Main.instance.getConfig().getInt("Punishments.Hax.Level." + p.getName()) == 4) {
									Date date = new Date(System.currentTimeMillis() + 60 * 60 * 1000);
									Bukkit.getBanList(Type.NAME).addBan(p.getName(),
											"Banned for hacking by a moderator.", date, "Moderator");
								}
								if (Main.instance.getConfig().getInt("Punishments.Hax.Level." + p.getName()) == 5) {
									Date date = new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000);
									Bukkit.getBanList(Type.NAME).addBan(p.getName(),
											"Banned for hacking by a moderator.", date, "Moderator");
								}
								if (Main.instance.getConfig().getInt("Punishments.Hax.Level." + p.getName()) == 6) {
									Date date = new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000);
									Bukkit.getBanList(Type.NAME).addBan(p.getName(),
											"Banned for hacking by a moderator.", date, "Moderator");
								}
								if (Main.instance.getConfig().getInt("Punishments.Hax.Level." + p.getName()) == 7) {
									Date date = new Date(System.currentTimeMillis() + 30 * 7 * 24 * 60 * 60 * 1000);
									Bukkit.getBanList(Type.NAME).addBan(p.getName(),
											"Banned for hacking by a moderator.", date, "Moderator");
								}
								if (Main.instance.getConfig().getInt("Punishments.Hax.Level." + p.getName()) >= 8) {
									Bukkit.getBanList(Type.NAME).addBan(p.getName(),
											"Banned for hacking by a moderator.", null, "Moderator");
								}
							}
							sender.sendMessage(Vars.prefix + "Successfully punished " + p.getName()
									+ " for hacking. Hacking punishments:"
									+ Main.instance.getConfig().getInt("Punishments.Hax.Level." + p.getName()));
						}
						if (args[2].startsWith("b")) {
							Bukkit.broadcastMessage(Vars.prefix + args[0] + " has been banned for ban evasion.");
							Bukkit.banIP(p.getAddress().getAddress().getHostAddress());
							p.kickPlayer(Vars.prefix + "Banned for ban evasion by a moderator.");
							sender.sendMessage(
									Vars.prefix + "Successfully punished " + p.getName() + " for ban evasion.");
						}
						if (args[2].startsWith("s")) {
							Bukkit.broadcastMessage(Vars.prefix + args[0] + " has been punished for spam.");
							if (Main.instance.getConfig().get("Punishments.Spam.Level." + p.getName()) == null) {
								Main.instance.getConfig().set("Punishments.Spam.Level." + p.getName(),
										Main.instance.getConfig().getInt(("Punishments.Spam.Level." + p.getName())) + 1);
								p.sendMessage(Vars.prefix + "You have been warned for spam.");
							} else {
								Main.instance.getConfig().set("Punishments.Spam.Level." + p.getName(),
								Main.instance.getConfig().getInt("Punishments.Spam.Level." + p.getName()) + 1);
								if (Main.instance.getConfig().getInt("Punishments.Spam.Level." + p.getName()) >= 1
										&& Main.instance.getConfig().getInt("Punishments.Spam.Level." + p.getName()) <= 3) {
									p.sendMessage(Vars.prefix + "You have been warned for spam.");
								} else {
									Main.instance.getConfig().set("Punishments.Spam.Level." + p.getName(),
											Main.instance.getConfig().getInt(("Punishments.Spam.Level." + p.getName())) + 1);
									Main.instance.getConfig().set("Punishments.Spam.Muted." + p.getName(), true);
									p.sendMessage(Vars.prefix
											+ "You have been muted for spam. Please request an unmute by a mod if you wish to be unmuted.");
								}
							}
							sender.sendMessage(Vars.prefix + "Successfully punished " + p.getName() + " for spam.");
						}
						if (args[2].startsWith("o")) {
							Bukkit.broadcastMessage(Vars.prefix + args[0] + " has been punished for " + args[3] + ".");
							if (Main.instance.getConfig().get("Punishments.Other.Level." + p.getName()) == null) {
								Main.instance.getConfig().get("Punishments.Other.Level." + p.getName(), 1);
								p.sendMessage(Vars.prefix + "You have been warned for " + args[3] + ".");
							} else {
								Main.instance.getConfig().set("Punishments.Other.Level." + p.getName(),
										Main.instance.getConfig().getInt("Punishments.Other.Level." + p.getName()) + 1);
								if (Main.instance.getConfig().getInt("Punishments.Other.Level." + p.getName()) >= 1
										&& Main.instance.getConfig().getInt("Punishments.Other.Level." + p.getName()) <= 3) {
									p.sendMessage(Vars.prefix + "You have been warned for " + args[3] + ".");
								} else {
									p.kickPlayer(Vars.prefix + "Kicked for " + args[3] + " by a moderator.");
								}
							}
							sender.sendMessage(
									Vars.prefix + "Successfully punished " + p.getName() + " for " + args[3] + ".");
						}
					}
					Main.instance.saveConfig();
				}
				if (args[0].equalsIgnoreCase(p.getName())) {
					// yes or true
					if (args[1].startsWith("y") || args[1].startsWith("t")) {
						// hax
						if (args[2].startsWith("h")) {
							if (Main.instance.getConfig().get("Punishments.Hax.Level." + p.getName()) == null) {
								Main.instance.getConfig().set("Punishments.Hax.Level." + p.getName(),
										Main.instance.getConfig().getInt(("Punishments.Hax.Level." + p.getName())) + 1);
								p.kickPlayer(Vars.prefix + "Kicked for hacking by a moderator.");
							} else {
								Main.instance.getConfig().set("Punishments.Hax.Level." + p.getName(),
										Main.instance.getConfig().getInt(("Punishments.Hax.Level." + p.getName())) + 1);
								if (Main.instance.getConfig().getInt("Punishments.Hax.Level." + p.getName()) <= 3) {
									p.kickPlayer(
											Vars.prefix + "Kicked for hacking by a moderator. Hacking punishments: "
													+ Main.instance.getConfig().getInt("Punishments.Hax.Level." + p.getName()) + "h");
								} else {
									p.kickPlayer("Banned for hacking by a moderator.");
								}
								if (Main.instance.getConfig().getInt("Punishments.Hax.Level." + p.getName()) == 4) {
									Date date = new Date(System.currentTimeMillis() + 60 * 60 * 1000);
									Bukkit.getBanList(Type.NAME).addBan(p.getName(),
											"Banned for hacking by a moderator.", date, "Moderator");
								}
								if (Main.instance.getConfig().getInt("Punishments.Hax.Level." + p.getName()) == 5) {
									Date date = new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000);
									Bukkit.getBanList(Type.NAME).addBan(p.getName(),
											"Banned for hacking by a moderator.", date, "Moderator");
								}
								if (Main.instance.getConfig().getInt("Punishments.Hax.Level." + p.getName()) == 6) {
									Date date = new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000);
									Bukkit.getBanList(Type.NAME).addBan(p.getName(),
											"Banned for hacking by a moderator.", date, "Moderator");
								}
								if (Main.instance.getConfig().getInt("Punishments.Hax.Level." + p.getName()) == 7) {
									Date date = new Date(System.currentTimeMillis() + 30 * 7 * 24 * 60 * 60 * 1000);
									Bukkit.getBanList(Type.NAME).addBan(p.getName(),
											"Banned for hacking by a moderator.", date, "Moderator");
								}
								if (Main.instance.getConfig().getInt("Punishments.Hax.Level." + p.getName()) >= 8) {
									Bukkit.getBanList(Type.NAME).addBan(p.getName(),
											"Banned for hacking by a moderator.", null, "Moderator");
								}
							}
							sender.sendMessage(Vars.prefix + "Successfully punished " + p.getName()
									+ " for hacking. Hacking punishments:"
									+ Main.instance.getConfig().getInt("Punishments.Hax.Level." + p.getName()));
						}
						if (args[2].startsWith("b")) {
							Bukkit.banIP(p.getAddress().getAddress().getHostAddress());
							p.kickPlayer(Vars.prefix + "Banned for ban evasion by a moderator.");
							sender.sendMessage(
									Vars.prefix + "Successfully punished " + p.getName() + " for ban evasion.");
						}
						if (args[2].startsWith("s")) {
							if (Main.instance.getConfig().get("Punishments.Spam.Level." + p.getName()) == null) {
								Main.instance.getConfig().set("Punishments.Spam.Level." + p.getName(),
										Main.instance.getConfig().getInt(("Punishments.Spam.Level." + p.getName())) + 1);
								p.sendMessage(Vars.prefix + "You have been warned for spam.");
							} else {
								Main.instance.getConfig().set("Punishments.Spam.Level." + p.getName(),
								Main.instance.getConfig().getInt("Punishments.Spam.Level." + p.getName()) + 1);
								if (Main.instance.getConfig().getInt("Punishments.Spam.Level." + p.getName()) >= 1
										&& Main.instance.getConfig().getInt("Punishments.Spam.Level." + p.getName()) <= 3) {
									p.sendMessage(Vars.prefix + "You have been warned for spam.");
								} else {
									Main.instance.getConfig().set("Punishments.Spam.Level." + p.getName(),
											Main.instance.getConfig().getInt(("Punishments.Spam.Level." + p.getName())) + 1);
									Main.instance.getConfig().set("Punishments.Spam.Muted." + p.getName(), true);
									p.sendMessage(Vars.prefix
											+ "You have been muted for spam. Please request an unmute by a mod if you wish to be unmuted.");
								}
							}
							sender.sendMessage(Vars.prefix + "Successfully punished " + p.getName() + " for spam.");
						}
						if (args[2].startsWith("o")) {
							if (Main.instance.getConfig().get("Punishments.Other.Level." + p.getName()) == null) {
								Main.instance.getConfig().get("Punishments.Other.Level." + p.getName(), 1);
								p.sendMessage(Vars.prefix + "You have been warned for " + args[3] + ".");
							} else {
								Main.instance.getConfig().set("Punishments.Other.Level." + p.getName(),
										Main.instance.getConfig().getInt("Punishments.Other.Level." + p.getName()) + 1);
								if (Main.instance.getConfig().getInt("Punishments.Other.Level." + p.getName()) >= 1
										&& Main.instance.getConfig().getInt("Punishments.Other.Level." + p.getName()) <= 3) {
									p.sendMessage(Vars.prefix + "You have been warned for " + args[3] + ".");
								} else {
									p.kickPlayer(Vars.prefix + "Kicked for " + args[3] + " by a moderator.");
								}
							}
							sender.sendMessage(
									Vars.prefix + "Successfully punished " + p.getName() + " for " + args[3] + ".");
						}
					}
					Main.instance.saveConfig();
				}

			}

		}
		return true;
	}
}
