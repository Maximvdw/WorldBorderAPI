package be.maximvdw.wbapi.events;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import be.maximvdw.wbapi.WorldBorder;

public class WorldBorderEvent extends Event {
	/**
	 * Event listener handler list.
	 */
	private static final HandlerList handlers = new HandlerList();
	/** Event cancelled */
	private boolean cancelled = false;

	/** World that is being updated */
	private WorldBorder worldBorder = null;

	public WorldBorderEvent(WorldBorder worldBorder) {
		this.worldBorder = worldBorder;
		Bukkit.getPluginManager().callEvent(this);
	}

	/**
	 * Check if cancelled
	 * 
	 * @return Cancelled
	 */
	public boolean isCancelled() {
		return cancelled;
	}

	/**
	 * Set event cancelled
	 * 
	 * @param cancelled
	 *            Cancelled
	 */
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	/**
	 * Get handlers
	 */
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	/**
	 * Get Handlers list
	 * 
	 * @return HandlerList
	 */
	public static HandlerList getHandlerList() {
		return handlers;
	}

	/**
	 * Get the world border instance
	 * 
	 * @return WorldBorder instance
	 */
	public WorldBorder getWorldBorder() {
		return worldBorder;
	}

	/**
	 * Set the world border instance
	 * 
	 * @param worldBorder
	 *            WorldBorder instance
	 */
	public void setWorldBorder(WorldBorder worldBorder) {
		this.worldBorder = worldBorder;
	}
}
