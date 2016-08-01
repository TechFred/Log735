package client.events;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import client.model.QuitUDP;
import client.model.Session;
import client.utils.TCPConnectionServeur;

public class DisconnectUser implements WindowListener {

	private JFrame f;

	public DisconnectUser(JFrame frame) {
		f = frame;
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent arg0) {

		int i = JOptionPane.showConfirmDialog(f, "Êtes-vous sure de vouloir quitter?");
		if (i == 0) {
			int roomUID = Session.getInstance().getLobby().getUid();
			int userUID = Session.getInstance().getUser().getUid();
			new UserRoomMessage().sendQuit(new QuitUDP(userUID, roomUID, false), Session.getInstance().getLobby());
			TCPConnectionServeur.getInstance().connectToServeur();
			TCPConnectionServeur.getInstance().logout();
			TCPConnectionServeur.getInstance().disconnectFromServeur();

			f.dispose();
			System.exit(0);

		}

	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

}
