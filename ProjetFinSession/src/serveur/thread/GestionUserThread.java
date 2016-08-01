package serveur.thread;

import client.model.QuitUDP;
import client.model.User;
import client.utils.UtilsSendUDP;
import serveur.model.OnlineUser;
import serveur.model.Room;
import serveur.model.Session;

public class GestionUserThread {

	// Méthode crawl user, timestamp
	// Flush users, dans toutes les rooms. 
	// Envoi des UDP aux clients. 
	// Sleep
	// Sauvegarde après le flush. 
	// 
	
	private void crawlerOnlineUsers(){
		for (OnlineUser u : Session.getInstance().getLobby().getListeUsers()){
			if (u.getLifeBeat() + (60*1000) < System.currentTimeMillis()){
				removeUser(u); // pas testé
				Session.getInstance().getLobby().removeUser(u); // Pas testé
				
				// Send UDP pour les users du rooms lobby
			}
		}
	}
	private void removeUser(OnlineUser u){
		for (Room r : Session.getInstance().getListeRooms()){
			r.removeUser(u); // Pas testé
			for (OnlineUser userToNotify : r.getListeUsers()){
				 //sendUDPTimeOut(userToNotify,r); // Send UDP pour les users du rooms. 
				
				
				}
		}
	}
	private void sendUDPTimeOut(OnlineUser u, Room r){
		QuitUDP q = new QuitUDP(u.getUid(),r.getUid(),true);
		UtilsSendUDP.SendUDP(q, u.getIp(),u.getPort());

	}
	private void saveData(){
		
	}
}
