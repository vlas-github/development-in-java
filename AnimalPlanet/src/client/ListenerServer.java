package client;

import java.io.DataInputStream;
import java.io.IOException;

public class ListenerServer extends Thread {

		
	private DataInputStream inputStream = null;
	private boolean isClose = false;
	
	
	public ListenerServer(DataInputStream inputStream) {

		this.inputStream = inputStream;
	}
	
	
	@Override
	public void run() {
		
		while (true) {
			
			if(isClose) {
				
				break;
			}

			try {
				
				if (inputStream.available() != 0) {	
					
					System.out.println(">>>" + inputStream.readUTF());
				}
				
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}
	
	
	public void close() {
		
		isClose = true;
	}
}