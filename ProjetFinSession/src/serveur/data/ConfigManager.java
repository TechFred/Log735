package serveur.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;


public class ConfigManager {
	private final static String filename = "src/serveur/data/configs";
	
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
	
	public static int getNextUserId() {
		int id = Integer.parseInt(prop.getProperty("nextUserId"));
		// set the properties value
		prop.setProperty( "nextUserId", ""+(id<2000000000?id+1:1) );
		
		save();
		return id;
	}
	
	public static int getNextRoomId() {
		int id = Integer.parseInt(prop.getProperty("nextRoomId"));
		// set the properties value
		prop.setProperty( "nextRoomId", ""+(id<2000000000?id+1:1) );
		
		save();
		return id;
	}
	
	
	
}
