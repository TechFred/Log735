package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


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
		UsersManager m = new UsersManager();
		
		String[] args = inputLine.split("\\]\\[");
		switch(args[0].toUpperCase()){
			case "CLOSE_CONNECTION":
				terminate = true;
			break;
			case "CREATE_USER":
				
				String pw = m.getUserPassword(args[1]);
				if(pw == null){
					System.out.println("CREATE USER: "+inputLine+" pw:"+pw);
					m.createNewUser(args[1], args[2]);
					out.println("__CREATED__");
				}else{
					out.println("__EXISTS__");
				}
				
				out.println("__END__");
				
			break;
			case "TRY_LOGIN":
				
				String pass = m.getUserPassword(args[1]);
				if(pass == null){
					out.println("__INVALID__][");
				}else{
					if(pass.equals(args[2])){
						out.println("__WORKED__][");
					}else{
						out.println("__INVALID__][");
					}
				}
				
				out.println("__END__");
				
			break;
			default:
				System.err.println("DEFAULT ARG: "+args[0]+" A PROGRAMMER...");
			break;
		}
	}
	
	
}
