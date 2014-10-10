package be.maximvdw.wbapi.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_7_R4.EntityPlayer;
import net.minecraft.server.v1_7_R4.EnumProtocol;
import net.minecraft.server.v1_7_R4.Packet;

public class PacketUtils {
	private static List<Class<? extends Packet>> newPackets = new ArrayList<Class<? extends Packet>>();

	public static void sendPacket(Player player, Packet packet) {
		// Check if the client is 1.7
		if (PlayerUtils.getProtocolVersion(player) <= 47) {
			// If it is a newly added packet do not send it
			if (newPackets.contains(packet)) {
				return;
			}
		}
		CraftPlayer cPlayer = (CraftPlayer) player;
		EntityPlayer nmsPlayer = cPlayer.getHandle();
		nmsPlayer.playerConnection.sendPacket(packet);
	}

	@SuppressWarnings("unchecked")
	public static void registerPacket(EnumProtocol protocol,
			Class<? extends Packet> packetClass, int packetID,
			boolean isClientbound) {
		try {
			if (isClientbound) {
				protocol.b().put(packetID, packetClass);
			} else {
				protocol.a().put(packetID, packetClass);
			}
			Field mapField = EnumProtocol.class.getDeclaredField("f");
			mapField.setAccessible(true);
			Map<Class<? extends Packet>, EnumProtocol> map = (Map<Class<? extends Packet>, EnumProtocol>) mapField
					.get(null);
			map.put(packetClass, protocol);
			newPackets.add(packetClass);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
