package serveur.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import serveur.OnlineUser;
import serveur.Session;
import serveur.data.ConfigManager;
import serveur.data.UsersManager;


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
			default:
				System.err.println("DEFAULT ARG: "+args[0]+" A PROGRAMMER...");
			break;
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
				
					Session.getInstance().addUser(user);
					
					out.println("__WORKED__]["+args[1]+"]["+id);
					
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
