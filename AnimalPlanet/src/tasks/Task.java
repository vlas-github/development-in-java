package tasks;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import server.ClientThread;

public class Task {

	
	private String 				description;
	private DataInputStream 	inputStream;
	private DataOutputStream 	outputStream;
	private ClientThread		client;
	
	
	public Task(String task, DataInputStream inputStream,
			DataOutputStream outputStream, ClientThread client) {

		this.description = task;
		this.inputStream = inputStream;
		this.outputStream = outputStream;
		this.client = client;
	}
	
	
	public ClientThread getClient() {
		return client;
	}
	
	
	public String getDescription() {
		return description;
	}

	
	public DataInputStream getInputStream() {
		return inputStream;
	}

	
	public DataOutputStream getOutputStream() {
		return outputStream;
	}
}
