package serveur.model;

import java.util.ArrayList;

public class Room {

	private int 					uid;
	
	private String 					uname,
									password;
	
	private boolean 				privateRoom;
	
	private ArrayList<OnlineUser> 	users = new ArrayList<OnlineUser>();
	
	
	public Room(int uid, String name, String pass, boolean privateR){
		this.uid = uid;
		this.uname = name;
		this.password = pass;
		this.privateRoom = privateR;
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

	public ArrayList<OnlineUser> getListeUsers() {
		return users;
	}
	
	public void addUser(OnlineUser u){
		this.users.add(u);
	}
	
	public boolean removeUser(OnlineUser u){
		return this.users.remove(u);
	}
	
}
