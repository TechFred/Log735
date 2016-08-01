package client.events;

import client.model.Session;
import client.model.User;
import client.utils.UtilsSendUDP;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

import client.model.AnnounceUDP;
import client.model.MessageUDP;
import client.model.Room;

public class UserRoomMessage {

	public UserRoomMessage() {
	}

	public void sendMessage(MessageUDP m, Room r) {
		System.out.println("Message envoyé: " + m.getMessage());
		UtilsSendUDP.SendUDP(m, r);
		/*
		 * try { ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		 * ObjectOutputStream os = new ObjectOutputStream(outSteam);
		 * os.writeObject(m); byte[] payload = outSteam.toByteArray();
		 * 
		 * // Do While pour tous les users du room. for (User u : r.getUsers())
		 * { InetAddress ipAdress = InetAddress.getByName(u.getIpAddress());
		 * DatagramPacket sendPacket = new DatagramPacket(payload,
		 * payload.length, ipAdress, u.getPort()); DatagramSocket socket = new
		 * DatagramSocket(); socket.send(sendPacket); System.out.println(
		 * "Message envoyé"); }
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 */

	}

	public void receiveMessage(MessageUDP m) {
		Session session = Session.getInstance();
		System.out.println("Message Reçu: " + m.getRoomUID() + " - " + m.getMessage());

		if (session.getLobby().getUid() == m.getRoomUID()) {
			showMessageInChat(m.getMessage(), session.getLobby(), m.getUserUID());
		} else {
			for (Room r : session.getRooms()) {
				if (r.getUid() == m.getRoomUID()) {
					showMessageInChat(m.getMessage(), r, m.getUserUID());
					break;
				}

			}
		}
	}

	public void sendAnnounce(AnnounceUDP a, Room r) {
		UtilsSendUDP.SendUDP(a, r);

	}

	private void showMessageInChat(String message, Room room, int userUID) {
		boolean userFound = false;
		for (User u : room.getUsers()) {
			if (userUID == u.getUid()) {
				Date dt = new Date();
				String hoursMinute = ("[" + dt.getHours() + ":" + dt.getMinutes() + "]");
				String nickname = "<" + u.getUsername() + ">";
				room.addUserMessage(hoursMinute + " " + nickname + " " + message);
				userFound = true;
				break;
			}
		}
		if (!userFound) {
			System.out.println("Error, user not found");
		}
	}

}
