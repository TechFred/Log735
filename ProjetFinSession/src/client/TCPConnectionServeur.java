package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TCPConnectionServeur {
	private static TCPConnectionServeur instance = null;
	private String serveurIP 	= "";
	private int serveurPort 	= 0;
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	private boolean connected = false;
		
	public static TCPConnectionServeur getInstance(){
		if(instance == null){
			instance = new TCPConnectionServeur();
		}
		return instance;
	}
	
	public void initServerCoordinates(String ip, int port){
		serveurIP = ip;
		serveurPort = port;
	}
		
	public void connectToServeur(){

		try{
			
			socket = new Socket(serveurIP, serveurPort);
			System.out.println("Connected to server: "+socket.isConnected());
			
			out = new PrintWriter( socket.getOutputStream(), true );
			in = new BufferedReader(new InputStreamReader( socket.getInputStream() ) );
			
			connected = true;
			
		} catch (UnknownHostException e) {
			System.out.println("Unknown host: "+serveurIP+":"+serveurPort);
		} catch  (IOException e) {
			System.out.println("No I/O "+serveurIP+":"+serveurPort);
		}
	}
	
	public void disconnectFromServeur(){
		connected = false;
		
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		out.close();
		
	}
	
	public boolean isConnectedToServeur(){
		return connected;
	}

	public boolean createUser(String user, String encryptedPassword) {
		
		boolean worked = false;
		out.println("CREATE_USER]["+user+"]["+encryptedPassword);
		String inputLine;
		
		try {
			while ((inputLine = in.readLine()) != null) {
				
				System.out.println("Client received: '"+inputLine+"'");
				
				if(inputLine.trim().equals("__END__")){
					break;
				}else if(inputLine.trim().equals("__CREATED__")){
					worked = true;
				}else if(inputLine.trim().equals("__EXISTS__")){
					JOptionPane.showMessageDialog(new JFrame(), "L'utilisateur "+user+" existe déjà dans le système!\nVeuillez essayer un nom différent!");
					worked = false;
				}
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return worked;
	}

	public boolean tryToLogin(String user, String encryptedPassword) {
		boolean worked = false;
		
		out.println("TRY_LOGIN]["+user+"]["+encryptedPassword);
		
		String inputLine;
		
		try {
			while ((inputLine = in.readLine()) != null) {
				
				String[] args = inputLine.split("\\]\\[");
				System.out.println("Client received: '"+inputLine+"'");
				
				if(args[0].trim().equals("__END__")){
					break;
				}else if(args[0].trim().equals("__INVALID__")){
					JOptionPane.showMessageDialog(new JFrame(), "L'utilisateur et le mot de passe entré sont invalide!");
				}else if(args[0].trim().equals("__USER_ALREADY_ONLINE__")){
					JOptionPane.showMessageDialog(new JFrame(), "L'utilisateur est déjà connecté!\nVeuillez réessayer plus tard!");
				}else if(args[0].trim().equals("__WORKED__")){
					User u = new User();
					u.setUid(Integer.parseInt(args[2]));
					u.setUsername(args[1]);
					
					Session.getInstance().setUser(u);
					
					worked = true;
				}
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return worked;
	}
	
	
}
