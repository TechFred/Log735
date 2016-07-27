package serveur;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;


public class UsersManager {
	
	public String getUserPassword(String user){
		String pw = null;
		
		Properties prop = new Properties();
		InputStream input = null;
		
		try {

			input = new FileInputStream("data/users");
			// load a properties file
			prop.load(input);

			// get the property value and print it out
			pw = prop.getProperty(user);

			
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
		
		return pw;
	}

	public void createNewUser(String user, String password) {
		
		Properties prop = new Properties();
		OutputStream output = null;
		InputStream input = null;

		try {

			String filename = "data/users";
			input = new FileInputStream(filename);
			prop.load(input);
			input.close();
			output = new FileOutputStream(filename);

			// set the properties value
			prop.setProperty(user, password);

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
	}
	
}
