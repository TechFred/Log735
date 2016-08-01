package client.model;

public class User {
	
	private int uid,
				receiveId = 1;
		
	private String username;
	private int port;
	private String ipAddress;
	
	public String toString(){
		String r = username;
		if(Session.getInstance().getUser().getUid() == uid && Session.getInstance().getUser().getUsername().equals(username) ){
			r = "<HTML><FONT COLOR=GREEN>"+username+"</FONT></HTML>";
		}
		return r;
	}
	public User(){
		
	}
	public User(int id, String nom){
		this.uid = id;
		this.username = nom;
	}
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getReceiveId() {
		return receiveId;
	}
	public void setReceiveId(int receiveId) {
		this.receiveId = receiveId;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
}
