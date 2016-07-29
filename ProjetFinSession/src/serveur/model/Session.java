package serveur.model;

import java.util.ArrayList;


public class Session {

	private static Session instance;
	
	private ArrayList<Room> listeRooms = new ArrayList<Room>();
	private Room lobby = new Room(0, "Lobby", "", false);
	
	public static Session getInstance(){
		if(instance == null){
			instance = new Session();
		}
		return instance;
	}

	public OnlineUser getUser(String username) {
		OnlineUser u = null;
		
		for(OnlineUser us : lobby.getListeUsers()){
			if(us.getUsername().equals(username)){
				u = us;
				break;
			}
		}
		
		return u;
	}

	public Room getLobby() {
		return lobby;
	}

	public void setLobby(Room lobby) {
		this.lobby = lobby;
	}

	public ArrayList<Room> getListeRooms() {
		return listeRooms;
	}

	public void setListeRooms(ArrayList<Room> listeRooms) {
		this.listeRooms = listeRooms;
	}
		
}
