package serveur.thread;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import serveur.LifeBeatManager;

public class UDPConnectionThread extends Thread {
	private DatagramSocket socketUDP;

	public UDPConnectionThread(DatagramSocket socketUDP) {
		this.socketUDP = socketUDP;
	}

	public void run() {

		while (true) {

			System.out.println("UDP started");
			try {
				System.out.println("Listening UDP port: " + socketUDP.getLocalPort());
				byte[] incomingData = new byte[2048];

				while (true) {
					
					DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
					socketUDP.receive(incomingPacket);
					byte bytes[] = incomingPacket.getData();
					ObjectInputStream is = new ObjectInputStream(new ByteArrayInputStream(bytes));
					Serializable messageReceived = (Serializable) is.readObject();
					try {
						System.out.println("Yo! "+(Integer)messageReceived);
						if (messageReceived instanceof Integer) {
							Integer life = (Integer) messageReceived;
							LifeBeatManager.receiveLifeBeat(life);
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
