package client.utils;

import java.text.DecimalFormat;
import java.time.chrono.MinguoChronology;
import java.util.Date;

import client.model.Room;
import client.model.User;

public class InChatPrebuildMessages {

	public static void showMessageInChat(String message, Room room, int userUID) {
		boolean userFound = false;
		for (User u : room.getUsers()) {
			if (userUID == u.getUid()) {
				String nickname = "<" + u.getUsername() + ">";
				room.addUserMessage(getDate() + nickname + " " + message);
				userFound = true;
				break;
			}
		}
		if (!userFound) {
			System.out.println("Error, user not found");
		}
	}

	public static void showMessageUserJoined(User user, Room r) {
		// r.addUserMessage("<HTML><FONT COLOR=GREEN>" + getDate() + "*** JOINS:
		// " + user.getUsername() + "(" + user.getIpAddress() +
		// ")</FONT></HTML>");
		r.addUserMessage(getDate() + "*** JOINS: " + user.getUsername() + " (" + user.getIpAddress() + ")");
	}

	public static void showMessageUserQuit(User user, Room r){
		r.addUserMessage(getDate() + "*** QUITS: " + user.getUsername() + "(" + user.getIpAddress() + ")");
	}
	
	public static void showMessageUserTimeout(User user, Room r){
		r.addUserMessage(getDate() + "*** TIMEOUT: " + user.getUsername() + "(" + user.getIpAddress() + ")");

	}
	
	private static String getDate() {
		DecimalFormat df = new DecimalFormat("00");
		Date dt = new Date();
		String hours = df.format(dt.getHours());
		String mins = df.format(dt.getMinutes());
		return ("[" + hours + ":" + mins + "] ");

	}
}
