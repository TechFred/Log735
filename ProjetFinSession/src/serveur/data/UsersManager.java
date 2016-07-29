package serveur.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;


public class UsersManager {
	private final static String filename = "src/serveur/data/users";
	private static Properties prop = new Properties();
	
	public static void load(){
		InputStream input = null;
		try {
			input = new FileInputStream(filename);
			prop.load(input);
			input.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void save(){
		OutputStream output = null;
		try {
			output = new FileOutputStream(filename);
			prop.store(output, null);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static String getUserPassword(String user){
		return prop.getProperty(user);
	}

	public static void createNewUser(String user, String password) {
		// set the properties value
		prop.setProperty(user, password);
		save();
	}
	
}
