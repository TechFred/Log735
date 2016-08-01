package client.model;

import java.io.Serializable;

public class AnnounceUDP implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -804646085748053486L;
	private String ipAdress, nickname;
	private int port, roomID, userUID;

	public AnnounceUDP(String ipAdress, String nickname, int port, int roomID, int userUID) {
		super();
		this.ipAdress = ipAdress;
		this.nickname = nickname;
		this.port = port;
		this.roomID = roomID;
		this.userUID = userUID;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getIpAdress() {
		return ipAdress;
	}

	public String getNickname() {
		return nickname;
	}

	public int getPort() {
		return port;
	}

	public int getRoomID() {
		return roomID;
	}

	public int getUserUID() {
		return userUID;
	}

}
