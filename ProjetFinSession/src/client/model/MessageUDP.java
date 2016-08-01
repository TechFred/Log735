package client.model;

import java.io.Serializable;

public class MessageUDP implements Serializable{

	private static final long serialVersionUID = -65029402392047671L;
	int roomUID;
	String message;
	int userUID;
	
	public MessageUDP(int roomUID, String message, int userUID) {

		this.roomUID = roomUID;
		this.message = message;
		this.userUID = userUID;
	}

	public int getRoomUID() {
		return roomUID;
	}

	public String getMessage() {
		return message;
	}

	public int getUserUID() {
		return userUID;
	}
	

}
