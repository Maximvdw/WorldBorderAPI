package be.maximvdw.wbapi.events;

import org.bukkit.Bukkit;

import be.maximvdw.wbapi.WorldBorder;

public class WorldBorderInitiailizeEvent extends WorldBorderEvent {
	public WorldBorderInitiailizeEvent(WorldBorder worldBorder) {
		super(worldBorder);
		Bukkit.getPluginManager().callEvent(this);
	}
}
