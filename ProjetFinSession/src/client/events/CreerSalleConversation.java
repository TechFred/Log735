package client.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import client.model.Room;
import client.model.Session;
import client.ui.JFrameRoom;
import client.utils.TCPConnectionServeur;

public class CreerSalleConversation implements ActionListener {
	
	private JFrame 		f;
	
	private JTextField 	roomName,
						roomPassword;
	
	public CreerSalleConversation(JFrame frame, JTextField nom, JTextField mdp){
		this.f = frame;
		this.roomName = nom;
		this.roomPassword = mdp;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(roomName.getText().length() > 0){
			
			TCPConnectionServeur.getInstance().connectToServeur();
			int idRoom = TCPConnectionServeur.getInstance().tryCreateRoom(roomName.getText(), roomPassword.getText());
			TCPConnectionServeur.getInstance().disconnectFromServeur();
			if(idRoom > 0){
				
				JFrameRoom frameR = new JFrameRoom();
				Room room = new Room(idRoom, roomName.getText(), roomPassword.getText(), false, frameR);
				frameR.setRoom(room);
				room.addUser(Session.getInstance().getUser());
				frameR.setVisible(true);
				room.refreshListeUsers();
				Session.getInstance().getRooms().add(room);
				f.dispose();
				
			}
			
		}else{
			JOptionPane.showConfirmDialog(f, "Vous devez entre un nom pour la salle de conversation!");
		}
		
	}

}
