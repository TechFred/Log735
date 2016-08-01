package client.thread;

import client.model.Session;
import client.utils.TCPConnectionServeur;
import client.utils.UtilsSendUDP;

public class ThreadLifebeat extends Thread{
	private int sleepTime = 10*1000;
	public void run(){
		
		while(true){
			try {
				Thread.sleep(sleepTime);
				UtilsSendUDP.SendUDP(new Integer(Session.getInstance().getUser().getUid()), TCPConnectionServeur.getInstance().getServerIp(), 9002);
			} catch (InterruptedException e) {
			}
		}
	}
}
