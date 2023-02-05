/**
 * The class that represents the rooms in which the courses will be taught.
 * @author zsenel
 *
 */
public class Room {
	
	private final int roomId;
	private final String roomName;

	public Room(int roomId, String roomName) {
		this.roomId = roomId;
		this.roomName = roomName;
	}

	public int getRoomId() {
		return this.roomId;
	}

	public String getRoomNumber() {
		return this.roomName;
	}

}