package client;


import java.io.DataOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class SenderServer extends Thread {

	
	private DataOutputStream 	outputStream;
	private boolean 			isClose;
	private List<String>		commands;
	
	
	public SenderServer(DataOutputStream outputStream) {

		this.outputStream 	= outputStream;
		this.isClose		= false;
		this.commands		= new LinkedList<>();
	}
	
	
	@Override
	public void run() {
		
		while (true) {
			
			if(isClose) {
				
				break;
			}
			
			synchronized (commands) {
				
				while (commands.isEmpty()) {
					
					try {
						
						commands.wait();
						
					} catch (InterruptedException e) {

						e.printStackTrace();
					}
				}
				
				String command = commands.remove(0);
				
				try {
					
					outputStream.writeUTF(command);

				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}
	}
	
	
	public void send(String command) {
		
		synchronized (commands) {
			
			commands.add(command);
			commands.notify();
		}
	}
	
	
	public void close() {
		
		isClose = true;
	}
}
