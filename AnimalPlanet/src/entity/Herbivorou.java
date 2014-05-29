package entity;


import javax.xml.bind.annotation.XmlRootElement;

import localisation.EngLocal;
import foodExceptions.NotSuitableFoodExceptions;


@XmlRootElement(name = "Herbivorou")
public class Herbivorou extends Animal{

	
	public Herbivorou() {
		
		super();
	}
	
	
	public Herbivorou(int id, double nutritionalValue, double weight) {
		
		this(id, true, nutritionalValue, weight);
	}
	
	
	public Herbivorou(int id, boolean isAlive, double nutritionalValue, double weight) {
		
		super(id, isAlive, nutritionalValue, weight);
	}

	
	@Override
	public void eat(Food eat) {
		
		super.eat(eat);
		
		if (!(eat instanceof Plants)) 
			throw new NotSuitableFoodExceptions(EngLocal.EXTEPTION_NOT_SUITABLE_FOOD_FOR_HERBIVOROU);
			
		if (eat.getWeight() * eat.getNutritionalValue() > this.getWeight() * 0.05) {
			
			eat.setWeight(eat.getWeight() -  this.getWeight() * 0.05);
			this.setWeight(eat.getWeight() * 0.05);
		}
	}
}
