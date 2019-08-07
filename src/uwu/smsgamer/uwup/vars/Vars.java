package uwu.smsgamer.uwup.vars;

import uwu.smsgamer.uwup.Main;
import uwu.smsgamer.uwup.utils.ChatUtils;

public class Vars {

	public static String ps_msg = ChatUtils.colorize((String) Main.instance.getConfig().get("punished_msg"));
	public static String bc_msg = ChatUtils.colorize((String) Main.instance.getConfig().get("bc_msg"));
	public static String no_type = ChatUtils.colorize((String) Main.instance.getConfig().get("no_type"));
	public static String no_player = ChatUtils.colorize((String) Main.instance.getConfig().get("no_player"));
	public static String forgive = ChatUtils.colorize((String) Main.instance.getConfig().get("forgive_msg"));
	public static String no_perm = ChatUtils.colorize((String) Main.instance.getConfig().get("no_perm"));
	public static String check_vl = ChatUtils.colorize((String) Main.instance.getConfig().get("check_vl"));
}
