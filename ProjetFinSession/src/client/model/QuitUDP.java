package client.model;

import java.io.Serializable;

public class QuitUDP implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1321733103871217532L;
	int userUID;
	int roomUID;
	boolean timedOut;

	public QuitUDP(int userUID, int roomUID, boolean timedOut) {
		super();
		this.userUID = userUID;
		this.roomUID = roomUID;
		this.timedOut = timedOut;
	}

	public int getUserUID() {
		return userUID;
	}

	public int getRoomUID() {
		return roomUID;
	}

	public boolean isTimedOut() {
		return timedOut;
	}

}
