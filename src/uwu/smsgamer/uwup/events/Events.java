package uwu.smsgamer.uwup.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import uwu.smsgamer.uwup.ConfigManager;
import uwu.smsgamer.uwup.vars.Vars;

public class Events implements Listener {
	
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		if (ConfigManager.instance.getPlayers().getBoolean("Punishments.Spam.Muted." + e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(Vars.prefix + ChatColor.DARK_RED + ChatColor.BOLD + "You are muted!"
					+ ChatColor.DARK_RED
					+ " Please request an unmute by a mod if you wish to be unmuted.");
		} else {

		}
	}
}
