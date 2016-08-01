package serveur;

import serveur.model.OnlineUser;
import serveur.model.Session;

public class LifeBeatManager {

	public static void receiveLifeBeat(int userUID){
		OnlineUser user = Session.getInstance().getUser(userUID);
		user.setLifeBeat(System.currentTimeMillis());
		System.out.println("LB (" + user.getUsername() +")");
	}
}
