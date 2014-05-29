package tasks;


import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import server.MainServer;
import dao.FoodFactory;
import dao.StorageOfFood;
import entity.Animal;
import entity.Food;


public class TaskManager extends Thread {

	
	private StorageOfFood 	food;
	private List<Task> 		tasks;
	private boolean 		isClose;
	private MainServer		server;

	
	public TaskManager(MainServer server, StorageOfFood food) {

		this.food 		= food;
		this.tasks		= new LinkedList<>();
		this.isClose	= false;
		this.server		= server;
	}


	public void add(Task task) throws IOException {

		synchronized (tasks) {
			
			tasks.add(task);
			tasks.notify();
		}
	}

	
	@Override
	public void run() {
		
		while(true) {
			
			if (isClose) break;

			synchronized (tasks) {
				
				while (tasks.isEmpty()) {
					
					try {
						
						tasks.wait();
						
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
				}
				
				Task task = tasks.remove(0);
				
				try {
					
					execute(task);
					
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}			
		}
	}
	
	
	private void execute(Task task) throws IOException {
		
		String description = task.getDescription();
		
		System.out.println("Some client sent " + task.getDescription());
		
		String name = description.split(" ")[0];
		
		switch (name) {
		case "add":
			addFood(task);
			break;
			
		case "get-all":
			getAll(task);
			break;
			
		case "get":
			get(task);
			break;
			
		case "feed":
			feed(task);
			break;

		case "remove":
			remove(task);
			break;
			
		case "save":
			save(task);
			break;
		
		case "close" :
			close(task);
			break;
			
		case "help":
			help(task);
			break;
		}
	}

	
	public void close() {
		
		isClose = true;
	}

	
	private void close(Task task) throws IOException {
		
		server.getListenerClients().remove(task.getClient());
		
		System.out.println("Some client is closed");
	}


	private void save(Task task) throws IOException {
		
		String answer = "Successfully!";
		
		try {
			
			this.food.save();
		
		} catch(IOException e) {
			
			answer = "Error saving! Try the operation later...";
		}

		task.getOutputStream().writeUTF(answer);
	}


	private void remove(Task task) throws IOException {
		
		String param = task.getDescription().split(" ")[1];
		
		Integer index = Integer.valueOf(param);
		
		if (this.food.remove(this.food.get(index))) {
			
			task.getOutputStream().writeUTF("Successfully!");
			
		} else {
			
			task.getOutputStream().writeUTF("Food with such an index is not found!");
		}
		
		
	}


	private void addFood(Task task) throws IOException {
		
		String food = task.getDescription().substring(4);
		
		this.food.add(FoodFactory.make(food));
		
		String answer = "Successfully!";
		
		task.getOutputStream().writeUTF(answer);
	}


	private void feed(Task task) throws IOException {
		
		String param1 = task.getDescription().split(" ")[1];
		String param2 = task.getDescription().split(" ")[2];
		
		int indexEat = 	Integer.valueOf(param1);
		int indexEats = Integer.valueOf(param2);
		
		Food eat = this.food.get(indexEat);
		Food eats = this.food.get(indexEats);
		
		Animal animal = null;
		
		String answer = "Grass does not eat!";
		
		if (eat instanceof Animal) {
			
			animal = (Animal) eat;
			answer = "Successfully: " + animal.toString() + " ate " + eats.toString();
			
			try {
				
				animal.eat(eats);
				
			} catch (IllegalArgumentException e) {
				
				answer = e.toString();
			}
		}	
		
		task.getOutputStream().writeUTF(answer);
	}

	
	private void get(Task task) throws IOException {
		
		String param = task.getDescription().split(" ")[1];
		
		Integer index = Integer.valueOf(param);
		
		Food f = food.get(index);
		
		String answer = "Food with such an index is not found";
		
		if (f != null)
			answer = f.toString();
		
		task.getOutputStream().writeUTF(answer);
	}


	private void getAll(Task task) throws IOException {
		
		synchronized (food) {
			
			for(Food f : food) {
				
				synchronized (task) {
						
					task.getOutputStream().writeUTF(f.toString());
				}
			}
		}
	}
	
	private void help(Task task) throws IOException {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("Help\n");
		sb.append("add <description food>  adding new food that meets <description food>,\n");
		sb.append("                        where <description food> of the corresponding template:\n");
		sb.append("\n");
		sb.append("                        Predator {id; is alive; nutritional value; weight},\n");
		sb.append("                        Herbivorou {id; isAlive; nutritional value; weight},\n");
		sb.append("                        Plants {id; nutritional value; weight};\n");
		sb.append("\n");
		sb.append("                        id - any number;\n");
		sb.append("                        is alive - 0 or 1;\n");
		sb.append("                        nutritional value - [0..1];\n");
		sb.append("                        weight - any number > 0;\n");
		sb.append("\n");
		sb.append("get-all                 return all food from storage;\n");
		sb.append("\n");
		sb.append("get <index>             returns on the index of food;\n");
		sb.append("\n");
		sb.append("feed <index1> <index2>  animal feeds <index1> food <index2>;\n");
		sb.append("\n");
		sb.append("remove <index>          removes food index <index>;\n");
		sb.append("\n");
		sb.append("save                    instructs the server to keep the store with food;\n");
		sb.append("\n");
		sb.append("close                   disconnect from the server;\n");
		sb.append("\n");
		sb.append("help                    show this help.\n");
		
		task.getOutputStream().writeUTF(sb.toString());
	}
}
