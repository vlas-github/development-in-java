package animalPlanet;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.bind.JAXBException;

import dao.StorageOfFood;
import localisation.EngLocal;
import entity.Animal;
import entity.Food;
import foodExceptions.AnimalIllegalArgumentException;


public class Main {
	
	public static final Properties PROGRAM_PROPERTIES = new Properties();
	
	private static void printEat(Iterable<Food> list) {
		
		for (Food eat : list) {
			
			System.out.println(eat);
		}
	}
	
	
	private static void test() throws IOException, JAXBException {
		
		final int COUNT_SEASON = 10;
		final int COUNT_ITERATION_IN_SEASON = 20;
		
		System.out.println(EngLocal.TITLE_USER_INTERFACE);
		
		PROGRAM_PROPERTIES.load(new InputStreamReader(new FileInputStream("AnimalPlanet.property")));
		
		StorageOfFood storage = StorageOfFood.getInstance();
		
		List<Food> foods = new ArrayList<Food>(200);

		for (Food food : storage) {

			foods.add(food);
		}
				
		System.out.println(EngLocal.INPUT_USER_INTERFACE);
		
		printEat(foods);	
		
		for (int i = 1; i < COUNT_SEASON; i++) {
			
			for (int j = 0; j < COUNT_ITERATION_IN_SEASON; j++) {
				
				int randEats = (int)(Math.random() );
				int randEat = (int)(Math.random() * foods.size());
				
				if (randEat != randEats && foods.get(randEats) instanceof Animal) {
						
					Animal an = (Animal)foods.get(randEats);
					Food eat = foods.get(randEat);
					
					try {
						
						an.eat(eat);
						
					} catch (AnimalIllegalArgumentException ex) {
						
						System.out.println(ex);
					}
				}				
			}
			
			System.out.println();
			System.out.println(EngLocal.SEASON_USER_INTERFACE + i);
			System.out.println(EngLocal.SEPARETOR_USER_INTERFACE);
			printEat(foods);
		}
		
		System.out.println(EngLocal.END_TEST);
		
		storage.save();
	}
	
	
	public static void main(String [] arg) throws IOException {
		
		try {
			
			test();
			
		} catch (IOException ex) {
			
			System.out.println(EngLocal.FILE_IS_NOT_FOUND + ex);
		} catch (JAXBException ex){
			
			System.out.println(ex);
		}
	}
}
