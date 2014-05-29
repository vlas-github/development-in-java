package dao;


import entity.Food;
import entity.Herbivorou;
import entity.Plants;
import entity.Predator;


public class FoodFactory {
	

	public static Food make(String descriptionFood) {

		
		Food food = null;
		
		String [] classFoodDescription = descriptionFood.split("\\{");
		
		String nameClassFood = classFoodDescription[0].replaceAll("\\s", "");
	
		String [] parametresFood = classFoodDescription[1].replaceAll("}", "")
				.replace(" ", "").replace(",", ".").split(";");

		switch (nameClassFood) {
		
			case "Predator":
	
				food = makePredator(Integer.parseInt(parametresFood[0]),
						Integer.parseInt(parametresFood[1]) == 1,
						Double.parseDouble(parametresFood[2]),
						Double.parseDouble(parametresFood[3]));
				break;
				
			case "Plants":
				
				food = makePlants(Integer.parseInt(parametresFood[0]),
						Double.parseDouble(parametresFood[1]),
						Double.parseDouble(parametresFood[2]));
				break;
				
			case "Herbivorou":
				
				food = makeHerbivorou(Integer.parseInt(parametresFood[0]),
						Integer.parseInt(parametresFood[1]) == 1,
						Double.parseDouble(parametresFood[2]),
						Double.parseDouble(parametresFood[3]));
				break;
		}

		return food;
	}


	private static Food makePredator(int id, boolean isAlive, 
			double nutritionalValue, double weight) {

		return new Predator(id, isAlive, nutritionalValue, weight); 
	}


	private static Food makeHerbivorou(int id, boolean isAlive, 
			double nutritionalValue, double weight) {

		return new Herbivorou(id, isAlive, nutritionalValue, weight); 
	}
	
	
	private static Food makePlants(int id, double nutritionalValue, double weight) {
		
		return new Plants(id, nutritionalValue, weight);
	}
}