package dao;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import localisation.EngLocal;
import entity.Food;


public class StorageOfFood implements Iterable<Food> {

	
	private static final Properties PROPERTIES = new Properties();
	
	
	private static StorageOfFood instance = null;
	
	
	private List<Food> foods;	
	private static int counterFood;
	

	private StorageOfFood() throws IOException, JAXBException {
		
		String init = PROPERTIES.getProperty(EngLocal.PROPERTIES_INITIALIZATION);
		
		switch (init) {
		
			case EngLocal.DEFAULT_INITIALIZATION:
				
				defaultInit();
				break;
				
			case EngLocal.EMPTY_INITIALIZATION:
				
				emptyInit();
				break;
				
			case EngLocal.INITIALIZATION_FROM_FILE:
				
				initFromFile();
				break;
		}
	}


	public static StorageOfFood getInstance() throws IOException, JAXBException {

		if (instance == null) {
			
			PROPERTIES.load(new InputStreamReader(new FileInputStream("AnimalPlanet.property")));
			
			instance = new StorageOfFood();
		}

		return instance;
	}


	public void save() throws IOException {
		
		String file = PROPERTIES.getProperty(EngLocal.PROPERTIES_INITIALIZATION_FILE);
		
		OutputStream outStream = new FileOutputStream(file);
		
		try {
			
			JAXBContext c = JAXBContext.newInstance(FileOfFood.class);
			
			Marshaller m = c.createMarshaller();
			
			FileOfFood f = new FileOfFood();
			f.setFoods(foods);
			
			m.marshal(f, outStream);
			
		} catch (JAXBException e) {

			e.printStackTrace();
		}

		outStream.close();
	}
	

	public boolean add(Food food) {

		food.setId(getCounterFood());
		
		return foods.add(food);
	}


	public boolean addAll(Collection<Food> c)  {

		for(Food food : c) {
			
			food.setId(getCounterFood());
		}
		
		return foods.addAll(c);
	}

	
	public boolean addAll(String file) throws IOException, JAXBException {

		return foods.addAll(readFile(file));
	}


	public void clear() {

		emptyInit();
	}


	public boolean remove(Food f) {
		
		return foods.remove(f);
	}
	
	
	@Override
	public Iterator<Food> iterator() {
		
		return foods.iterator();
	}
	

	public Food get(int index) {
		
		for(Food f : foods) {
			
			if (f.getId() == index)
				return f;
		}
		
		return null;
	}
	
	
	private void emptyInit() {
		
		foods = new LinkedList<Food>();
		
		counterFood = 0;
	}


	private void defaultInit() {

		emptyInit();

		foods.add(FoodFactory.make("Predator {" + getCounterFood() + "; 1; 0.20; 425.95}"));
		foods.add(FoodFactory.make("Predator {" + getCounterFood() + "; 1; 0.20; 75.54}"));
		foods.add(FoodFactory.make("Predator {" + getCounterFood() + "; 1; 0.20; 48.97}"));
		foods.add(FoodFactory.make("Predator {" + getCounterFood() + "; 1; 0.20; 3.31}"));
		foods.add(FoodFactory.make("Predator {" + getCounterFood() + "; 1; 0.20; 48.05}"));

		foods.add(FoodFactory.make("Herbivorou {" + getCounterFood() + "; 1; 0.60; 14.61}"));
		foods.add(FoodFactory.make("Herbivorou {" + getCounterFood() + "; 1; 0.60; 88.06}"));
		foods.add(FoodFactory.make("Herbivorou {" + getCounterFood() + "; 1; 0.60; 97.13}"));
		foods.add(FoodFactory.make("Herbivorou {" + getCounterFood() + "; 1; 0.60; 32.64}"));
		foods.add(FoodFactory.make("Herbivorou {" + getCounterFood() + "; 1; 0.60; 42.69}"));

		foods.add(FoodFactory.make("Plants {" + getCounterFood() + "; 0.90; 34.38}"));
		foods.add(FoodFactory.make("Plants {" + getCounterFood() + "; 0.90; 44.52}"));
		foods.add(FoodFactory.make("Plants {" + getCounterFood() + "; 0.90; 74.15}"));
		foods.add(FoodFactory.make("Plants {" + getCounterFood() + "; 0.90; 22.95}"));
		foods.add(FoodFactory.make("Plants {" + getCounterFood() + "; 0.90; 16.65}"));
	}


	private void initFromFile() throws IOException, JAXBException {

		emptyInit();
		
		String file = PROPERTIES.getProperty(EngLocal.PROPERTIES_INITIALIZATION_FILE);
		
		addAll(readFile(file));
	}
	

	private Collection<Food> readFile(String file) throws IOException, JAXBException {

		JAXBContext c = JAXBContext.newInstance(FileOfFood.class);
		Unmarshaller u = c.createUnmarshaller();		
		
		FileOfFood storage = (FileOfFood)u.unmarshal(new File(file));
		
		return storage.getFoods();
	}
	
	
	private int getCounterFood() {
		
		return counterFood++;
	}
}