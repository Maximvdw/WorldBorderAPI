package be.maximvdw.wbapi.listeners;

import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class PlayerListener implements Listener {

	/**
	 * On player join
	 * 
	 * @param event
	 *            Player Join event
	 */
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {

	}

	/**
	 * On player teleport
	 * 
	 * @param event
	 *            Player Teleport event
	 */
	@EventHandler
	public void onTeleport(PlayerTeleportEvent event) {

	}

	public void loadWorldBorder(World world) {

	}
}
