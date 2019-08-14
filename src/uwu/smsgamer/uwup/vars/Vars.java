package uwu.smsgamer.uwup.vars;

import uwu.smsgamer.uwup.UwUP;
import uwu.smsgamer.uwup.utils.ChatUtils;

public class Vars {

	public static String ps_msg = ChatUtils.colorize((String) UwUP.instance.getConfig().get("punished_msg"));
	public static String bc_msg = ChatUtils.colorize((String) UwUP.instance.getConfig().get("bc_msg"));
	public static String no_type = ChatUtils.colorize((String) UwUP.instance.getConfig().get("no_type"));
	public static String no_player = ChatUtils.colorize((String) UwUP.instance.getConfig().get("no_player"));
	public static String forgive = ChatUtils.colorize((String) UwUP.instance.getConfig().get("forgive_msg"));
	public static String no_perm = ChatUtils.colorize((String) UwUP.instance.getConfig().get("no_perm"));
	public static String check_vl = ChatUtils.colorize((String) UwUP.instance.getConfig().get("check_vl"));
	public static String set_vl = ChatUtils.colorize((String) UwUP.instance.getConfig().get("set_msg"));
}
