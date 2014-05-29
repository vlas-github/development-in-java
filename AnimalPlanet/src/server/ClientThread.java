package server;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import tasks.Task;
import tasks.TaskManager;


public class ClientThread extends Thread {
	
	
	private Socket 				client;	
	private boolean 			isClose;
	private DataOutputStream 	outputStream;
	private DataInputStream 	inputStream;
	private TaskManager 		taskManager;
	
	
	public ClientThread(Socket client, TaskManager taskManager) {
		
		this.client 		= client;
		this.isClose 		= false;
		this.taskManager	= taskManager;
	}
	
	
	@Override
	public void run() {
		
		try {
			
			outputStream = new DataOutputStream(client.getOutputStream());
			inputStream = new DataInputStream(client.getInputStream());
		
			while (true) {
				
				if(isClose) {
					
					break;
				}
				
				if (inputStream.available() == 0)
					continue;
				
				String task = inputStream.readUTF();
				
				taskManager.add(new Task(task, inputStream, outputStream, this));
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		} finally {
			
			try {
				
				closeResurses();
				
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}
	
	
	public void close() {
		
		isClose = true;
	}
	
	
	public boolean isClose() {
		
		return isClose;
	}
	
	
	private void closeResurses() throws IOException {
		
		if (inputStream != null) {
			
			inputStream.close();
		}
		
		if (outputStream != null) {
			
			inputStream.close();
		}
		
		client.close();
	}
	
	
	public void notify(String notice) throws IOException {
		
		synchronized (outputStream) {
			
			outputStream.writeUTF(notice);
		}
	}
}
