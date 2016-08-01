package client.events;

import client.model.Session;
import client.model.User;
import client.utils.InChatPrebuildMessages;
import client.utils.UtilsSendUDP;

import java.util.ArrayList;

import client.model.AnnounceUDP;
import client.model.MessageUDP;
import client.model.QuitUDP;
import client.model.Room;

public class UserRoomMessage {

	private Room lobby = Session.getInstance().getLobby();
	private ArrayList<Room> roomList = Session.getInstance().getRooms();

	public UserRoomMessage() {
	}

	public void sendMessage(MessageUDP m, Room r) {
		System.out.println("Message envoyé: " + m.getMessage());
		for (User u : r.getUsers()) {
			UtilsSendUDP.SendUDP(m, u.getIpAddress(), u.getPort());
		}
	}

	public void receiveMessage(MessageUDP m) {
		Session session = Session.getInstance();
		System.out.println("Message Reçu: " + m.getRoomUID() + " - " + m.getMessage());

		if (session.getLobby().getUid() == m.getRoomUID()) {
			InChatPrebuildMessages.showMessageInChat(m.getMessage(), session.getLobby(), m.getUserUID());
		} else {
			for (Room r : session.getRooms()) {
				if (r.getUid() == m.getRoomUID()) {
					InChatPrebuildMessages.showMessageInChat(m.getMessage(), r, m.getUserUID());
					break;
				}

			}
		}
	}

	public void receiveAnnounce(AnnounceUDP a) {
		Session session = Session.getInstance();
		System.out.println("Announce Reçu: " + a.getRoomID() + " - " + a.getNickname());

		if (session.getLobby().getUid() == a.getRoomID()) {
			User user = new User(a.getUserUID(), a.getNickname(), a.getIpAdress(), Integer.toString(a.getPort()));
			session.getLobby().addUser(user);
			session.getLobby().refreshListeUsers();
			InChatPrebuildMessages.showMessageUserJoined(user, session.getLobby());

		} else {
			for (Room r : session.getRooms()) {
				if (r.getUid() == a.getRoomID()) {
					User user = new User(a.getUserUID(), a.getNickname(), a.getIpAdress(),
							Integer.toString(a.getPort()));
					r.addUser(user);
					r.refreshListeUsers();
					InChatPrebuildMessages.showMessageUserJoined(user, r);
					break;
				}

			}
		}
	}

	public void sendAnnounce(AnnounceUDP a, Room r) {
		System.out.println("Announces sent");
		for (User u : r.getUsers()) {
			UtilsSendUDP.SendUDP(a, u.getIpAddress(), u.getPort());
		}

	}

	public void receiveQuit(QuitUDP q) {
	System.out.println("Quit Reçu");
		Room r;
		if (q.getRoomUID() == lobby.getUid()) {
			r = lobby;
			User u = r.remove(q.getUserUID());
			InChatPrebuildMessages.showMessageUserQuit(u, r);
		} else if ((r = findRoomUID(q.getRoomUID())) != null) {
			
			r.remove(q.getUserUID());
			User  u = r.remove(q.getUserUID());
			InChatPrebuildMessages.showMessageUserQuit(u, r);
		} else {
			System.out.println("Salle non trouvé");
		}
	}

	public void sendQuit(QuitUDP q, Room r) {
		System.out.println("Quits sent");
		ArrayList<User> Users = (ArrayList<User>) r.getUsers().clone();
		for (User u : Users) {
			UtilsSendUDP.SendUDP(q, u.getIpAddress(), u.getPort());
		}

		// Envoyer au serveur en dernier.
	}

	private Room findRoomUID(int uid) {
		Room roomFound = null;
		for (Room r : this.roomList) {
			if (r.getUid() == uid) {
				roomFound = r;
			}
		}
		return roomFound;
	}

	private User findUserUID(int uid, Room r) {
		User userFound = null;
		for (User u : r.getUsers()) {
			if (u.getUid() == uid) {
				userFound = u;
			}
		}
		return userFound;
	}

}
