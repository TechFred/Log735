package client.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.ui.JFrameCreerSalleConversation;

public class MenuCreerSalleConversation implements ActionListener{
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		JFrameCreerSalleConversation f = new JFrameCreerSalleConversation();
		f.setVisible(true);
		
	}

}
