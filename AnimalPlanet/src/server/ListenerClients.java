package server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

import tasks.TaskManager;


public class ListenerClients extends Thread {
	
	
	private List<ClientThread> clients;
	private List<ClientThread> clientsForRemove;
	private ServerSocket 		ss;
	private TaskManager			taskManager;
	private boolean 			isClose;
	

	public ListenerClients(List<ClientThread> clients, ServerSocket ss, TaskManager taskManager) {

		this.clients 			= clients;
		this.clientsForRemove	= new LinkedList<>();
		this.ss 				= ss;
		this.taskManager		= taskManager;
	}
	
	
	@Override
	public void run() {
		
		while (true) {
			
			if (isClose) {
				
				break;
			}
			
			try {
				
				Socket s = ss.accept();
				
				System.out.println("New client accept!");
				
				ClientThread ct = new ClientThread(s, taskManager);			

				synchronized (clients) {
					
					clients.add(ct);
				}
				
				ct.start();
				
				synchronized (clientsForRemove) {
					
					for(ClientThread client : clientsForRemove) {
						
						synchronized (clients) {
							
							clients.remove(client);
							clientsForRemove.remove(client);
						}
					}
				}

			} catch (IOException e) {

				e.printStackTrace();
			}
		}		
	}
	
	
	public void remove(ClientThread client) {
		
		synchronized (clientsForRemove) {
			
			clientsForRemove.add(client);
		}
	}

	
	public void close() {
		
		isClose = true;
	}
}
