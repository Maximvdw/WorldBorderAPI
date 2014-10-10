package be.maximvdw.wbapi;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import be.maximvdw.wbapi.events.WorldBorderInitiailizeEvent;
import be.maximvdw.wbapi.events.WorldBorderUpdateCenterEvent;
import be.maximvdw.wbapi.packets.PacketPlayOutWorldBorder;
import be.maximvdw.wbapi.packets.PacketPlayOutWorldBorder.Action;
import be.maximvdw.wbapi.utils.PacketUtils;

public class WorldBorder {
	private World world = null;
	private double radius = 0;
	private double oldRadius = 0;
	private long speed = 0;
	private double x = 0;
	private double z = 0;
	private int warningTime = 0;
	private int warningBlocks = 0;
	private int portalBoundary = 0;

	/** Auto update on change */
	private boolean autoUpdate = false;

	public WorldBorder() {

	}

	public WorldBorder(World world) {
		setWorld(world);
	}

	public int getPortalBoundary() {
		return portalBoundary;
	}

	public void setPortalBoundary(int portalBoundary) {
		this.portalBoundary = portalBoundary;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public double getOldRadius() {
		return oldRadius;
	}

	public void setOldRadius(double oldradius) {
		this.oldRadius = oldradius;
	}

	public long getSpeed() {
		return speed;
	}

	public void setSpeed(long speed) {
		this.speed = speed;
	}

	public int getWarningBlocks() {
		return warningBlocks;
	}

	public void setWarningBlocks(int warningBlocks) {
		this.warningBlocks = warningBlocks;
	}

	public int getWarningTime() {
		return warningTime;
	}

	public void setWarningTime(int warningTime) {
		this.warningTime = warningTime;
	}

	public double getZ() {
		return z;
	}

	/**
	 * Set the Z coord of the center location
	 * 
	 * @deprecated Do not change the location using this function. Use
	 *             setLocation(Location location) instead.
	 * 
	 * @param z
	 *            Z Coord
	 */
	public void setZ(double z) {
		this.z = z;
	}

	public double getX() {
		return x;
	}

	/**
	 * Set the X coord of the center location
	 * 
	 * @deprecated Do not change the location using this function. Use
	 *             setLocation(Location location) instead.
	 * 
	 * @param x
	 *            X Coord
	 */
	@Deprecated
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Auto update the changes to all players
	 * 
	 * @param autoUpdate
	 *            Auto update changes
	 * @return WorldBorder instance
	 */
	public WorldBorder setAutoUpdate(boolean autoUpdate) {
		this.autoUpdate = autoUpdate;
		return this;
	}

	/**
	 * Check if updates are performed on changes
	 * 
	 * @return Auto update state
	 */
	public boolean isAutoUpdate() {
		return autoUpdate;
	}

	/**
	 * Get the world of the world border
	 * 
	 * @return World
	 */
	public World getWorld() {
		return world;
	}

	/**
	 * Set the world of the world border
	 * 
	 * @deprecated Do not change the world using this function use
	 *             setLocation(Location location) instead.
	 * 
	 * @param world
	 *            World
	 */
	@Deprecated
	public void setWorld(World world) {
		this.world = world;
	}

	/**
	 * Set the center location of the world border
	 * 
	 * @param location
	 *            Location
	 */
	public void setCenter(Location location) {
		setX(location.getBlockX());
		setZ(location.getBlockZ());
		setWorld(location.getWorld());
		if (isAutoUpdate())
			updateCenter();
	}

	/**
	 * Get the center location
	 * 
	 * @return Location
	 */
	public Location getCenter() {
		return new Location(getWorld(), getX(), 0, getZ());
	}

	/**
	 * Update the center location of the world border for all players
	 * 
	 * @return WorldBorder instance
	 */
	public WorldBorder updateCenter() {
		// Call event
		WorldBorderUpdateCenterEvent event = new WorldBorderUpdateCenterEvent(
				this);
		if (event.isCancelled())
			return this;
		for (Player player : Bukkit.getOnlinePlayers()) {
			updateCenter(player);
		}
		return this;
	}

	/**
	 * Initialize the world border for all players
	 * 
	 * @return WorldBorder instance
	 */
	public WorldBorder initialize() {
		// Call event
		WorldBorderInitiailizeEvent event = new WorldBorderInitiailizeEvent(
				this);
		if (event.isCancelled())
			return this;
		for (Player player : Bukkit.getOnlinePlayers()) {
			initialize(player);
		}
		return this;
	}

	/**
	 * Update the size of the world border for all players
	 * 
	 * @return WorldBorder instance
	 */
	public WorldBorder updateSize() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			updateSize(player);
		}
		return this;
	}

	/**
	 * Update the size of the world border for all players
	 * 
	 * @param speed
	 *            Changing speed
	 * @return WorldBorder instance
	 */
	public WorldBorder updateSize(int speed) {
		for (Player player : Bukkit.getOnlinePlayers()) {
			updateSize(player, speed);
		}
		return this;
	}

	/**
	 * Update the center location of the world border for a specific player
	 * 
	 * @param player
	 *            Player
	 * @return WorldBorder instance
	 */
	public WorldBorder updateCenter(Player player) {
		if (world != null) {
			if (!player.getLocation().getWorld().equals(world))
				return this;
		}
		PacketPlayOutWorldBorder packet = new PacketPlayOutWorldBorder(
				Action.SET_CENTER, this);
		PacketUtils.sendPacket(player, packet);
		return this;
	}

	/**
	 * Update the warning time of the world border for a specific player
	 * 
	 * @param player
	 *            Player
	 * @return WorldBorder instance
	 */
	public WorldBorder updateWarningTime(Player player) {
		if (world != null) {
			if (!player.getLocation().getWorld().equals(world))
				return this;
		}
		PacketPlayOutWorldBorder packet = new PacketPlayOutWorldBorder(
				Action.SET_WARNING_TIME, this);
		PacketUtils.sendPacket(player, packet);
		return this;
	}

	/**
	 * Update the warning blocks of the world border for a specific player
	 * 
	 * @param player
	 *            Player
	 * @return WorldBorder instance
	 */
	public WorldBorder updateWarningBlocks(Player player) {
		if (world != null) {
			if (!player.getLocation().getWorld().equals(world))
				return this;
		}
		PacketPlayOutWorldBorder packet = new PacketPlayOutWorldBorder(
				Action.SET_WARNING_BLOCKS, this);
		PacketUtils.sendPacket(player, packet);
		return this;
	}

	/**
	 * Update the size of the world border for a specfic player
	 * 
	 * @param player
	 *            Player
	 * @return WorldBorder instance
	 */
	public WorldBorder updateSize(Player player) {
		if (world != null) {
			if (!player.getLocation().getWorld().equals(world))
				return this;
		}
		PacketPlayOutWorldBorder packet = new PacketPlayOutWorldBorder(
				Action.SET_SIZE, this);
		PacketUtils.sendPacket(player, packet);
		return this;
	}

	/**
	 * Update the size of the world border for a specfic player
	 * 
	 * @param player
	 *            Player
	 * @param speed
	 *            Changing speed
	 * @return WorldBorder instance
	 */
	public WorldBorder updateSize(Player player, int speed) {
		if (world != null) {
			if (!player.getLocation().getWorld().equals(world))
				return this;
		}
		setSpeed(speed);
		PacketPlayOutWorldBorder packet = new PacketPlayOutWorldBorder(
				Action.LERP_SIZE, this);
		PacketUtils.sendPacket(player, packet);
		return this;
	}

	/**
	 * Initialize the world border for a specific player
	 * 
	 * @param player
	 *            Player
	 * @return WorldBorder instance
	 */
	public WorldBorder initialize(Player player) {
		if (world != null) {
			if (!player.getLocation().getWorld().equals(world))
				return this;
		}
		PacketPlayOutWorldBorder packet = new PacketPlayOutWorldBorder(
				Action.INITIALIZE, this);
		PacketUtils.sendPacket(player, packet);
		return this;
	}
}
