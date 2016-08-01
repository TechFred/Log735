package client;

import java.net.DatagramSocket;
import java.net.SocketException;

import client.thread.UDPConnectionThread;
import client.ui.JFrameConnectionServeur;

public class MainClient {
	private final static int CLIENT_UDP_PORT = 0; // port 0 = port libre. 
	public static int localPort;
	public static void main(String[] args) {
		startSocket();
		
		// Debug test
		/*
		User user = new User();
		user.setIpAddress("127.0.0.1");
		user.setPort(localPort);
		ArrayList<User> arrayUser = new ArrayList<User>();
		arrayUser.add(user);
		Room room = new Room();
		room.setUid(2);
		room.setUsers(arrayUser);
		UserRoomMessage urm = new UserRoomMessage();
		*/
		//MessageUDP msg = new MessageUDP(room.getUid(), "allo");
		//urm.sendMessage(msg, room);
		
		// End of debug
		
		JFrameConnectionServeur frame = new JFrameConnectionServeur();
		frame.setVisible(true);

	}

	public static String encrypt(String md5) {
		try {
			md5 = "un)Peu[De.Sel" + md5 + "et?Du*Poivre" + md5 + "!";
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
		}
		return null;
	}

	private static void startSocket() {
		DatagramSocket socket;
		try {
			socket = new DatagramSocket(CLIENT_UDP_PORT);
			new UDPConnectionThread(socket).start();
			localPort = socket.getLocalPort();
		} catch (SocketException e) {
			e.printStackTrace();
			System.out.println("Erreur, le thread UDP n'a pas démarré");
		}


	}

}
