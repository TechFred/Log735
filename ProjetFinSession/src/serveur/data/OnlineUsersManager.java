package serveur.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import serveur.model.OnlineUser;
import serveur.model.Session;

public class OnlineUsersManager {
	private static String usersFile = "src/serveur/dataonlineUsers.dat";
	public static ArrayList<OnlineUser> load(){
		ArrayList<OnlineUser> l = new ArrayList<>();
		
		FileInputStream fis;
		try {
			fis = new FileInputStream(usersFile);
			ObjectInputStream ois = new ObjectInputStream(fis);
			l = (ArrayList<OnlineUser>) ois.readObject();
		} catch (FileNotFoundException e) {
			l = new ArrayList<OnlineUser>();
		} catch (IOException e) {
			l = new ArrayList<OnlineUser>();
		} catch (ClassNotFoundException e) {
			l = new ArrayList<OnlineUser>();
		}
		
		for (OnlineUser onlineUser : l) {
			System.out.println("LOADED: "+onlineUser.getUsername());
			onlineUser.setLifeBeat(System.currentTimeMillis());
		}
		
		return l;
	}
	
	public static void save(){
		
		try {
			FileOutputStream fos = new FileOutputStream(usersFile);
			
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(Session.getInstance().getLobby().getListeUsers());
			
			oos.flush();
			oos.close();
		} catch (IOException e) {
			System.err.println("Erreur lors de la sauvegarde des utilisateurs online!");
			e.printStackTrace();
		}
		
	}
	
}
