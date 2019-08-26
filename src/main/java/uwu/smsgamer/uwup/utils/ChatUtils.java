package uwu.smsgamer.uwup.utils;

/**
 * Some utilities that will be used a lot throughout my program.
 */
public class ChatUtils {
	/**
	 * Replace '&amp;' with '§' and '&amp;&amp;' with '&amp;' for a message.
	 *
	 * @param msg The message that will be "colorized".
	 * @return Will return a string that has been "colorized".
	 */
	public static String colorize(String msg) {
		String fmsg = msg;
		fmsg = fmsg.replaceAll("&&", "_=-fa");
		fmsg = fmsg.replaceAll("&", "§");
		fmsg = fmsg.replaceAll("_=-fa", "&");
		return fmsg;
	}

	/**
	 * Edits placeholders for a message.
	 *
	 * @param msg The message that will be edited.
	 * @param p String to replace %player%
	 * @param r String to replace %reason%
	 * @param vl Integer to replace %vl%
	 * @return Will return a string with certain placeholders replaced.
	 */
	public static String phReplace(String msg, String p, String r, int vl) {
		String fmsg = msg;
		fmsg = fmsg.replaceAll("%player%", p);
		fmsg = fmsg.replaceAll("%reason%", r);
		fmsg = fmsg.replaceAll("%vl%", vl + "");
		return fmsg;
	}

	/**
	 * Edits placeholders for a message.
	 *
	 * @param msg The message that will be edited.
	 * @param time String to replace %date%
	 * @param sender String to replace %sender%
	 * @param player String to replace %player%
	 * @param vl Integer to replace %vl%
	 * @return Will return a string with certain placeholders replaced.
	 */
	public static String logReplace(String msg, String time, String sender, String player, int vl) {
		String fmsg = msg;
		fmsg = fmsg.replaceAll("%date%", time);
		fmsg = fmsg.replaceAll("%sender%", sender);
		fmsg = fmsg.replaceAll("%player%", player);
		fmsg = fmsg.replaceAll("%vl%", vl + "");
		return fmsg;
	}
}
