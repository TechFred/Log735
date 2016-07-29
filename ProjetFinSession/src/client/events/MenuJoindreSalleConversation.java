package client.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.ui.JFrameJoindreSalleConversation;

public class MenuJoindreSalleConversation implements ActionListener{
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		JFrameJoindreSalleConversation f = new JFrameJoindreSalleConversation();
		f.setVisible(true);
		
	}

}
