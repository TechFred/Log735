package serveur.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import serveur.model.OnlineUser;
import serveur.model.Room;
import serveur.model.Session;

public class RoomManager {
	private static String usersFile = "src/serveur/dataRoom.dat";
	
	public static ArrayList<Room> load(){
		ArrayList<Room> l = new ArrayList<Room>();
		
		FileInputStream fis;
		try {
			fis = new FileInputStream(usersFile);
			ObjectInputStream ois = new ObjectInputStream(fis);
			l = (ArrayList<Room>) ois.readObject();
		} catch (FileNotFoundException e) {
			l = new ArrayList<Room>();
		} catch (IOException e) {
			l = new ArrayList<Room>();
		} catch (ClassNotFoundException e) {
			l = new ArrayList<Room>();
		}
		
		for (Room r : l) {
			System.out.println("LOADED ROOM: "+r.getUname());
		}
		
		return l;
	}
	
	public static void save(){
		
		try {
			
			FileOutputStream fos = new FileOutputStream(usersFile);
			
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(Session.getInstance().getListeRooms());
			
			oos.flush();
			oos.close();
		} catch (IOException e) {
			System.err.println("Erreur lors de la sauvegarde des chatrooms!");
			e.printStackTrace();
		}
		
	}
	
}
