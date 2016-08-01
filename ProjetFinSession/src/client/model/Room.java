package client.model;

import java.util.ArrayList;
import client.ui.FrameConvo;

public class Room {

	private int 					uid,
									sendID = 1;
	
	private String 					uname,
									password;
	
	private boolean 				privateRoom;
	
	private ArrayList<User> 		users = new ArrayList<User>();
	
	private FrameConvo				frameParent;
	
	public Room(int uid, String name, String pass, boolean privateR, FrameConvo conv){
		this.uid = uid;
		this.uname = name;
		this.password = pass;
		this.privateRoom = privateR;
		this.frameParent = conv;
	}
	
	public Room() {}

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
	public void addUserMessage(String message){
		frameParent.refreshUserMessage(message);
	
	}
	
}
