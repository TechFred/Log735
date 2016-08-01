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

}
