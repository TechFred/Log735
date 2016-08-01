package serveur;

public class OnlineUser {
	
	private int 	uid,
					port;
	private String 	ip,
					username;
	private long 	login,
					lifeBeat;
	
	
	public int getUid() {
		return uid;
	}
	
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	public String getIp() {
		return ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public long getLogin() {
		return login;
	}
	
	public void setLogin(long login) {
		this.login = login;
	}
	
	public long getLifeBeat() {
		return lifeBeat;
	}
	
	public void setLifeBeat(long lifeBeat) {
		this.lifeBeat = lifeBeat;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
}
