package serveur.data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;


public class ConfigManager {
	private final static String filename = "src/serveur/data/configs";
	
	public static int getNextUserId() {
		int id = 0;
		Properties prop = new Properties();
		OutputStream output = null;
		InputStream input = null;

		try {

			input = new FileInputStream(filename);
			prop.load(input);
			input.close();
			output = new FileOutputStream(filename);
			
			id = Integer.parseInt(prop.getProperty("nextUserId"));
			
			// set the properties value
			prop.setProperty( "nextUserId", ""+(id<2000000000?id+1:1) );

			// save properties to project root folder
			prop.store(output, null);
			
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
		return id;
	}
	
}
