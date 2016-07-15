package client.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class ConnectionServeur implements ActionListener {
	JTextField txtFieldIp;
	JTextField txtFieldPort;
	
	public ConnectionServeur(JTextField ip, JTextField port){
		this.txtFieldIp = ip;
		this.txtFieldPort = port;
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("IP:\t"+txtFieldIp.getText()+"\nPort:\t"+txtFieldPort.getText());
	}
}
