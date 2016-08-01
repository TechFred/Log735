package serveur;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import serveur.data.ConfigManager;
import serveur.data.UsersManager;
import serveur.thread.TCPClientConnectionThread;


public class MainServeur {
	
	private static int SERVER_PORT = 9001;
	private static int SERVER_PORT_UDP = 9005;
	
	public static void main(String[] args) {
		
		ConfigManager.load();
		UsersManager.load();
		
		ServerSocket serverSocket = null;
        Socket socket = null;

        try {
			DatagramSocket SocketUDP = new DatagramSocket(SERVER_PORT_UDP);
			System.out.println("Le serveur UDP est fonctionnel");
		} catch (SocketException e1) {
			e1.printStackTrace();
		}

        try {
            serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Le serveur est en marche et en attente de la connexion au port "+SERVER_PORT+" ...");
            
			
	        while (true) {
	            try {
	                socket = serverSocket.accept();
	                
	                System.out.println("Connexion r\u00E9ussie.");
	    			System.out.println("Attente de l'entr\u00e9e...");
	    			
	                new TCPClientConnectionThread(socket).start();
	            } catch (IOException e) {
	                System.out.println("I/O error: " + e);
	            }
	        }
	        
	        
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
        	try {
				serverSocket.close();
			} catch (IOException e) {
				System.out.println("UH?");
			}
        }
		
	}
	
	
	
}
