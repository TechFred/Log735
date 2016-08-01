package client.events;

import client.model.Session;
import client.model.User;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

import client.model.AnnounceUDP;
import client.model.MessageUDP;
import client.model.Room;

public class UserRoomMessage {

	public UserRoomMessage() {
	}

	public void sendMessage(MessageUDP m, Room r) {
		SendUDP(m, r);
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

			session.getLobby().addUserMessage(m.getMessage());

		} else {
			for (Room r : session.getRooms()) {
				if (r.getUid() == m.getRoomUID()) {
					// Trouver la fenêtre avec le bon roomUID. Envoyer le
					// message à
					// la méthode

					r.addUserMessage(m.getMessage());
					break;
				}
			}
		}

	}

	public void sendAnnounce(AnnounceUDP a, Room r) {
		SendUDP(a, r);

	}

	private void SendUDP(Object ob, Room r) {

		try {
			ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
			ObjectOutputStream os = new ObjectOutputStream(outSteam);
			os.writeObject(ob);
			byte[] payload = outSteam.toByteArray();

			// Do While pour tous les users du room.
			for (User u : r.getUsers()) {
				InetAddress ipAdress = InetAddress.getByName(u.getIpAddress());
				DatagramPacket sendPacket = new DatagramPacket(payload, payload.length, ipAdress, u.getPort());
				DatagramSocket socket = new DatagramSocket();
				socket.send(sendPacket);
				System.out.println("Message envoyé");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
