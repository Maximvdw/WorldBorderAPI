package be.maximvdw.wbapi.utils;

import org.bukkit.entity.Player;

public class PlayerUtils {
	public static int getProtocolVersion(Player player) {
		int version = 0;
		try {
			Object handle = ReflectionUtil.getHandle(player);
			Object connection = ReflectionUtil.getField(handle.getClass(),
					"playerConnection").get(handle);
			Object networkManager = ReflectionUtil.getValue("networkManager",
					connection);
			version = (Integer) ReflectionUtil.getMethod("getVersion",
					networkManager.getClass()).invoke(networkManager);

			return version;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return version;
	}
}
