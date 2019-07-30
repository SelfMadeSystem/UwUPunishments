package uwu.smsgamer.uwup.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import uwu.smsgamer.uwup.Main;
import uwu.smsgamer.uwup.vars.Vars;

public class Events implements Listener {
	
	private Main plugin = Main.getPlugin(Main.class);
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		if (plugin.getConfig().getBoolean("Punishments.Spam.Muted" + e.getPlayer().getName())) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(Vars.prefix + ChatColor.DARK_RED + ChatColor.BOLD + "You are muted!"
					+ ChatColor.DARK_RED
					+ " Please request an unmute by a mod if you wish to be unmuted.");
		} else {

		}
	}
}
