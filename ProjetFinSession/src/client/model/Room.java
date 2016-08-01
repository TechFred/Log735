package client.model;

import java.util.ArrayList;

import client.ui.FrameConvo;
import client.utils.UserRoomMessage;

public class Room {

	private int uid, sendID = 1;

	private String uname, password;

	private boolean privateRoom;

	private ArrayList<User> users = new ArrayList<User>();

	private FrameConvo frameParent;

	public Room(int uid, String name, String pass, boolean privateR, FrameConvo conv) {
		this.uid = uid;
		this.uname = name;
		this.password = pass;
		this.privateRoom = privateR;
		this.frameParent = conv;

	}

	public Room() {
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isPrivateRoom() {
		return privateRoom;
	}

	public void setPrivateRoom(boolean privateRoom) {
		this.privateRoom = privateRoom;
	}

	public int getSendID() {
		return sendID;
	}

	public void setSendID(int sendID) {
		this.sendID = sendID;
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	public void refreshListeUsers() {
		frameParent.refreshListeUsers(users.toArray(new User[users.size()]));
	}

	public void addUserMessage(String message) {
		frameParent.refreshUserMessage(message);

	}

	public void joinRoomAnnounce(User u) {

		AnnounceUDP announce = new AnnounceUDP(u.getIpAddress(), u.getUsername(), u.getPort(), this.uid, u.getUid());
		new UserRoomMessage().sendAnnounce(announce, this);

	}

	public void addUser(User u) {
		boolean exist = false;
		for (User user : this.users) {
			if (user.getUid() == u.getUid()) {
				exist = true;
			}

		}
		if (!exist) {
			this.users.add(u);
			refreshListeUsers();
		}
	}

	public User remove(int uid) {
		User userFound = null;
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getUid() == uid) {
				userFound = users.get(i);
				this.users.remove(i);
				i--;
				System.out.println("User removed: " + userFound.getUsername());
				break;
			}

		}
		refreshListeUsers();
		return userFound;
	}

}
