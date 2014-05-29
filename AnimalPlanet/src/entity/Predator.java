package entity;


import javax.xml.bind.annotation.XmlRootElement;

import localisation.EngLocal;
import foodExceptions.NotSuitableFoodExceptions;


@XmlRootElement(name = "Predator")
public class Predator extends Animal{
	
	
	public Predator() {
		
		super();		
	}

	
	public Predator(int id, double nutritionalValue, double weight) {
		
		this(id, true, nutritionalValue, weight);
	}
	
	
	public Predator(int id, boolean isAlive, double nutritionalValue, double weight) {
		
		super(id, isAlive, nutritionalValue, weight);
	}

	
	@Override
	public void eat(Food eat) {
		
		super.eat(eat);
		
		if (!(eat instanceof Animal)) 
			throw new NotSuitableFoodExceptions(EngLocal.EXTEPTION_NOT_SUITABLE_FOOD_FOR_PREDATOR);
			
		Animal an = (Animal)eat;
		
		if (!an.isAlive()) 
			throw new NotSuitableFoodExceptions(EngLocal.EXTEPTION_PREDATOR_NOT_EAT_DEAD_ANIMALS);
				
		an.setAlive(false);
		
		this.setWeight(this.getWeight() + eat.getWeight() * eat.getNutritionalValue());
	}
}
