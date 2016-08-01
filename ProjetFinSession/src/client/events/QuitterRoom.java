package client.events;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import client.model.QuitUDP;
import client.model.Room;
import client.model.Session;
import client.ui.JFrameRoom;
import client.utils.TCPConnectionServeur;
import client.utils.UserRoomMessage;

public class QuitterRoom implements WindowListener {
	private JFrame f;
	private Room r;
	
	public QuitterRoom(Room room, JFrameRoom jFrameRoom) {
		this.f = jFrameRoom;
		this.r = room;
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		int i = JOptionPane.showConfirmDialog(f, "Êtes-vous sure de vouloir quitter cette salle de conversation?");
		if (i == 0) {
			int roomUID = r.getUid();
			int userUID = Session.getInstance().getUser().getUid();
			
			new UserRoomMessage().sendQuit(new QuitUDP(userUID, roomUID, false), r);
			
			TCPConnectionServeur.getInstance().connectToServeur();
			TCPConnectionServeur.getInstance().leaveRoom( userUID, r.getUid() );
			TCPConnectionServeur.getInstance().disconnectFromServeur();
			
			Session.getInstance().quitterRoom(r.getUid());
			
			f.dispose();
		}
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		
	}
}
