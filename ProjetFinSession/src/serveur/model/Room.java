package serveur.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Room {

	private int 					uid,
									operationID = 1;
	
	private Queue<Operation> 		fileOperations = new LinkedList<Operation>();
	
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
		fileOperations.add(new Operation(operationID++, Operation.OP_JOIN, u.getUid(), u.getUsername()));
	}
	
	public void removeUser(OnlineUser u){
		if(this.users.remove(u)){
			fileOperations.add(new Operation(operationID++, Operation.OP_LEAVE, u.getUid(), u.getUsername()));
		}
	}
	
}
