package serveur.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import serveur.data.ConfigManager;
import serveur.data.UsersManager;
import serveur.model.OnlineUser;
import serveur.model.Room;
import serveur.model.Session;


public class TCPClientConnectionThread extends Thread{
	
	private Socket socket;
	private boolean terminate = false;
	
	public TCPClientConnectionThread(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		try{
			
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	
			String inputLine;
	
			while ((inputLine = in.readLine()) != null && !terminate) {
				
				System.out.println("Serveur received: "+inputLine);
				traiterCommande(inputLine, in, out);
				
			}
			
			out.close();
			in.close();
			socket.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}

	private void traiterCommande(String inputLine, BufferedReader in, PrintWriter out) {
		
		String[] args = inputLine.split("\\]\\[");
		switch(args[0].toUpperCase()){
			case "CLOSE_CONNECTION":
				terminate = true;
			break;
			case "CREATE_USER":
				createUser(args, out);	
			break;
			case "TRY_LOGIN":
				tryLogin(args, out);
			break;
			case "LOGOUT":
				logout(args);
			break;
			case "RETRIEVE_USERS_FROM_LOBBY":
				retrieveUsersInLobby(out);
			break;
			default:
				System.err.println("DEFAULT ARG: "+args[0]+" A PROGRAMMER...");
			break;
		}
	}
	
	
	private void retrieveUsersInLobby(PrintWriter out) {
		for (OnlineUser u : Session.getInstance().getLobby().getListeUsers()) {
			out.println("__USER__]["+u.getUid()+"]["+u.getUsername()+"]["+u.getIp()+"]["+u.getPort());
		}
		out.println("__END__");
	}

	private void logout(String[] args) {

		int uid = Integer.parseInt(args[1]);
		String username = args[2];
		
		OnlineUser user = null;
		for(OnlineUser u : Session.getInstance().getLobby().getListeUsers()){
			if(u.getUid() == uid && u.getUsername().equals(username) && socket.getInetAddress().getHostAddress().equals(u.getIp())){
				user = u;
			}
		}
		
		if(user != null){
			System.out.println("REMOVE USER ___ "+user.getUid()+" "+user.getUsername());
			Session.getInstance().getLobby().removeUser(user);
			for(Room r : Session.getInstance().getListeRooms()){
				r.removeUser(user);
			}
		}
		
	}

	private void tryLogin(String[] args, PrintWriter out) {
		String pass = UsersManager.getUserPassword(args[1]);
		if(pass == null){
			out.println("__INVALID__");
		}else{
			if(pass.equals(args[2])){
				
				OnlineUser user = Session.getInstance().getUser(args[1]);
				if(user == null){
					
					int id = ConfigManager.getNextUserId();
					
					user = new OnlineUser();
					user.setUid(id);
					user.setUsername(args[1]);
					user.setIp(socket.getInetAddress().getHostAddress());
					user.setLogin(System.currentTimeMillis());
					user.setLifeBeat(System.currentTimeMillis());
					user.setPort(Integer.parseInt(args[3]));
					Session.getInstance().getLobby().addUser(user);
					
					out.println("__WORKED__]["+args[1]+"]["+id+"]["+ user.getIp());
					
					
				}else{
					
					out.println("__USER_ALREADY_ONLINE__");
					
				}
				
			}else{
				out.println("__INVALID__");
			}
		}
		
		out.println("__END__");
	}

	public void createUser(String[] args, PrintWriter out){
		String pw = UsersManager.getUserPassword(args[1]);
		if(pw == null){
			UsersManager.createNewUser(args[1], args[2]);
			out.println("__CREATED__");
		}else{
			out.println("__EXISTS__");
		}
		
		out.println("__END__");
	}
}
