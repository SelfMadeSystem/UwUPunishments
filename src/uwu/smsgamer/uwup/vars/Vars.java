package uwu.smsgamer.uwup.vars;

import net.md_5.bungee.api.ChatColor;
import uwu.smsgamer.uwup.Main;
import uwu.smsgamer.uwup.utils.ChatUtils;

public class Vars {
	public static String prefix = (ChatColor.RED + "UwU >> ");

	public static String ps_msg = ChatUtils.colorize((String) Main.instance.getConfig().get("punished_msg"));
	public static String bc_msg = ChatUtils.colorize((String) Main.instance.getConfig().get("bc_msg"));
}
