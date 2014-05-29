package server;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import javax.xml.bind.JAXBException;

import tasks.TaskManager;
import dao.StorageOfFood;


public class MainServer {
	
	
	public static final Properties PROPERTIES = new Properties();
	
	
	private List<ClientThread> clients;
	private ServerSocket 		ss;
	private ListenerClients 	lc;
	private NotifierClients		nc;
	private StorageOfFood		food;
	private Scanner				scanner;
	private TaskManager			taskManager;
	
	
	public void start() throws IOException, JAXBException {
		
		System.out.println("Server starting...");
		
		int port = Integer.parseInt(PROPERTIES.getProperty("ServerPort"));
		
		this.ss = new ServerSocket(port);
		
		this.clients = new LinkedList<>();
		
		this.food = StorageOfFood.getInstance();
		
		this.taskManager = new TaskManager(this, food);
		this.taskManager.start();

		this.lc = new ListenerClients(clients, ss, taskManager);
		this.lc.start();
		
		this.nc = new NotifierClients(clients);
		nc.start();
		
		this.scanner = new Scanner(System.in);
		
		System.out.println("Server start");
	}
	
	
	public void run() throws IOException, JAXBException {

		System.out.println("Server running...");
		
		while(true) {
			
			String command = scanner.nextLine();
			
			if (command.equals("close"))
				break;

			doCommand(command);
		}
	}
	
	
	public void stop() throws IOException {
		
		System.out.println("Server closing...");
		
		this.nc.close();
		this.lc.close();
		
		synchronized (clients) {
			
			for(ClientThread client : clients) {
				
				client.close();
			}
		}
		
		this.ss.close();
		this.scanner.close();
		
		this.food.save();

		System.out.println("Server close");
	}
	
	
	public ListenerClients getListenerClients() {
		
		return lc;
	}
	
	
	private void doCommand(String command) throws IOException, JAXBException {
		
		if(command.split(" ")[0].equals("notify")) {
			
			String param = command.substring(6);
			
			nc.notify(param);
		}
	}
	
	
	public static void loadPropery() {
		
		try {
			
			PROPERTIES.load(new InputStreamReader(new FileInputStream("AnimalPlanet.property")));
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	
	public static void main(String [] arg) {
		
		loadPropery();
		
		MainServer server = new MainServer();
		
		try {

			server.start();
			server.run();
			server.stop();
			
		} catch (IOException e) {

			e.printStackTrace();
			
		} catch (JAXBException e) {

			e.printStackTrace();
		}
	}
}
