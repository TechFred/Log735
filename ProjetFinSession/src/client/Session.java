package client;

public class Session {

	private static Session instance;
	private User user;
	
	
	public static Session getInstance(){
		if(instance == null){
			instance = new Session();
		}
		return instance;
	}
	
	public Session(){
		
	}
	
	public void setUser(User u){
		user = u;
	}
	public User getUser(){
		return user;
	}
	
}
