package serveur;

import java.util.ArrayList;


public class Session {

	private static Session instance;
	
	private ArrayList<OnlineUser> users = new ArrayList<OnlineUser>();
	
	public static Session getInstance(){
		if(instance == null){
			instance = new Session();
		}
		return instance;
	}
	
	public void addUser(OnlineUser u){
		users.add(u);
	}

	public OnlineUser getUser(String username) {
		OnlineUser u = null;
		
		for(OnlineUser us : users){
			if(us.getUsername().equals(username)){
				u = us;
				break;
			}
		}
		
		return u;
	}
		
}
