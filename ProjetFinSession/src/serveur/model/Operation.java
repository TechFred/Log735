package serveur.model;

public class Operation {

	public static final String OP_LEAVE = "LEAVE";
	public static final String OP_JOIN = "JOIN";
	
	private int uid;
	private String operation;
	private int userid;
	private String username;
	
	public Operation(int uid, String op, int idUser, String uname){
		this.uid = uid;
		this.operation = op;
		this.userid = idUser;
		this.username = uname;
	}
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}
