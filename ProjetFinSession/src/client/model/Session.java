package client.model;

import java.util.ArrayList;

public class Session {

	private static Session instance;
	private Room lobby;
	private ArrayList<Room> rooms = new ArrayList<>();
	private User user;
	
	
	public static Session getInstance(){
		if(instance == null){
			instance = new Session();
		}
		return instance;
	}
	
	public Session(){}
	
	public void setUser(User u){
		user = u;
	}
	public User getUser(){
		return user;
	}

	public Room getLobby() {
		return lobby;
	}
	
	public void setLobby(Room l){
		this.lobby = l;
	}
	
	public ArrayList<Room> getRooms(){
		return rooms;
	}
	
	public void quitterRoom(int uid) {
		for(int i = 0; i <= rooms.size(); i++ ){
			if(rooms.get(i).getUid() == uid){
				rooms.remove(i);
				i--;
			}
		}
	}

}
