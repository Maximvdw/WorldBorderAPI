package be.maximvdw.wbapi;

import java.util.HashMap;

import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class WorldBorderAPI extends JavaPlugin {
	/** Registered world borders */
	private static HashMap<World, WorldBorder> worldBorders = new HashMap<World, WorldBorder>();

	@Override
	public void onEnable() {

		// Register the packet

	}

	@Override
	public void onDisable() {

	}

	/**
	 * Register a world border
	 * 
	 * @param world
	 *            World
	 * @param worldBorder
	 *            World Border
	 */
	@SuppressWarnings("deprecation")
	public static void registerWorldBorder(World world, WorldBorder worldBorder) {
		// Check if the world in worldborder matches the registration world
		if (!worldBorder.getWorld().equals(world)) {
			worldBorder.setWorld(world);
		}
		// Add or overwrite the world border
		worldBorders.put(world, worldBorder);
	}
}
