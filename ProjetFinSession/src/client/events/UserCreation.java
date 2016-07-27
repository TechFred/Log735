package client.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.sound.midi.SysexMessage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import client.MainClient;
import client.TCPConnectionServeur;
import client.ui.JFrameAuthentificationUtilisateur;

public class UserCreation implements ActionListener {
	private JFrame frame;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JPasswordField passwordFieldConfirm;
	
	public UserCreation( JFrame f, JTextField txtUsername, JPasswordField txtPassword, JPasswordField passwordField_1) {
		this.frame = f;
		this.usernameField = txtUsername;
		this.passwordField = txtPassword;
		this.passwordFieldConfirm = passwordField_1;
	}

	public void actionPerformed(ActionEvent arg0) {

		String 	user = usernameField.getText(),
				password = new String(passwordField.getPassword()),
				passwordC = new String(passwordFieldConfirm.getPassword());
		String encryptedPassword;
		
		
		
		if(password.equals(passwordC)){
			
				encryptedPassword = MainClient.encrypt(password);
				if(TCPConnectionServeur.getInstance().createUser(user, encryptedPassword)){
					JOptionPane.showMessageDialog(new JFrame(), "L'utilisateur à été crée avec succès!");
					JFrameAuthentificationUtilisateur auth = new JFrameAuthentificationUtilisateur();
					auth.setVisible(true);
					frame.dispose();
				}
			
		}else{
			JOptionPane.showMessageDialog(new JFrame(), "Les deux mots de passes ne coordonnent pas!");
		}
		
		
	}
	
}
