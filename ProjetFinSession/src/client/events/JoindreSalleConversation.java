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

public class JoindreSalleConversation implements ActionListener {
	
	private JFrame 		f;
	
	private JTextField 	roomName,
						roomPassword;
	
	public JoindreSalleConversation(JFrame frame, JTextField nom, JTextField mdp){
		this.f = frame;
		this.roomName = nom;
		this.roomPassword = mdp;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(roomName.getText().length() > 0){
			
			TCPConnectionServeur.getInstance().connectToServeur();
			int idRoom = TCPConnectionServeur.getInstance().tryJoinRoom(roomName.getText(), roomPassword.getText());
			
			if(idRoom > 0){
				
				JFrameRoom frameR = new JFrameRoom();
				Room room = new Room(idRoom, roomName.getText(), roomPassword.getText(), false, frameR);
				frameR.setRoom(room);
				
				room.setUsers(TCPConnectionServeur.getInstance().retreiveRoomUsers(idRoom));
				room.refreshListeUsers();
				room.joinRoomAnnounce(Session.getInstance().getUser());
				
				
				frameR.setVisible(true);

				Session.getInstance().getRooms().add(room);
				f.dispose();
				
			}
			TCPConnectionServeur.getInstance().disconnectFromServeur();
			
		}else{
			JOptionPane.showConfirmDialog(f, "Vous devez entre un nom pour la salle de conversation!");
		}
		
	}

}
