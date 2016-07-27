package client.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import client.MainClient;
import client.Session;
import client.TCPConnectionServeur;
import client.ui.JFrameLobby;

public class UserAuthentification implements ActionListener {
	private JFrame frame;
	private JTextField usernameField;
	private JPasswordField passwordField;
	
	public UserAuthentification( JFrame f, JTextField txtUsername, JPasswordField txtPassword) {
		this.frame = f;
		this.usernameField = txtUsername;
		this.passwordField = txtPassword;
	}

	public void actionPerformed(ActionEvent arg0) {

		String 	user = usernameField.getText(),
				password = new String(passwordField.getPassword());
		
		String encryptedPassword = MainClient.encrypt(password);
		
		if(TCPConnectionServeur.getInstance().tryToLogin(user, encryptedPassword)){

			JFrameLobby lobby = new JFrameLobby();
			lobby.setVisible(true);
			
			frame.dispose();
			TCPConnectionServeur.getInstance().disconnectFromServeur();
			
		}
		
	}

}
