package client.utils;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import client.model.Room;
import client.model.User;

public class UtilsSendUDP {

	
	public static void SendUDP(Object ob, Room r) {

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
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
