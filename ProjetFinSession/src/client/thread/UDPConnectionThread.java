package client.thread;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

import client.model.MessageUDP;

public class UDPConnectionThread extends Thread {
	private int port;

	public UDPConnectionThread(int port) {
		this.port = port;
	}

	public void run() {

		while (true) {
			DatagramSocket socketUDP = null;
			System.out.println("UDP started");
			try {
				socketUDP = new DatagramSocket(port);
				byte[] incomingData = new byte[2048];

				while (true) {
					DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
					socketUDP.receive(incomingPacket);
					System.out.println("UDP reçu");
					byte bytes[] = incomingPacket.getData();
					ObjectInputStream is = new ObjectInputStream(new ByteArrayInputStream(bytes));
					Serializable messageReceived = (Serializable) is.readObject();
					try {
						if (messageReceived instanceof MessageUDP) {
							MessageUDP messageUDP = (MessageUDP) messageReceived;
							System.out.println("UDP reçu: " + messageUDP.getMessage());
						} else {

						}

					} catch (Exception e) {
						e.printStackTrace();
					}

				}

			} catch (SocketException e) {
				e.printStackTrace();
			} catch (IOException i) {
				i.printStackTrace();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} finally {
				socketUDP.close();
			}

		}
	}
}
