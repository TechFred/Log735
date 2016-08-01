package client.model;

import java.io.Serializable;

public class MessageUDP implements Serializable{

	private static final long serialVersionUID = -65029402392047671L;
	int roomUID;
	String message;
	
	public MessageUDP(int roomUID, String message) {

		this.roomUID = roomUID;
		this.message = message;
	}

	public int getRoomUID() {
		return roomUID;
	}

	public String getMessage() {
		return message;
	}
	

}
