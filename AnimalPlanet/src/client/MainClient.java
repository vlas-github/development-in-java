package client;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.Scanner;


public class MainClient {

	
	public static final Properties PROPERTIES = new Properties();
	
	
	private Scanner 			scanner;
	private DataOutputStream 	outputStream;
	private DataInputStream 	inputStream;
	private ListenerServer 		listener;
	private Socket 				socket;
	private SenderServer		sender;
	
	
	public void start() throws UnknownHostException, IOException {
		
		System.out.println("Client starting...");
		
		this.scanner = new Scanner(System.in);
		
		String host = PROPERTIES.getProperty("ServerIP");
		Integer port = Integer.valueOf(PROPERTIES.getProperty("ServerPort"));
		this.socket = new Socket(host, port);
		
		this.outputStream = new DataOutputStream(this.socket.getOutputStream());
		this.inputStream = new DataInputStream(this.socket.getInputStream());
		
		this.listener = new ListenerServer(inputStream);
		this.listener.start();
		
		this.sender = new SenderServer(outputStream);
		this.sender.start();
		
		System.out.println("Client start");
	}
	
	
	public void run() {
		
		while(true) {
			
			String command = getNextCommand();
			
			this.sender.send(command);	
			
			if(command.equals("close"))
				break;				
		}
	}
	
	
	public void stop() throws IOException, InterruptedException {

		System.out.println("Client closing...");
		
		this.sender.close();
		this.sender.join();
		
		this.listener.close();
		this.outputStream.close();
		this.inputStream.close();
		this.socket.close();
		this.scanner.close();
		
		System.out.println("Client close");
	}

	
	private String getNextCommand() {
		
		String command = scanner.nextLine();
		
		return command;
	}
	
	
	private static void loadProperty() {
		
		try {
			
			PROPERTIES.load(new InputStreamReader(new FileInputStream("AnimalPlanet.property")));
			
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	
	public static void main(String [] arg) {

		loadProperty();

		MainClient server = new MainClient();
		
		try {
			
			server.start();
			server.run();
			server.stop();
			
		} catch (IOException | InterruptedException e) {

			e.printStackTrace();
		}
	}
}
