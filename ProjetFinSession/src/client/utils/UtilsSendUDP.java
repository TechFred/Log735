package client.utils;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import client.model.Room;
import client.model.User;

public class UtilsSendUDP {

	public static void SendUDP(Object ob, String ip, int port) {

		try {
			ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
			ObjectOutputStream os = new ObjectOutputStream(outSteam);
			os.writeObject(ob);
			byte[] payload = outSteam.toByteArray();
			// Do While pour tous les users du room.

			InetAddress ipAdress = InetAddress.getByName(ip);
			DatagramPacket sendPacket = new DatagramPacket(payload, payload.length, ipAdress, port);
			DatagramSocket socket = new DatagramSocket();
			socket.send(sendPacket);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
