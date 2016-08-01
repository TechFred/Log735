package client.ui;

import client.model.User;

public interface FrameConvo {
	public void refreshListeUsers(User[] u);
	public void refreshUserMessage(String message);
}
