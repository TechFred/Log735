package client.thread;

import client.model.Session;
import client.utils.TCPConnectionServeur;
import client.utils.UtilsSendUDP;

public class ThreadLifebeat extends Thread{
	private int sleepTime = 10*1000;
	public void run(){
		Integer id = new Integer(Session.getInstance().getUser().getUid());
		while(true){
			try {
				Thread.sleep(sleepTime);
				System.out.println("lifeBeat!");
				UtilsSendUDP.SendUDP(id , TCPConnectionServeur.getInstance().getServerIp(), 9002);
			} catch (InterruptedException e) {
			}
		}
	}
}
