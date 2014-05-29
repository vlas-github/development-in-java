package server;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class NotifierClients extends Thread {

	
	private List<ClientThread> 	clients;
	private List<String> 		notice;
	private boolean 			isClose;

	
	public NotifierClients(List<ClientThread> clients) {
		
		this.clients 	= clients;
		this.notice		= new LinkedList<>();
	}
	
	
	@Override
	public void run() {
		
		while (true) {
			
			if (isClose) {
				
				break;
			}
			
			synchronized (notice) {
				
				while(notice.isEmpty()) {
					
					try {
						
						notice.wait();
						
					} catch (InterruptedException e) {

						e.printStackTrace();
					}
				}
				
				String notice = this.notice.remove(0);
				
				synchronized (clients) {
				
					for(ClientThread client : clients) {
						
						try {

							client.notify(notice);
							
						} catch (IOException e) {
							
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	
	public void close() {
		
		isClose = true;
	}
	
	
	public void notify(String notify) {
		
		synchronized (notice) {
			
			notice.add(notify);
			notice.notify();
		}
	}
}
