package uwu.smsgamer.uwup.commands;

import java.util.Date;

import org.bukkit.BanList.Type;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import uwu.smsgamer.uwup.ConfigManager;
import uwu.smsgamer.uwup.Main;
import uwu.smsgamer.uwup.vars.Vars;

public class Commands implements CommandExecutor {

	public String cmd1 = "verbose";
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("pu") || cmd.getName().equalsIgnoreCase("punish")) {
			if (sender.hasPermission("punish.use")) {
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
								if (ConfigManager.instance.getPlayers().get("Punishments.Hax.Level." + p.getUniqueId()) == null) {
									ConfigManager.instance.getPlayers().set("Punishments.Hax.Level." + p.getUniqueId(),
											ConfigManager.instance.getPlayers()
													.getInt(("Punishments.Hax.Level." + p.getUniqueId())) + 1);
									p.kickPlayer(Vars.prefix + "Kicked for hacking by a moderator.");
								} else {
									ConfigManager.instance.getPlayers().set("Punishments.Hax.Level." + p.getUniqueId(),
											ConfigManager.instance.getPlayers()
													.getInt(("Punishments.Hax.Level." + p.getUniqueId())) + 1);
									if (ConfigManager.instance.getPlayers()
											.getInt("Punishments.Hax.Level." + p.getUniqueId()) <= 3) {
										p.kickPlayer(
												Vars.prefix + "Kicked for hacking by a moderator. Hacking punishments: "
														+ ConfigManager.instance.getPlayers().getInt(
																"Punishments.Hax.Level." + p.getUniqueId())
														+ "h");
									} else {
										p.kickPlayer("Banned for hacking by a moderator.");
									}
									if (ConfigManager.instance.getPlayers()
											.getInt("Punishments.Hax.Level." + p.getUniqueId()) == 4) {
										Date date = new Date(System.currentTimeMillis() + 60 * 60 * 1000);
										Bukkit.getBanList(Type.NAME).addBan(p.getName(),
												"Banned for hacking by a moderator.", date, "Moderator");
									}
									if (ConfigManager.instance.getPlayers()
											.getInt("Punishments.Hax.Level." + p.getUniqueId()) == 5) {
										Date date = new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000);
										Bukkit.getBanList(Type.NAME).addBan(p.getName(),
												"Banned for hacking by a moderator.", date, "Moderator");
									}
									if (ConfigManager.instance.getPlayers()
											.getInt("Punishments.Hax.Level." + p.getUniqueId()) == 6) {
										Date date = new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000);
										Bukkit.getBanList(Type.NAME).addBan(p.getName(),
												"Banned for hacking by a moderator.", date, "Moderator");
									}
									if (ConfigManager.instance.getPlayers()
											.getInt("Punishments.Hax.Level." + p.getUniqueId()) == 7) {
										Date date = new Date(System.currentTimeMillis() + 30 * 7 * 24 * 60 * 60 * 1000);
										Bukkit.getBanList(Type.NAME).addBan(p.getName(),
												"Banned for hacking by a moderator.", date, "Moderator");
									}
									if (ConfigManager.instance.getPlayers()
											.getInt("Punishments.Hax.Level." + p.getUniqueId()) >= 8) {
										Bukkit.getBanList(Type.NAME).addBan(p.getName(),
												"Banned for hacking by a moderator.", null, "Moderator");
									}
								}
								sender.sendMessage(Vars.prefix + "Successfully punished " + p.getName()
										+ " for hacking. Hacking punishments:"
										+ ConfigManager.instance.getPlayers().getInt("Punishments.Hax.Level." + p.getName()));
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
								if (ConfigManager.instance.getPlayers()
										.get("Punishments.Spam.Level." + p.getUniqueId()) == null) {
									ConfigManager.instance.getPlayers().set("Punishments.Spam.Level." + p.getUniqueId(),
											ConfigManager.instance.getPlayers()
													.getInt(("Punishments.Spam.Level." + p.getUniqueId())) + 1);
									p.sendMessage(Vars.prefix + "You have been warned for spam.");
								} else {
									ConfigManager.instance.getPlayers().set("Punishments.Spam.Level." + p.getUniqueId(),
											ConfigManager.instance.getPlayers()
													.getInt("Punishments.Spam.Level." + p.getUniqueId()) + 1);
									if (ConfigManager.instance.getPlayers()
											.getInt("Punishments.Spam.Level." + p.getUniqueId()) >= 1
											&& ConfigManager.instance.getPlayers()
													.getInt("Punishments.Spam.Level." + p.getUniqueId()) <= 3) {
										p.sendMessage(Vars.prefix + "You have been warned for spam.");
									} else {
										ConfigManager.instance.getPlayers().set("Punishments.Spam.Level." + p.getUniqueId(),
												ConfigManager.instance.getPlayers()
														.getInt(("Punishments.Spam.Level." + p.getUniqueId())) + 1);
										ConfigManager.instance.getPlayers().set("Punishments.Spam.Muted." + p.getUniqueId(),
												true);
										p.sendMessage(Vars.prefix
												+ "You have been muted for spam. Please request an unmute by a mod if you wish to be unmuted.");
									}
								}
								sender.sendMessage(Vars.prefix + "Successfully punished " + p.getName() + " for spam.");
							}
							if (args[2].startsWith("o")) {
								Bukkit.broadcastMessage(
										Vars.prefix + args[0] + " has been punished for " + args[3] + ".");
								if (ConfigManager.instance.getPlayers().get("Punishments.Other.Level." + p.getName()) == null) {
									ConfigManager.instance.getPlayers().get("Punishments.Other.Level." + p.getName(), 1);
									p.sendMessage(Vars.prefix + "You have been warned for " + args[3] + ".");
								} else {
									ConfigManager.instance.getPlayers().set("Punishments.Other.Level." + p.getName(),
											ConfigManager.instance.getPlayers().getInt("Punishments.Other.Level." + p.getName())
													+ 1);
									if (ConfigManager.instance.getPlayers().getInt("Punishments.Other.Level." + p.getName()) >= 1
											&& ConfigManager.instance.getPlayers()
													.getInt("Punishments.Other.Level." + p.getName()) <= 3) {
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
								if (ConfigManager.instance.getPlayers().get("Punishments.Hax.Level." + p.getName()) == null) {
									ConfigManager.instance.getPlayers().set("Punishments.Hax.Level." + p.getName(),
											ConfigManager.instance.getPlayers().getInt(("Punishments.Hax.Level." + p.getName()))
													+ 1);
									p.kickPlayer(Vars.prefix + "Kicked for hacking by a moderator.");
								} else {
									ConfigManager.instance.getPlayers().set("Punishments.Hax.Level." + p.getName(),
											ConfigManager.instance.getPlayers().getInt(("Punishments.Hax.Level." + p.getName()))
													+ 1);
									if (ConfigManager.instance.getPlayers().getInt("Punishments.Hax.Level." + p.getName()) <= 3) {
										p.kickPlayer(
												Vars.prefix + "Kicked for hacking by a moderator. Hacking punishments: "
														+ ConfigManager.instance.getPlayers()
																.getInt("Punishments.Hax.Level." + p.getName())
														+ "h");
									} else {
										p.kickPlayer("Banned for hacking by a moderator.");
									}
									if (ConfigManager.instance.getPlayers().getInt("Punishments.Hax.Level." + p.getName()) == 4) {
										Date date = new Date(System.currentTimeMillis() + 60 * 60 * 1000);
										Bukkit.getBanList(Type.NAME).addBan(p.getName(),
												"Banned for hacking by a moderator.", date, "Moderator");
									}
									if (ConfigManager.instance.getPlayers().getInt("Punishments.Hax.Level." + p.getName()) == 5) {
										Date date = new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000);
										Bukkit.getBanList(Type.NAME).addBan(p.getName(),
												"Banned for hacking by a moderator.", date, "Moderator");
									}
									if (ConfigManager.instance.getPlayers().getInt("Punishments.Hax.Level." + p.getName()) == 6) {
										Date date = new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000);
										Bukkit.getBanList(Type.NAME).addBan(p.getName(),
												"Banned for hacking by a moderator.", date, "Moderator");
									}
									if (ConfigManager.instance.getPlayers().getInt("Punishments.Hax.Level." + p.getName()) == 7) {
										Date date = new Date(System.currentTimeMillis() + 30 * 7 * 24 * 60 * 60 * 1000);
										Bukkit.getBanList(Type.NAME).addBan(p.getName(),
												"Banned for hacking by a moderator.", date, "Moderator");
									}
									if (ConfigManager.instance.getPlayers().getInt("Punishments.Hax.Level." + p.getName()) >= 8) {
										Bukkit.getBanList(Type.NAME).addBan(p.getName(),
												"Banned for hacking by a moderator.", null, "Moderator");
									}
								}
								sender.sendMessage(Vars.prefix + "Successfully punished " + p.getName()
										+ " for hacking. Hacking punishments:"
										+ ConfigManager.instance.getPlayers().getInt("Punishments.Hax.Level." + p.getName()));
							}
							if (args[2].startsWith("b")) {
								Bukkit.banIP(p.getAddress().getAddress().getHostAddress());
								p.kickPlayer(Vars.prefix + "Banned for ban evasion by a moderator.");
								sender.sendMessage(
										Vars.prefix + "Successfully punished " + p.getName() + " for ban evasion.");
							}
							if (args[2].startsWith("s")) {
								if (ConfigManager.instance.getPlayers().get("Punishments.Spam.Level." + p.getName()) == null) {
									ConfigManager.instance.getPlayers().set("Punishments.Spam.Level." + p.getName(),
											ConfigManager.instance.getPlayers().getInt(("Punishments.Spam.Level." + p.getName()))
													+ 1);
									p.sendMessage(Vars.prefix + "You have been warned for spam.");
								} else {
									ConfigManager.instance.getPlayers().set("Punishments.Spam.Level." + p.getName(),
											ConfigManager.instance.getPlayers().getInt("Punishments.Spam.Level." + p.getName())
													+ 1);
									if (ConfigManager.instance.getPlayers().getInt("Punishments.Spam.Level." + p.getName()) >= 1
											&& ConfigManager.instance.getPlayers()
													.getInt("Punishments.Spam.Level." + p.getName()) <= 3) {
										p.sendMessage(Vars.prefix + "You have been warned for spam.");
									} else {
										ConfigManager.instance.getPlayers().set("Punishments.Spam.Level." + p.getName(),
												ConfigManager.instance.getPlayers()
														.getInt(("Punishments.Spam.Level." + p.getName())) + 1);
										ConfigManager.instance.getPlayers().set("Punishments.Spam.Muted." + p.getName(), true);
										p.sendMessage(Vars.prefix
												+ "You have been muted for spam. Please request an unmute by a mod if you wish to be unmuted.");
									}
								}
								sender.sendMessage(Vars.prefix + "Successfully punished " + p.getName() + " for spam.");
							}
							if (args[2].startsWith("o")) {
								if (ConfigManager.instance.getPlayers().get("Punishments.Other.Level." + p.getName()) == null) {
									ConfigManager.instance.getPlayers().get("Punishments.Other.Level." + p.getName(), 1);
									p.sendMessage(Vars.prefix + "You have been warned for " + args[3] + ".");
								} else {
									ConfigManager.instance.getPlayers().set("Punishments.Other.Level." + p.getName(),
											ConfigManager.instance.getPlayers().getInt("Punishments.Other.Level." + p.getName())
													+ 1);
									if (ConfigManager.instance.getPlayers().getInt("Punishments.Other.Level." + p.getName()) >= 1
											&& ConfigManager.instance.getPlayers()
													.getInt("Punishments.Other.Level." + p.getName()) <= 3) {
										p.sendMessage(Vars.prefix + "You have been warned for " + args[3] + ".");
									} else {
										p.kickPlayer(Vars.prefix + "Kicked for " + args[3] + " by a moderator.");
									}
								}
								sender.sendMessage(
										Vars.prefix + "Successfully punished " + p.getName() + " for " + args[3] + ".");
							}
						}
						ConfigManager.instance.savePlayers();
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
					sender.sendMessage(Vars.prefix + ChatColor.DARK_RED + ChatColor.BOLD + "Ò" + ChatColor.DARK_RED +"wÓ");
				}

			}
		}
		return true;
	}
}
