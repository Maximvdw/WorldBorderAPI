package be.maximvdw.wbapi.packets;

import java.io.IOException;

import be.maximvdw.wbapi.WorldBorder;
import net.minecraft.server.v1_7_R4.Packet;
import net.minecraft.server.v1_7_R4.PacketDataSerializer;
import net.minecraft.server.v1_7_R4.PacketListener;

public class PacketPlayOutWorldBorder extends Packet {
	private Action action;
	private WorldBorder worldBorder = new WorldBorder();

	public PacketPlayOutWorldBorder() {
	}

	public PacketPlayOutWorldBorder(Action action) {
		this.setAction(action);
	}

	public PacketPlayOutWorldBorder(Action action, WorldBorder worldBorder) {
		this.setAction(action);
		this.worldBorder = worldBorder;
	}

	/**
	 * Deserialize
	 */
	@SuppressWarnings("deprecation")
	public void a(PacketDataSerializer packetdataserializer) throws IOException {
		this.action = Action.values()[packetdataserializer.a()];
		switch (this.action) {
		case SET_SIZE:
			worldBorder.setRadius(packetdataserializer.readDouble());
			break;
		case LERP_SIZE:
			worldBorder.setOldRadius(packetdataserializer.readDouble());
			worldBorder.setRadius(packetdataserializer.readDouble());
			worldBorder.setSpeed(packetdataserializer.readLong());
			break;
		case SET_CENTER:
			worldBorder.setX(packetdataserializer.readDouble());
			worldBorder.setZ(packetdataserializer.readDouble());
			break;
		case INITIALIZE:
			worldBorder.setX(packetdataserializer.readDouble());
			worldBorder.setZ(packetdataserializer.readDouble());
			worldBorder.setOldRadius(packetdataserializer.readDouble());
			worldBorder.setRadius(packetdataserializer.readDouble());
			worldBorder.setSpeed(packetdataserializer.readLong());
			worldBorder.setPortalBoundary(packetdataserializer.readInt());
			worldBorder.setWarningTime(packetdataserializer.readInt());
			worldBorder.setWarningBlocks(packetdataserializer.readInt());
			break;
		case SET_WARNING_TIME:
			worldBorder.setWarningTime(packetdataserializer.readInt());
			break;
		case SET_WARNING_BLOCKS:
			worldBorder.setWarningBlocks(packetdataserializer.readInt());
			break;
		default:
			break;
		}
	}

	/**
	 * Serialize packet
	 */
	public void b(PacketDataSerializer serializer) {
		serializer.b(this.action.ordinal());
		switch (action) {
		case SET_SIZE: {
			serializer.writeDouble(worldBorder.getRadius());
			break;
		}
		case LERP_SIZE: {
			serializer.writeDouble(worldBorder.getOldRadius());
			serializer.writeDouble(worldBorder.getRadius());
			serializer.b((int) worldBorder.getSpeed());
			break;
		}
		case SET_CENTER: {
			serializer.writeDouble(worldBorder.getX());
			serializer.writeDouble(worldBorder.getZ());
			break;
		}
		case SET_WARNING_BLOCKS: {
			serializer.b(worldBorder.getWarningBlocks());
			break;
		}
		case SET_WARNING_TIME: {
			serializer.b(worldBorder.getWarningTime());
			break;
		}
		case INITIALIZE: {
			serializer.writeDouble(worldBorder.getX());
			serializer.writeDouble(worldBorder.getZ());
			serializer.writeDouble(worldBorder.getOldRadius());
			serializer.writeDouble(worldBorder.getRadius());
			serializer.b((int) worldBorder.getSpeed());
			serializer.b(worldBorder.getPortalBoundary());
			serializer.b(worldBorder.getWarningBlocks());
			serializer.b(worldBorder.getWarningTime());
		}
		}
	}

	public void handle(PacketListener packetlistener) {
	}

	/**
	 * Get world border action
	 * 
	 * @return Action
	 */
	public Action getAction() {
		return action;
	}

	/**
	 * Set world border action
	 * 
	 * @param action
	 *            Action
	 */
	public void setAction(Action action) {
		this.action = action;
	}

	public void setWorldBorder(WorldBorder worldBorder) {
		this.worldBorder = worldBorder;
	}

	public WorldBorder getWorldBorder() {
		return worldBorder;
	}

	public static enum Action {
		SET_SIZE, LERP_SIZE, SET_CENTER, INITIALIZE, SET_WARNING_TIME, SET_WARNING_BLOCKS;

		private Action() {
		}
	}
}
