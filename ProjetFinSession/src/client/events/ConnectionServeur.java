package client.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import client.ui.JFrameAuthentificationUtilisateur;
import client.utils.TCPConnectionServeur;

public class ConnectionServeur implements ActionListener {
	JTextField txtFieldIp;
	JTextField txtFieldPort;
	JFrame frame;
	
	public ConnectionServeur(JFrame f, JTextField ip, JTextField port){
		this.txtFieldIp = ip;
		this.txtFieldPort = port;
		this.frame = f;
	}

	public void actionPerformed(ActionEvent e) {
		
		try{
			TCPConnectionServeur.getInstance().initServerCoordinates( txtFieldIp.getText(), Integer.parseInt(txtFieldPort.getText()) );
			TCPConnectionServeur.getInstance().connectToServeur();
			if(TCPConnectionServeur.getInstance().isConnectedToServeur()){
				JFrameAuthentificationUtilisateur auth = new JFrameAuthentificationUtilisateur();
				auth.setVisible(true);
				frame.dispose();
			}
			
		}catch(NumberFormatException err){
			JOptionPane.showMessageDialog(new JFrame(), "Le port entré doit être un nombre valide!");
		}
		
	}
	
}
