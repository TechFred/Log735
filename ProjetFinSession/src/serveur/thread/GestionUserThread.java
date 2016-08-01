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
				removeUser(u);
				Session.getInstance().getLobby().removeUser(u);
				// Send UDP
			}
		}
	}
	private void removeUser(OnlineUser u){
		for (Room r : Session.getInstance().getListeRooms()){
			r.removeUser(u);
			for (OnlineUser userToNotify : r.getListeUsers()){
				
				UtilsSendUDP.SendUDP(q, userToNotify.getIp(),userToNotify.getPort());
				

				}
		}
	}
	private void sendUDPTimeOut(OnlineUser u, Room r){
		QuitUDP q = new QuitUDP(u.getUid(),r.getUid(),true);

	}
	private void saveData(){
		
	}
}
