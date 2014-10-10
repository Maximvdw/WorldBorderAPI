package be.maximvdw.wbapi.events;

import org.bukkit.Bukkit;
import be.maximvdw.wbapi.WorldBorder;

public class WorldBorderUpdateCenterEvent extends WorldBorderEvent {
	public WorldBorderUpdateCenterEvent(WorldBorder worldBorder) {
		super(worldBorder);
		Bukkit.getPluginManager().callEvent(this);
	}
}
