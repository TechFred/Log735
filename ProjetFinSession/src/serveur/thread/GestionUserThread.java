package serveur.thread;

import java.util.ArrayList;

import client.model.QuitUDP;
import client.utils.UtilsSendUDP;
import serveur.data.OnlineUsersManager;
import serveur.data.RoomManager;
import serveur.model.OnlineUser;
import serveur.model.Room;
import serveur.model.Session;

public class GestionUserThread extends Thread {

	private int sleepTime = 10*1000;
	
	public void run(){
		
		loadData();
		
		while(true){
			try {
				Thread.sleep(sleepTime);
				crawlerOnlineUsers();
				saveData();
			} catch (InterruptedException e) {
			}
		}
	}
	
	// Méthode crawl user, timestamp
	// Flush users, dans toutes les rooms. 
	// Envoi des UDP aux clients. 
	// Sleep
	// Sauvegarde après le flush. 
	// 
	
	private void loadData() {
		Session.getInstance().getLobby().setListeUsers(OnlineUsersManager.load());
		Session.getInstance().setListeRooms(RoomManager.load());
	}

	private void crawlerOnlineUsers(){
		ArrayList<OnlineUser> users = Session.getInstance().getLobby().getListeUsers();
		for (int i = 0; i < users.size(); i++ ){
			if (users.get(i).getLifeBeat() + (60*1000) < System.currentTimeMillis()){
				OnlineUser u = users.get(i);
				System.out.println("L'utilisateur "+u.getUsername()+" a timeout!");
				removeUser(u);
				
				if(Session.getInstance().getLobby().removeUser(u)){
					// Send UDP pour les users du rooms lobby
					for (OnlineUser userToNotify : Session.getInstance().getLobby().getListeUsers()){
						sendUDPTimeOut(userToNotify,Session.getInstance().getLobby(), u); // Send UDP pour les users du rooms. 
					}
					sendUDPTimeOut( u, Session.getInstance().getLobby(), u );
				}
				
			}
		}
	}
	private void removeUser(OnlineUser u){
		for (Room r : Session.getInstance().getListeRooms()){
			if(r.removeUser(u)){
				for (OnlineUser userToNotify : r.getListeUsers()){
					sendUDPTimeOut(userToNotify,r, u); // Send UDP pour les users du rooms. 
				}
				sendUDPTimeOut( u, r, u );
			}
		}
	}
	private void sendUDPTimeOut(OnlineUser u, Room r, OnlineUser leavingUser){
		QuitUDP q = new QuitUDP(leavingUser.getUid(),r.getUid(),true);
		UtilsSendUDP.SendUDP(q, u.getIp(),u.getPort());
	}
	
	private void saveData(){
		System.out.println("SAVE");
		OnlineUsersManager.save();
		RoomManager.save();
	}
}
